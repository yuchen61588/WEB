import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import ElementPlus from "element-plus";
import { ElMessage } from "element-plus";
import "element-plus/dist/index.css";
import PrimeVue from "primevue/config";
import Calendar from "primevue/calendar";
import { ThemeProvider } from "./theme";
import axios from "axios";
import store from "./store";
import * as echarts from "echarts";

const app = createApp(App);
app.config.globalProperties.$message = ElMessage; //设置全局属性
app.config.globalProperties.$echarts = echarts;
// app.config.globalProperties.$axios = axios;
app
  .use(ElementPlus)
  .use(router)
  .use(ThemeProvider)
  .use(store)
  .use(axios)
  .component("Calendar", Calendar)
  .mount("#app"); //将挂载的vue实例挂到HTML上
