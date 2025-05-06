<template>
  <div class="author-manager">
    <el-row :gutter="20" class="info-section">
      <el-col :span="6">
        <div class="avatar-container">
          <img
            :src="'http://localhost:8080/api/file/' + author.url"
            class="avatar"
            alt="作者头像"
          />
        </div>
      </el-col>
      <el-col :span="18">
        <div class="info-container">
          <div class="info-header">
            <h3>作者基本信息</h3>
          </div>
          <div class="info-content">
            <el-form :model="author" label-width="80px">
              <el-form-item label="用户名">
                <el-input v-model="author.username" disabled />
              </el-form-item>
              <el-form-item label="邮箱">
                <el-input v-model="author.email" disabled />
              </el-form-item>
              <el-form-item label="余额">
                <el-input v-model="author.balance" disabled />
              </el-form-item>
              <el-form-item label="生日">
                <div class="birthday-input">
                  <el-input v-model="author.birthday" disabled />
                </div>
                <div class="return-hint">点击"返回"可以回到文章管理页面</div>
              </el-form-item>
            </el-form>
            <div class="button-container">
              <el-button type="primary" class="return-btn" @click="goBack"
                >返回</el-button
              >
              <!-- TODO: 上方 goBack 方法中修改路由地址 -->
            </div>
          </div>
        </div>
      </el-col>
    </el-row>

    <div class="article-section">
      <div class="toolbar">
        <el-button
          type="primary"
          size="small"
          icon="el-icon-plus"
          @click="handleAdd"
          >新增</el-button
        >
        <div class="search-area">
          <el-input
            placeholder="请输入搜索内容"
            v-model="searchQuery"
            clearable
            @clear="handleSearch"
            prefix-icon="el-icon-search"
            size="small"
          />
          <el-button type="primary" size="small" @click="handleSearch"
            >搜索</el-button
          >
        </div>
      </div>

      <div class="operation-hint">下方进行文章的增删改操作</div>

      <el-table
        :data="filteredArticles"
        border
        style="width: 100%"
        :header-cell-style="{ backgroundColor: '#f5f7fa', color: '#606266' }"
      >
        <el-table-column prop="id" label="序号" width="80" align="center" />
        <el-table-column prop="title" label="文章标题" align="center" />
        <el-table-column
          prop="content"
          label="文章内容"
          align="left"
          :show-overflow-tooltip="true"
        />
        <el-table-column label="操作" width="200" align="center">
          <template #default="{ row }">
            <el-button type="text" size="small" @click="handleEdit(row)"
              >修改</el-button
            >
            <el-divider direction="vertical"></el-divider>
            <el-button
              type="text"
              size="small"
              class="delete-btn"
              @click="handleDelete(row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog title="新增联系人" v-model="dialogVisible" width="20%" center>
      <el-form
        :model="form"
        label-width="60px"
        ref="formRef"
        :rules="rules"
        status-icon
      >
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-input
            type="textarea"
            v-model="form.content"
            placeholder="请输入内容"
            :rows="8"
            resize="both"
            class="content-textarea"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addForm()">确 定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { useRouter, useRoute } from "vue-router";
import axios from "axios";
import { ref, computed, onMounted } from "vue";

import { ElMessageBox } from "element-plus";
import { useStore } from "vuex";
// 使用 Vuex 的 useStore 获取 store 实例
const store = useStore();

// 使用 computed 获取 Vuex 中的 user 数据
const user = computed(() => store.getters.getUser);
// 获取当前路由信息
const route = useRoute();
// 获取路由器实例
const router = useRouter();
// 使用 Vuex 的 useStore 获取 store 实例
const username = ref("");

// 定义响应式数据
const author = ref({
  username: "",
  email: "",
  balance: 1250.75,
  birthday: "",
  url: "",
});
const articles = ref([]);
const loadarcitle = () => {
  axios
    .post("http://localhost:8080/api/load", null, {
      params: {
        username: username.value,
      },
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
    })
    .then((response) => {
      author.value.username = username.value;
      author.value.email = response.data.email;
      author.value.url = response.data.url;
      author.value.birthday = response.data.birthday;
    })
    .catch((error) => {
      alert(error);
      console.error("请求出错:", error);
    });
  axios
    .post("http://localhost:8080/article/load", null, {
      params: {
        username: username.value,
      },
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
    })
    .then((response) => {
      articles.value = response.data;
    })
    .catch((error) => {
      alert(error);
      console.error("请求出错:", error);
    });
};
onMounted(() => {
  username.value = route.query.name;
  loadarcitle();
});
const searchQuery = ref("");

// 计算属性：过滤文章
const filteredArticles = computed(() => {
  const q = searchQuery.value.trim().toLowerCase();
  if (!q) return articles.value;
  return articles.value.filter(
    (item) =>
      item.title.toLowerCase().includes(q) ||
      item.content.toLowerCase().includes(q)
  );
});

