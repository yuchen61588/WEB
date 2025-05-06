<template>
  <div class="big-box">
    <!-- 折叠按钮 -->
    <div class="left">
      <div class="lefts">
        <el-icon class="fold-btn" @click="toggleCollapse">
          <component :is="isCollapsed ? Expand : Fold" />
        </el-icon>
      </div>

      <!-- 导航菜单 -->
      <el-menu
        class="nav-menu"
        :collapse="isCollapsed"
        :default-active="activeIndex"
        :background-color="navBg"
        :text-color="navText"
        :active-text-color="themeColor"
        router
      >
        <!-- 菜单项 -->
        <el-menu-item index="/main/home">
          <el-icon><Document /></el-icon>
          <span>首页</span>
        </el-menu-item>

        <el-menu-item index="/main/profile">
          <el-icon><User /></el-icon>
          <span>个人中心</span>
        </el-menu-item>

        <el-sub-menu index="3" class="sub-menu">
          <template #title>
            <el-icon><Grid /></el-icon>
            <span>功能菜单</span>
          </template>
          <el-menu-item index="/main/users">用户管理</el-menu-item>
          <el-menu-item index="/main/articles">文章管理</el-menu-item>
        </el-sub-menu>

        <el-sub-menu index="4">
          <template #title>
            <el-icon><Setting /></el-icon>
            <span>主题设置</span>
          </template>
          <el-menu-item @click="switchTheme('light')">浅色模式</el-menu-item>
          <el-menu-item @click="switchTheme('dark')">深色模式</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </div>

    <!-- 遮罩层 -->
    <div class="mask" v-if="showMask" @click="toggleCollapse"></div>

    <!-- 主内容区 -->
    <div class="main-area" :class="{ 'blur-effect': showMask }">
      <div class="nav-top">
        <div class="current-path">{{ currentPath }}</div>
        <div class="time-display">{{ currentTime }}</div>
        <!-- 头像区域 -->
        <div>
          <el-button
            type="primary"
            @click="loginout()"
            style="margin-left: 8px"
          >
            退出
          </el-button>
        </div>
        <div class="avatar">
          <img :src="'http://localhost:8080/api/file/' + user.url" alt="头像" />
        </div>
      </div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useStore } from "vuex";
// 使用 Vuex 的 useStore 获取 store 实例
const store = useStore();

// 使用 computed 获取 Vuex 中的 user 数据
const user = computed(() => store.getters.getUser);
import {
  Fold,
  Expand,
  User,
  Setting,
  Document,
  Grid,
} from "@element-plus/icons-vue";

const route = useRoute();
const router = useRouter();
const isCollapsed = ref(false);
const showMask = ref(false); // 控制遮罩层显示
const currentTime = ref("");
const theme = ref(localStorage.getItem("theme") || "light");
const loginout = () => {
  router.push({ name: "login" });
};
// 主题颜色配置
const themeConfig = computed(() => ({
  light: {
    navBg: "#ffffff",
    navText: "#606266",
    themeColor: "#409EFF",
    pageBg: "#f0f2f5", // 新增页面背景色
    menu: "#409EFF",
    calendarWrapperBg: "#ffffff",
    calendarWrapperFont: "#333333",
    calendarBorder: "#cccccc",
    calendarBtnBg: "#dddddd",
    calendarBtnFont: "#333333",
    calendarBtnHoverBg: "#cccccc",
    calendarTheadBg: "#e1f5fe",
    calendarTheadFont: "#333333",
    calendarDayBg: "#ffffff",
    calendarDayBorder: "#dddddd",
    calendarDayFont: "#333333",
    calendarDayCurrentBg: "#ffffff",
    calendarTodayBg: "#1976d2",
    calendarTodayFont: "#ffffff",
    calendarTodayBorder: "#1976d2",
    //个人中心
    homebg: "#ffffff",
    cardbg: "#ffffff",
    textprimary: "333333",
    textsecondary: "#666666",
    texttertiary: "#999999",
    bordercolor: "#e0e0e0",
    //表格
    tableheaderbg: "#f5f7fa",
    tableheadertext: "#333",
    tablerowodd: "#ffffff",
    tableroweven: "#fafafa",
    tablerowhover: "#f0f7ff",
    tableborder: "#ebeef5",
  },
  dark: {
    navBg: "#1a1a1a",
    navText: "#ffffff",
    themeColor: "#7ec2ff",
    pageBg: "#121212", // 新增页面背景色
    menu: "#7ec2ff",
    calendarWrapperBg: "#1a1a1a",
    calendarWrapperFont: "#ffffff",
    calendarBorder: "rgb(43, 42, 42)",
    calendarBtnBg: "#333333",
    calendarBtnFont: "#ffffff",
    calendarBtnHoverBg: "#444444",
    calendarTheadBg: "#333333",
    calendarTheadFont: "#ffffff",
    calendarDayBg: "#2a2a2a",
    calendarDayBorder: "#333333",
    calendarDayFont: "#ffffff",
    calendarDayCurrentBg: "#1a1a1a",
    calendarTodayBg: "#b9cee3",
    calendarTodayFont: "#ffffff",
    calendarTodayBorder: "#b9cee3",
    homebg: "#121212",
    cardbg: "#1e1e1e",
    textprimary: "#ffffff",
    textsecondary: "#aaaaaa",
    texttertiary: "#888888",
    bordercolor: "#333333",
    tableheaderbg: "#1d1e1f",
    tableheadertext: "#eaeaea",
    tablerowodd: "#2a2a2a",
    tableroweven: "#1A1A1A",
    tablerowhover: "#1e3a8a",
    tableborder: "#444",
  },
}));

