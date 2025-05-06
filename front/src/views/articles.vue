<template>
  <div class="dashboard">
    <!-- 左侧：作者列表 -->
    <el-card class="author-list">
      <template #header>
        <div class="card-header">
          <h3>作者文章统计</h3>
        </div>
      </template>

      <el-table
        :data="paginatedAuthors"
        style="width: 100%"
        :stripe="true"
        :border="true"
        highlight-current-row
        height="calc(85vh - 90px)"
        :row-style="{ height: '60px' }"
      >
        <el-table-column prop="id" label="id号" align="center" />
        <el-table-column prop="username" label="作者姓名" align="center" />
        <el-table-column prop="number" label="文章数量" align="center" />
        <el-table-column label="操作" align="center" width="160">
          <template #default="scope">
            <el-button
              type="primary"
              size="small"
              @click="handleClick(scope.row)"
            >
              文章管理
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination-container">
        <el-pagination
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[5, 10, 20]"
          :total="totalCount"
          background
          layout="total, sizes, prev, pager, next"
        />
      </div>
    </el-card>

    <!-- 分隔线 -->
    <div class="divider"></div>

    <!-- 右侧：图表 -->
    <el-card class="chart-container">
      <template #header>
        <div class="card-header">
          <h3>文章发布统计</h3>
        </div>
      </template>
      <div ref="chartRef" class="chart"></div>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted, computed, onUnmounted } from "vue";
import * as echarts from "echarts";
import axios from "axios";
import { useRouter } from "vue-router";
import { watch } from "vue"; //监听数据
const router = useRouter();

// 作者数据
const authors = ref([]);
const currentPage = ref(1);
const pageSize = ref(5);

// 计算总数
const totalCount = computed(() => authors.value?.length || 0);

// 分页数据计算
const paginatedAuthors = computed(() => {
  if (!authors.value) return [];
  const start = (currentPage.value - 1) * pageSize.value;

  return authors.value.slice(start, start + pageSize.value);
});

// 初始化数据
const initData = () => {
  axios
    .post("http://localhost:8080/arc/getarc", null, {
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
    })
    .then((response) => {
      authors.value = response.data;
      authors.value.username;
      renderChart();
    })
    .catch((error) => {
      alert(error);
      console.error("请求出错:", error);
      if (error.message === "Network Error") {
        console.log(
          "连接问题，请确认服务器 http://localhost:8080 是否正在运行"
        );
      }
    });
};

// 图表相关
const chartRef = ref(null);
let chartInstance = null;

// 渲染图表
const renderChart = () => {
  if (!chartRef.value || !authors.value) return;

  if (!chartInstance) {
    chartInstance = echarts.init(chartRef.value);
  }

  const option = {
    backgroundColor: "#ffffff",
    tooltip: {
      trigger: "axis",
      axisPointer: {
        type: "shadow",
      },
    },
    grid: {
      left: "3%",
      right: "4%",
      bottom: "3%",
      top: "8%",
      containLabel: true,
    },
    xAxis: {
      type: "category",
      data: paginatedAuthors.value.map((a) => a.username),
      axisLabel: {
        interval: 0,
        rotate: 0,
        color: "#333",
      },
      axisLine: {
        lineStyle: {
          color: "#999",
        },
      },
    },
    yAxis: {
      type: "value",
      name: "文章数量",
      minInterval: 1,
      axisLabel: {
        color: "#333",
      },
      axisLine: {
        lineStyle: {
          color: "#999",
        },
      },
      splitLine: {
        lineStyle: {
          color: "#eee",
        },
      },
    },
    series: [
      {
        name: "发表文章数",
        type: "bar",
        data: paginatedAuthors.value.map((a) => a.number),
        itemStyle: {
          color: "#409EFF",
        },
        label: {
          show: true,
          position: "top",
          color: "#333",
        },
      },
    ],
  };

  chartInstance.setOption(option);
};
watch(paginatedAuthors, () => {
  renderChart();
});
// 处理窗口大小变化
const handleResize = () => {
  chartInstance?.resize();
};

// 点击处理
const handleClick = (row) => {
  console.log("查看作者文章：", row);
  router.push({
    name: "articles_post",
    query: {
      name: row.username,
    },
  });
};

onMounted(() => {
  initData(); // 初始化数据
  window.addEventListener("resize", handleResize);
});

onUnmounted(() => {
  window.removeEventListener("resize", handleResize);
  chartInstance?.dispose();
});
</script>

<style scoped>
.dashboard {
  display: flex;
  gap: 0;
  padding: 20px;
  min-height: 100vh;
  background-color: #f5f7fa;
  color: #333;
  position: relative;
}

.author-list {
  flex: 1;
  background-color: #fff;
  margin-right: 2px;
  height: calc(100vh - 40px);
}

.chart-container {
  flex: 1;
  background-color: #fff;
  margin-left: 2px;
  height: calc(100vh - 70px);
}

/* 分隔线样式 */
.divider {
  width: 2px;
  height: calc(100vh - 40px);
  background-color: #dcdfe6;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.card-header h3 {
  margin: 0;
  color: #333;
  font-size: 1.4em;
}

.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}

.chart {
  height: calc(100vh - 230px);
  margin-top: 20px;
}

/* Element Plus 组件样式覆盖 */
:deep(.el-card) {
  border: none;
  color: #333;
  height: 100%;
  background-color: #fff;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
}

:deep(.el-card__body) {
  height: calc(100% - 60px);
  padding: 10px;
  background-color: #fff;
}

:deep(.el-card__header) {
  padding: 15px 20px;
  border-bottom: 1px solid #ebeef5;
  background-color: #fff;
  color: #333;
}

/* 表格样式 */
:deep(.el-table) {
  background-color: #fff;
  color: #333;
}

:deep(.el-table th),
:deep(.el-table tr) {
  background-color: #f5f7fa;
  color: #333;
}

:deep(.el-table td) {
  background-color: #fff;
  color: #333;
}

:deep(.el-table--striped .el-table__body tr.el-table__row--striped td) {
  background-color: #fafafa;
}

:deep(.el-table--enable-row-hover .el-table__body tr:hover > td) {
  background-color: #f0f7ff;
}

/* 分页器样式 */
:deep(.el-pagination) {
  --el-pagination-bg-color: #fff;
  --el-pagination-text-color: #333;
  --el-pagination-button-color: #333;
  --el-pagination-hover-color: #409eff;
}

/* 边框样式 */
:deep(.el-table--border),
:deep(.el-table--group) {
  border-color: #ebeef5;
}

:deep(.el-table td.el-table__cell),
:deep(.el-table th.el-table__cell.is-leaf) {
  border-color: #ebeef5;
}

:deep(.el-table--border::after),
:deep(.el-table--group::after) {
  background-color: #ebeef5;
}

/* 按钮样式 */
:deep(.el-button--primary) {
  background-color: #409eff;
  border-color: #409eff;
}

:deep(.el-button--primary:hover) {
  background-color: #66b1ff;
  border-color: #66b1ff;
}

/* 响应式布局 */
@media (max-width: 1400px) {
  .dashboard {
    padding: 10px;
  }

  .chart {
    height: calc(100vh - 200px);
  }
}
</style>
