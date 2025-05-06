<template>
  <div class="user-management-container">
    <!-- 标题或面包屑等 -->
    <div class="user-management-header">
      <h2>用户管理列表</h2>
    </div>

    <!-- 可以根据需要，放一个操作区，比如搜索、添加用户等 -->
    <div class="toolbar">
      <el-input
        v-model="searchValue"
        placeholder="请输入用户名"
        style="width: 200px; margin-right: 16px"
        clearable
      ></el-input>
      <el-button type="primary" @click="handleSearch()"> 搜索 </el-button>
      <el-button
        type="success"
        @click="handleAddUser()"
        style="margin-left: 8px"
      >
        新增用户
      </el-button>
    </div>

    <!-- 用户列表表格 -->
    <el-table
      v-loading="tableLoading"
      :data="paginatedData"
      stripe
      border
      style="margin-top: 20px; width: 100%"
    >
      <el-table-column
        prop="id"
        label="用户ID"
        width="80"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="date"
        label="日期"
        min-width="160"
        align="center"
      />
      <el-table-column
        prop="username"
        label="用户名"
        width="120"
        align="center"
      ></el-table-column>

      <el-table-column prop="city" label="城市" min-width="80" align="center" />
      <el-table-column prop="area" label="地区" min-width="80" align="center" />
      <el-table-column
        prop="address"
        label="地址"
        min-width="250"
        align="center"
        show-overflow-tooltip
      />
      <el-table-column prop="zip" label="邮编" min-width="100" align="center" />

      <!-- 操作列，可以放编辑/删除等操作按钮 -->
      <el-table-column label="操作" width="120" align="center">
        <template #default="{ row }">
          <el-button size="mini" type="text" @click="handleEdit(row)">
            编辑
          </el-button>
          <el-button size="mini" type="text" @click="handleDelete(row)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination-container">
      <span class="pagination-info"
        >共 {{ total }} 条，每页 {{ pageSize }}/页</span
      >
      <el-pagination
        background
        layout="total, sizes, prev, pager, next, jumper"
        :current-page="currentPage"
        :page-size="pageSize"
        :page-sizes="[5, 10, 15, 20]"
        :total="total"
        @current-change="handleCurrentPageChange"
        @size-change="handlePageSizeChange"
      />
    </div>
    <el-dialog v-model="dialogVisible" title="新增联系人" width="20%">
      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-width="100px"
        status-icon
      >
        <el-form-item label="日期" prop="date">
          <el-date-picker
            v-model="form.date"
            type="datetime"
            placeholder="选择日期时间"
          />
        </el-form-item>
        <el-form-item label="姓名" prop="username">
          <el-input v-model="form.username" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="城市" prop="city">
          <el-select v-model="form.city" placeholder="请选择城市">
            <el-option label="上海" value="上海" />
            <el-option label="北京" value="北京" />
            <!-- 可以根据需要添加更多选项 -->
          </el-select>
        </el-form-item>
        <el-form-item label="地区" prop="area">
          <el-select v-model="form.area" placeholder="请选择地区">
            <el-option label="上海" value="上海" />
            <el-option label="北京" value="北京" />
            <!-- 可以根据需要添加更多选项 -->
          </el-select>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="邮编" prop="zip">
          <el-input v-model="form.zip" placeholder="请输入邮编" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="submitForm()">确定</el-button>
        </span>
      </template>
    </el-dialog>
    <el-dialog v-model="dialogVisible2" title="新增联系人" width="20%">
      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-width="100px"
        status-icon
      >
        <el-form-item label="日期" prop="date">
          <el-date-picker
            v-model="form.date"
            type="datetime"
            placeholder="选择日期时间"
          />
        </el-form-item>
        <el-form-item label="姓名" prop="username">
          <el-input v-model="form.username" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="城市" prop="city">
          <el-select v-model="form.city" placeholder="请选择城市">
            <el-option label="上海" value="上海" />
            <el-option label="北京" value="北京" />
            <!-- 可以根据需要添加更多选项 -->
          </el-select>
        </el-form-item>
        <el-form-item label="地区" prop="area">
          <el-select v-model="form.area" placeholder="请选择地区">
            <el-option label="上海" value="上海" />
            <el-option label="北京" value="北京" />
            <!-- 可以根据需要添加更多选项 -->
          </el-select>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="邮编" prop="zip">
          <el-input v-model="form.zip" placeholder="请输入邮编" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible2 = false">取消</el-button>
          <el-button type="primary" @click="UpdateForm()">修改</el-button>
        </span>
      </template>
    </el-dialog>
    <el-dialog
      v-model="dialogVisible3"
      title="提示"
      width="30%"
      :before-close="handleClose"
    >
      <span>此操作将永久删除该联系人，是否继续？</span>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleConfirm()">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import axios from "axios";