// 方法：返回文章管理页面
const goBack = () => {
  // 修改路由到文章管理页面
  router.push({ name: "articles" });
};

// 方法：新增文章（待实现）
const addupdate = ref(false);
const dialogVisible = ref(false);
const formRef = ref(null);
const form = ref({
  id: 0,
  title: "",
  content: "",
});
const rules = ref({
  title: [{ required: true, message: "请写标题", trigger: "change" }],
  content: [{ required: true, message: "请写出文章内容", trigger: "change" }],
});
const handleAdd = () => {
  dialogVisible.value = true;
};
const addForm = () => {
  formRef.value.validate((valid) => {
    if (valid) {
      if (!addupdate.value) {
        form.value.id =
          articles.value.length > 0
            ? Math.max(...articles.value.map((item) => item.id)) + 1
            : 1;
        axios
          .post("http://localhost:8080/article/add", form.value, {
            params: {
              username: username.value,
            },
            headers: {
              "Content-Type": "application/json",
              Accept: "application/json",
            },
          })
          .then((response) => {
            if (response.data) {
              alert("插入成功");
              loadarcitle();
              form.value.id = 0;
              form.value.title = "";
              form.value.content = "";
              dialogVisible.value = false;
            } else {
              alert("插入失败");
              form.value.id = 0;
              form.value.title = "";
              form.value.content = "";
              dialogVisible.value = false;
            }
          });
      } else {
        axios
          .post("http://localhost:8080/article/update", form.value, {
            params: {
              username: username,
            },
            headers: {
              "Content-Type": "application/json",
              Accept: "application/json",
            },
          })
          .then((response) => {
            if (response.data) {
              alert("更新成功");
              loadarcitle();
              form.value.id = 0;
              form.value.title = "";
              form.value.content = "";
              dialogVisible.value = false;
              addupdate.value = false;
            } else {
              alert("更新失败");
              form.value.id = 0;
              form.value.title = "";
              form.value.content = "";
              dialogVisible.value = false;
              addupdate.value = false;
            }
          });
      }
    }
  });
};
// 方法：编辑文章
const handleEdit = (row) => {
  form.value = {
    id: row.id,
    title: row.title,
    content: row.content,
  };
  dialogVisible.value = true;
  addupdate.value = true;
};

// 方法：删除文章
function handleDelete(row) {
  form.value = {
    id: row.id,
    title: row.title,
    content: row.content,
  };
  ElMessageBox.confirm("确定删除该文章吗?", "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      axios
        .post("http://localhost:8080/article/delete", form.value, {
          params: {
            username: username.value,
          },
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
          },
        })
        .then((response) => {
          if (response.data) {
            alert("删除成功");
            loadarcitle();
            form.value.id = 0;
            form.value.title = "";
            form.value.content = "";
          } else {
            alert("删除失败");
            form.value.id = 0;
            form.value.title = "";
            form.value.content = "";
          }
        })
        .catch((error) => {
          alert(error);
          alert("删除失败");
        });
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "已取消删除",
      });
    });
}
</script>

<style scoped>
.author-manager {
  padding: 20px;
  min-height: 100vh;
  background-color: #f0f2f5;
}

.info-section {
  margin-bottom: 20px;
}

.avatar-container {
  border: 1px solid #e4e7ed;
  border-radius: 4px;
  overflow: hidden;
  background-color: #fff;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
}

.avatar {
  max-width: 100%;
  display: block;
}

.info-container {
  border: 1px solid #e4e7ed;
  border-radius: 4px;
  background-color: #fff;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
}

.info-header {
  padding: 15px 20px;
  border-bottom: 1px solid #ebeef5;
}

.info-header h3 {
  margin: 0;
  font-size: 16px;
  font-weight: 500;
  color: #303133;
}

.info-content {
  padding: 20px;
}

.birthday-input {
  display: inline-block;
  width: calc(100% - 200px);
}

.return-hint {
  display: inline-block;
  margin-left: 10px;
  color: #f56c6c;
  font-size: 12px;
}

.button-container {
  text-align: center;
  margin-top: 10px;
}

.return-btn {
  width: 80px;
}

.article-section {
  background-color: #fff;
  border-radius: 4px;
  border: 1px solid #e4e7ed;
  padding: 20px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
}

.toolbar {
  display: flex;
  justify-content: space-between;
  margin-bottom: 15px;
}

.search-area {
  display: flex;
  align-items: center;
}

.search-area .el-input {
  width: 200px;
  margin-right: 10px;
}

.operation-hint {
  text-align: center;
  color: #f56c6c;
  margin-bottom: 15px;
  font-size: 14px;
}

.delete-btn {
  color: #f56c6c;
}

.el-table {
  margin-bottom: 20px;
}
.content-textarea :deep(.el-textarea__inner) {
  min-height: 150px !important;
  font-size: 14px;
}

/* 可选：调整弹窗整体高度，使内容区域更大 */
:deep(.el-dialog) {
  min-height: 400px;
}
</style>