// 当前路径显示
const currentPath = computed(() => {
  const matched = route.matched.filter((item) => item.meta?.title); //item表示本页面的路由
  return matched.map((item) => item.meta.title).join(" > ") || "首页";
});

// 切换主题
const switchTheme = (mode) => {
  theme.value = mode;
  localStorage.setItem("theme", mode);
  applyTheme();
};

// 应用主题样式
const applyTheme = () => {
  const root = document.documentElement;
  const config = themeConfig.value[theme.value];
  Object.entries(config).forEach(([key, value]) => {
    root.style.setProperty(`--${key}`, value);
  });

  // 更新子菜单和子菜单项的颜色
  // 更新子菜单项的字体颜色
};

// 折叠切换
const toggleCollapse = () => {
  isCollapsed.value = !isCollapsed.value;
  showMask.value = isCollapsed.value; // 控制遮罩层
};

// 初始化时间显示
onMounted(() => {
  setInterval(() => {
    currentTime.value = new Date().toLocaleTimeString("zh-CN", {
      hour: "2-digit",
      minute: "2-digit",
      second: "2-digit",
      hour12: false,
    });
  }, 1000);
  applyTheme();
});
</script>

<style scoped>
.big-box {
  display: flex;
  min-height: 100vh;
  background-color: var(--pageBg);
}
.left {
  display: flex;
  flex-direction: column;
}
.fold-btn {
  display: block;
  left: 20px;
  top: 20px;
  z-index: 1000;
  cursor: pointer;
  font-size: 24px;
  color: var(--navText);
  margin-bottom: 20px;
  padding-bottom: 10px;
}
.lefts {
  display: flex;
  flex-direction: row;
}

.nav-menu {
  width: 220px;
  min-height: 100vh;
  border-right: none;
  color: var(--menu);
}

.nav-menu:not(.el-menu--collapse) {
  width: 220px;
}

.main-area {
  flex: 1;
  padding: 20px;
}

.nav-top {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 20px;
  padding: 10px 20px;
  background: var(--navBg);
  border-radius: 8px;
}
.sub-menu {
  color: var(--navText);
  background-color: var(--navBg);
}

.current-path {
  color: var(--themeColor);
  font-weight: 500;
}

.time-display {
  color: var(--themeColor);
  font-weight: 500;
}

.avatar {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  overflow: hidden;
}

.avatar img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.mask {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background: rgba(0, 0, 0, 0.5);
  z-index: 900;
}

.blur-effect {
  filter: blur(5px);
}

/* 响应式设计 */
@media (max-width: 768px) {
  .nav-menu {
    position: fixed;
    left: -220px;
    z-index: 999;
  }

  .nav-menu.mobile-show {
    left: 0;
  }

  .fold-btn {
    display: block;
  }

  .main-area {
    margin-left: 0;
    width: 100vw;
  }
}
/**子菜单背景颜色 */
/* 侧边栏菜单背景色 */
.nav-menu {
  background-color: var(
    --navBg
  ) !important; /* 使用 !important 确保背景色优先级 */
}

/* 子菜单背景色 */
/* 添加以下样式到style部分 */
.el-sub-menu > .el-sub-menu__title {
  background-color: var(--navBg) !important;
  color: var(--navText) !important;
}

:deep(.el-sub-menu) .el-sub-menu__title:hover {
  background-color: rgb(197, 233, 235) !important;
  color: var(--navText) !important;
} /**深层穿透  要有scoped*/

/* 原有样式修改为 */
.nav-menu,
.nav-menu .el-sub-menu .el-menu-item,
.nav-menu .el-sub-menu__title {
  background-color: var(--navBg) !important;
}

.el-menu--vertical {
  background-color: var(--navBg) !important;
  border-left: 1px solid var(--themeColor) !important; /* 添加区分线 */
}

.el-menu--vertical .el-menu-item {
  background-color: var(--navBg) !important;
  min-width: 180px !important; /* 保持子菜单宽度一致 */
}
.nav-menu {
  /* 主菜单字体颜色 */
  --el-menu-text-color: var(--navText) !important; /* 控制全局文本颜色 */
  --el-menu-active-color: var(--themeColor) !important; /* 激活状态颜色 */
}

/* 单独处理父级菜单标题 */
.el-sub-menu__title {
  color: var(--navText) !important;
}
</style>