import { useStore } from "vuex";
/**
 * 是否在请求中，可以用来控制表格的 v-loading 状态
 */
const tableLoading = ref(false);
const currentPage = ref(1); // 当前页
const pageSize = ref(5); // 每页数量
const store = useStore();
const users = computed(() => store.getters.getUser);
const userData = ref([]);
/**
 * 模拟的用户数据，这里使用 ref 存储
 */

const loaderuser = () => {
  axios
    .post("http://localhost:8080/data/load", null, {
      params: {
        username: users.value.username,
      },

      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
        Accept: "application/json",
      },
    })
    .then((response) => {
      userData.value = response.data;
    });
};
onMounted(() => loaderuser());
/**
 * 用于搜索的关键字
 */
const searchValue = ref("");
//模拟后端加载数据

/**
 * 基于 searchValue 对 userData 做简单过滤
 */

const total = computed(() => filteredData.value.length);
// 切换页码
function handleCurrentPageChange(page) {
  currentPage.value = page;
}

// 切换每页数量
function handlePageSizeChange(size) {
  pageSize.value = size;
  currentPage.value = 1; // 一般切换 pageSize 后可重置 currentPage
}
//绑定分页数据
const paginatedData = computed(() => {
  const startIndex = (currentPage.value - 1) * pageSize.value;
  const endIndex = currentPage.value * pageSize.value;

  return filteredData.value.slice(startIndex, endIndex); //数据分离提取
});
//搜索
const filteredData = computed(() => {
  //没有输入返回全部数据
  if (!searchValue.value) {
    return userData.value;
  }
  return userData.value.filter((item) =>
    item.username.includes(searchValue.value)
  );
});

// 编辑
const formRef = ref(null); //绑定ref一般用于表单方法
const form = ref({
  id: 0,
  date: "",
  username: "",
  city: "",
  area: "",
  address: "",
  zip: "",
});
const dialogVisible2 = ref(false);
function handleEdit(row) {
  form.value = {
    id: row.id,
    date: row.date,
    username: row.username,
    city: row.city,
    area: row.area,
    address: row.address,
    zip: row.zip,
  };
  dialogVisible2.value = true;
}
const isIDExist = (idTOCheck) => {
  return userData.value.some((user) => user.id === idTOCheck);
};
const UpdateForm = () => {
  if (!isIDExist(form.value.id)) {
    alert("修改ID不存在");
    return;
  }
  formRef.value.validate((valid) => {
    if (valid) {
      axios
        .post("http://localhost:8080/data/update", form.value, {
          params: {
            username: users.value.username,
          },
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
          },
        })
        .then((response) => {
          if (response.data) {
            alert("修改成功");
            loaderuser();
            form.value.id = "";
            (form.value.date = ""),
              (form.value.username = ""),
              (form.value.city = ""),
              (form.value.area = ""),
              (form.value.address = ""),
              (form.value.zip = ""),
              (dialogVisible2.value = false);
          } else {
            alert("修改失败");
            form.value.id = "";
            (form.value.date = ""),
              (form.value.username = ""),
              (form.value.city = ""),
              (form.value.area = ""),
              (form.value.address = ""),
              (form.value.zip = ""),
              (dialogVisible2.value = false);
          }
        });
    } else {
      alert("表单验证失败，请检查输入");
      return false;
    }
  });
};
const dialogVisible3 = ref(false);
function handleDelete(row) {
  dialogVisible3.value = true;
  form.value = {
    id: row.id,
    date: row.date,
    username: row.username,
    city: row.city,
    area: row.area,
    address: row.address,
    zip: row.zip,
  };
}
// 删除
function handleConfirm() {
  axios
    .post("http://localhost:8080/data/delete", form.value, {
      params: {
        username: users.value.username,
      },
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
    })
    .then((response) => {
      if (response.data) {
        alert("删除成功");
        loaderuser();
        form.value.id = "";
        (form.value.date = ""),
          (form.value.username = ""),
          (form.value.city = ""),
          (form.value.area = ""),
          (form.value.address = ""),
          (form.value.zip = ""),
          (dialogVisible3.value = false);
      } else {
        alert("删除失败");
        form.value.id = "";
        (form.value.date = ""),
          (form.value.username = ""),
          (form.value.city = ""),
          (form.value.area = ""),
          (form.value.address = ""),
          (form.value.zip = ""),
          (dialogVisible3.value = false);
      }
    })
    .catch((error) => {
      alert(error);
      alert("删除失败");
    });
  //    userData.value.splice(index, 1);
  //    alert(`已删除：${row.username}`);
  // // 如果删除后分页数据不足，也可根据需要重置当前页
  //    if ((currentPage.value - 1) * pageSize.value >= filteredData.value.length) {
  //       currentPage.value = Math.max(currentPage.value - 1, 1);
  //   }
}
//弹窗

const dialogVisible = ref(false);
const rules = ref({
  date: [{ required: true, message: "请选择日期时间", trigger: "change" }],
  username: [{ required: true, message: "请输入姓名", trigger: "blur" }],
  city: [{ required: true, message: "请选择城市", trigger: "change" }],
  area: [{ required: true, message: "请选择地区", trigger: "change" }],
  address: [{ required: true, message: "请输入地址", trigger: "blur" }],
  zip: [{ required: true, message: "请输入邮编", trigger: "blur" }],
});
const handleAddUser = () => {
  dialogVisible.value = true;
};
const submitForm = () => {
  //验证表单规则
  formRef.value.validate((valid) => {
    if (valid) {
      form.value.id =
        userData.value.length > 0
          ? Math.max(...userData.value.map((item) => item.id)) + 1 //...不能省，要展开数组
          : 1;
      axios
        .post("http://localhost:8080/data/add", form.value, {
          params: {
            username: users.value.username,
          },

          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
          },
        })
        .then((response) => {
          if (response.data) {
            alert("插入成功");
            loaderuser();
            form.value.id = "";
            (form.value.date = ""),
              (form.value.username = ""),
              (form.value.city = ""),
              (form.value.area = ""),
              (form.value.address = ""),
              (form.value.zip = ""),
              (dialogVisible.value = false);
          } else {
            alert("插入失败");
            form.value.id = "";
            (form.value.date = ""),
              (form.value.username = ""),
              (form.value.city = ""),
              (form.value.area = ""),
              (form.value.address = ""),
              (form.value.zip = ""),
              (dialogVisible.value = false);
          }
        });
    } else {
      alert("表单验证失败，请检查输入");
      return false;
    }
  });
};
</script>

<style scoped>
.user-management-container {
  padding: 20px;
  background: var(--pageBg); /* 示例中为了看得清楚，可自行去掉或修改 */
}

.toolbar {
  margin-bottom: 10px;
}
.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.pagination-info {
  color: var(--textsecondary);
  font-size: 14px;
}
.user-management-header {
  margin-bottom: 20px;
  text-align: center;
  color: var(--calendarWrapperFont);
}
:deep(.el-table) {
  --el-table-bg-color: var(--pageBg); /* 使用CSS变量 */
  --el-table-tr-bg-color: var(--pageBg); /* 行背景 */
}

/* 表头样式 */
:deep(.el-table__header) th {
  background-color: var(--tableheaderbg) !important;
  color: var(--calendarBtnFont);
  font-weight: bold;
}

/* 表格行样式 */
:deep(.el-table__body) tr:hover > td {
  background-color: var(--tablerowhover) !important;
}
:deep(.el-input) {
  background-color: var(--pageBg) !important;
}
/* 斑马纹效果 */
:deep(.el-table__body) tr:nth-child(odd) {
  background-color: var(--tablerowodd) !important;
  color: var(--calendarBtnFont);
}
:deep(.el-table__body) tr:nth-child(even) td {
  background-color: var(--tableroweven) !important;
  color: var(--calendarBtnFont);
}

/* 单元格样式 */
:deep(.el-table__cell) {
  padding: 12px 0; /* 调整单元格内边距 */
  border-bottom: 1px solid var(--tableborder) !important;
}
</style>
