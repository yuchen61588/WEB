<template>
  <div id="app">
    <!--背景轮播图-->
    <div class="background-slideshow">
      <div
        v-for="(img, index) in images"
        :key="index"
        class="slide"
        :class="{ active: currentSlide === index }"
        :style="{ backgroundImage: `url(${img})` }"
      ></div>
      <!--active检测是否条件等于-->
    </div>
    <!-- Main Section -->

    <el-main class="main-content">
      <div class="hero-section">
        <h1 class="title">AI-explore-master</h1>
        <h3 class="subtitle">AI探索者-引领者</h3>
        <p class="subtitles">大模型评测系统</p>
        <p class="description">由XXXXXX研发的</p>
        <p class="description">
          大模型测评模型实例,支持OpenAI系列,DeepSeek系列等大模型的测评展示
        </p>
        <div class="buttons">
          <el-button class="btn" size="large" @click="openRegisterDialog()"
            >注册账号</el-button
          >
          <!-- View Leaderboards Button -->
          <el-button class="btn" size="large" @click="openLoginDialog()"
            >登录</el-button
          >
        </div>
        <div class="bottom">
          <p class="bottoms">made in yuchen61588</p>
          <p class="bottoms">github主页:https://github.com/yuchen61588</p>
        </div>
      </div>
    </el-main>
    <!-- 注册弹窗 -->
    <el-dialog v-model="loadingDialog" width="50%" class="dialog">
      <el-form
        :model="registerForm"
        @submit.prevent="submitRegister"
        :rules="registerRules"
      >
        <template #title>
          <div class="dialog-header">
            <span>注册</span>
            <i class="el-icon-close" @click="loadingDialog = false"></i>
            <!-- 关闭按钮 -->
          </div>
        </template>
        <div class="main-dialog">
          <div class="box">
            <h1 class="imgupld-up">Sign in</h1>
            <el-upload
              class="avatar-uploader"
              action="http://localhost:8080/api/imageUrl"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
              name="mFile"
            >
              <img
                v-if="imageUrl"
                :src="'http://localhost:8080/api/file/' + imageUrl"
                class="avatar"
              />
              <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
            </el-upload>
            <h2 class="imgupld">上传头像</h2>
            <div class="tt">login page by yuchen61588</div>
          </div>

          <div class="register-input">
            <div class="label1">Username:</div>
            <el-form-item prop="username"
              ><!--加前面标签名的 然后添加规则-->
              <el-input
                v-model="registerForm.username"
                placeholder="请输入用户名"
                :suffix-icon="User"
              ></el-input>
            </el-form-item>
            <div class="label1">Password:</div>
            <el-form-item prop="password">
              <el-input
                v-model="registerForm.password"
                type="password"
                placeholder="请输入密码"
                :suffix-icon="Lock"
              ></el-input>
            </el-form-item>
            <div class="label1">Confirm Password:</div>
            <el-form-item prop="confirmPassword">
              <el-input
                v-model="registerForm.confirmPassword"
                type="password"
                placeholder="确认密码"
                :suffix-icon="Lock"
              ></el-input>
            </el-form-item>
            <div class="label1">email:</div>
            <el-form-item prop="email">
              <el-input
                v-model="registerForm.email"
                placeholder="请输入电子邮箱"
                :suffix-icon="Lock"
              ></el-input>
            </el-form-item>
            <div class="label1">Birthday:</div>
            <el-form-item prop="birthday">
              <el-date-picker
                v-model="registerForm.birthday"
                type="date"
                placeholder="选择出生日期"
              ></el-date-picker>
              <!--可以通过.方式处理多个元素对象-->
            </el-form-item>
            <!--处理文件上传-->

            <div class="dialog-footer">
              <el-button type="primary" @click="submitRegister()"
                >注册</el-button
              >
            </div>
          </div>
        </div>
      </el-form>
    </el-dialog>

    <!-- 登录弹窗 -->
    <el-dialog v-model="loadingDialog2" width="40%" class="dialog">
      <el-form
        :model="loginForm"
        @submit.prevent="submitLogin"
        :rules="loginrules"
      >
        <template #title>
          <div class="dialog-header">
            <span>登录</span>
            <i class="el-icon-close" @click="loadingDialog2 = false"></i>
            <!-- 关闭按钮 -->
          </div>
        </template>
        <div class="login">
          <div class="left-login">
            <h1 class="imgupld-up">Log in</h1>
            <el-button class="other">
              <img src="/image/QQ图片20250314193530.png" alt="" class="qq" />
              Continue With Tencent QQ
            </el-button>
            <el-button class="other">
              <img src="/image/QQ图片20250314193603.png" alt="" class="fb" />
              Continue With Facebook
            </el-button>
            <el-button class="other">
              <img src="/image/QQ图片20250314193609.png" alt="" class="ge" />
              Continue With Google
            </el-button>
            <div class="tt">login page by yuchen61588</div>
          </div>
          <div class="login-input">
            <div class="label1">Username:</div>
            <el-form-item prop="username">
              <el-input
                v-model="loginForm.username"
                placeholder="请输入用户名"
                :suffix-icon="User"
              ></el-input>
            </el-form-item>
            <div class="label1">Password:</div>
            <el-form-item prop="password">
              <el-input
                v-model="loginForm.password"
                type="password"
                placeholder="请输入密码"
                :suffix-icon="Lock"
              ></el-input>
            </el-form-item>
            <div class="dialog-footer">
              <el-button
                type="primary"
                style="background-color: aquamarine"
                @click="openChangePasswordDialog()"
                >修改密码</el-button
              >
              <el-button type="primary" @click="submitLogin()">登录</el-button>
            </div>
          </div>
        </div>
      </el-form>
    </el-dialog>
    <!--修改密码弹窗-->
    <el-dialog
      v-model="loadingDialog3"
      title="修改密码"
      width="40%"
      class="dialog"
    >
      <el-form
        :model="passwordForm"
        @submit.prevent="submitChangePassword"
        :rules="changepasswordRule"
      >
        <template #title>
          <div class="dialog-header">
            <span>修改</span>
            <i class="el-icon-close" @click="loadingDialog3 = false"></i>
            <!-- 关闭按钮 -->
          </div>
        </template>
        <div class="label1">Username:</div>
        <el-form-item prop="username">
          <el-input
            v-model="passwordForm.username"
            placeholder="请输入用户名"
            :suffix-icon="User"
          ></el-input>
        </el-form-item>
        <div class="label1">Password:</div>
        <el-form-item prop="password">
          <el-input
            v-model="passwordForm.password"
            type="password"
            placeholder="请输入密码"
            :suffix-icon="Lock"
          ></el-input>
        </el-form-item>
        <div class="label1">newPassword:</div>
        <el-form-item prop="newPassword">
          <el-input
            v-model="passwordForm.newPassword"
            type="password"
            placeholder="请输入新密码"
            :suffix-icon="Lock"
          ></el-input>
        </el-form-item>
        <div class="label1">confirmNewPassword:</div>
        <el-form-item prop="confirmNewPassword">
          <el-input
            v-model="passwordForm.confirmNewPassword"
            type="password"
            placeholder="请确认新密码"
            :suffix-icon="Lock"
          ></el-input>
        </el-form-item>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitChangePassword()"
            >改密码</el-button
          >
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>

<script setup>
import "element-plus/dist/index.css";
import { ref, onMounted, onUnmounted } from "vue";
import { useRouter } from "vue-router";
import { Lock, User, Plus } from "@element-plus/icons-vue";
import axios from "axios";
import { useStore } from "vuex";
import { ElMessage } from "element-plus";
const router = useRouter();
const store = useStore(); //在set up方法中 要使用userouter获得实例
const currentSlide = ref(0);
let timer = null;
const beforeAvatarUpload = (file) => {
  const isJpgOrPng = file.type.startsWith("image/");
  const isLt10M = file.size / 1024 / 1024 <= 10;

  if (!isJpgOrPng) {
    ElMessage.error("仅支持图片文件");
    return false;
  }
  if (!isLt10M) {
    ElMessage.error("文件大小不能超过 10MB");
    return false;
  }
  return true;
};
const imageUrl = ref("");
const editForm = ref();
// 上传成功回调，获取后端返回的URL
const handleAvatarSuccess = (response) => {
  if (response != null) {
    imageUrl.value = response; // 关键：更新响应式数据
    ElMessage.success("图片上传成功");
  } else {
    ElMessage.error(`上传失败：${response.message}`);
  }
};
const images = ref([
  "/image/image.png",
  "/image/image2.png",
  "/image/image3.jpg",
  "/image/image4.png",
]);

// 预加载优化
const preloadImages = () => {
  images.value.forEach((img) => {
    const image = new Image();
    image.src = img;
    image.onload = () => console.log(`预加载完成: ${img}`);
    image.onerror = () => console.error(`预加载失败: ${img}`);
  });
};

// 轮播控制
const nextSlide = () => {
  currentSlide.value = (currentSlide.value + 1) % images.value.length;
};

// 自动播放控制
const startSlideshow = () => {
  timer = setInterval(nextSlide, 8000);
};

onMounted(() => {
  preloadImages();
  startSlideshow();
});

onUnmounted(() => {
  clearInterval(timer);
});
//弹窗方法
const loadingDialog = ref(false);
const loadingDialog2 = ref(false);
const loadingDialog3 = ref(false);
const registerForm = ref({
  username: "",
  password: "",
  confirmPassword: "",
  email: "",
  birthday: "",
});
const loginForm = ref({
  username: "",
  password: "",
});
const passwordForm = ref({
  username: "",
  password: "",
  newPassword: "",
  confirmNewPassword: "",
});
//表单验证规则
const registerRules = {
  username: [
    { required: true, message: "用户名不能为空", trigger: "blur" },
    { min: 3, max: 12, message: "长度在3-12个字符", trigger: "blur" },
  ],
  password: [
    { required: true, message: "密码不能为空", trigger: "blur" },
    { min: 6, message: "密码至少6位", trigger: "blur" },
  ],
  confirmPassword: [
    { required: true, message: "确认密码不能为空", trigger: "blur" },
    {
      validator: (rule, value, callback) => {
        if (value !== registerForm.value.password) {
          callback(new Error("两次密码不一致!"));
        } else {
          callback();
        }
      },
      trigger: "blur",
    },
  ],
  email: [
    { required: true, message: "电子邮箱不能为空", trigger: "blur" },
    {
      type: "email",
      message: "请输入有效邮箱",
      trigger: ["blur", "change"],
    },
  ],
  birthday: [
    {
      required: true,
      message: "请选择生日",
      trigger: "change", // 日期选择器应该用 change 触发
    },
  ],
};
const loginrules = {
  username: [
    { required: true, message: "用户名不能为空", trigger: "blur" },
    { min: 3, max: 12, message: "长度在3-12个字符", trigger: "blur" },
  ],
  password: [
    { required: true, message: "密码不能为空", trigger: "blur" },
    { min: 6, message: "密码至少6位", trigger: "blur" },
  ],
};
const changepasswordRule = {
  username: [
    { required: true, message: "用户名不能为空", trigger: "blur" },
    { min: 3, max: 12, message: "长度在3-12个字符", trigger: "blur" },
  ],
  password: [
    { required: true, message: "密码不能为空", trigger: "blur" },
    { min: 6, message: "密码至少6位", trigger: "blur" },
  ],
  newPassword: [
    { required: true, message: "密码不能为空", trigger: "blur" },
    { min: 6, message: "密码至少6位", trigger: "blur" },
    {
      //validator验证新规则函数
      validator: (rule, value, callback) => {
        //当前值是newpassword rule不用但是可以获取字段值， callback必须用 决定效益通过
        if (value === passwordForm.value.password)
          //
          callback(new Error("两次密码一致!")); //报错
        else {
          callback(); //表示效验通过
        }
      },
      trigger: "blur",
    },
  ],
  confirmNewPassword: [
    { required: true, message: "确认密码不能为空", trigger: "blur" },
    {
      validator: (rule, value, callback) => {
        if (value !== passwordForm.value.newPassword) {
          callback(new Error("两次密码不一致!"));
        } else {
          callback();
        }
      },
      trigger: "blur",
    },
  ],
};
//注册表单提交
const authors = ref([]);
const submitRegister = () => {
  if (
    registerForm.value.username === "" ||
    registerForm.value.password === "" ||
    registerForm.value.confirmPassword === "" ||
    registerForm.value.birthday === "" ||
    registerForm.value.email === ""
  ) {
    alert("缺失字段");
    return;
  }
  if (registerForm.value.password !== registerForm.value.confirmPassword) {
    alert("密码与确认密码不一致！");
    return;
  }
  registerForm.value.birthday = "2025-05-08";
  axios
    .post("http://localhost:8080/api/register", null, {
      params: {
        username: registerForm.value.username,
        password: registerForm.value.password,
        confirmPassword: registerForm.value.confirmPassword,
        birthday: registerForm.value.birthday,
        email: registerForm.value.email,
        url: imageUrl.value,
      },
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
    })
    .then((response) => {
      if (response.data.success) {
        alert(
          response.data.message + " " + "用户：" + registerForm.value.username
        );
        return axios.post("http://localhost:8080/arc/getarc", null, {
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
          },
        });
      }
      throw new Error("注册失败");
    })
    .then((response) => {
      authors.value = response.data;
      // 返回添加作者的Promise
      return axios.post("http://localhost:8080/arc/add", null, {
        params: {
          id:
            authors.value.length > 0
              ? Math.max(...authors.value.map((item) => item.id)) + 1
              : 1,
          username: registerForm.value.username,
          number: 0,
        },
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
          Accept: "application/json",
        },
      });
    })
    .then((response) => {
      if (!response.data) {
        throw new Error("插入失败");
      }
      console.log(response.data.message);
      loadingDialog.value = false; // 关闭弹窗
      registerForm.value.username = "";
      registerForm.value.password = "";
      registerForm.value.confirmPassword = "";
      registerForm.value.birthday = "";
      registerForm.value.email = "";
      registerForm.value.url = "";
      imageUrl.value = "";
    })
    //出现请求超时或者中断
    .catch((error) => {
      alert("注册失败，请重试");
      console.error("注册错误:", error.message);
      console.log("响应:", error.response);
      console.log("请求:", error.request);
      console.log("配置:", error.config);
    });
};
// 提交登录表单

const submitLogin = () => {
  axios
    .post("http://localhost:8080/api/login", null, {
      params: {
        username: loginForm.value.username,
        password: loginForm.value.password,
      },

      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
        Accept: "application/json",
      },
    })
    .then((response) => {
      if (response.data.success) {
        console.log(response.data.message);
        alert(
          response.data.message + " " + "用户：" + loginForm.value.username
        );
        store.dispatch("setUser", {
          username: loginForm.value.username,
          email: response.data.email,
          birthday: response.data.birthday,
          url: response.data.url,
        });
        loadingDialog2.value = false; // 提交后关闭弹窗
        router.push("/main");
        loginForm.value.username = "";
        loginForm.value.password = "";
      } else {
        alert(response.data.message);
        loginForm.value.username = "";
        loginForm.value.password = "";
      }
    })
    .catch((error) => {
      alert("登录失败，请重试");
      console.error("登录错误:", error);
      loginForm.value.username = "";
      loginForm.value.password = "";
    });
};
// 提交修改密码表单
const submitChangePassword = () => {
  if (
    passwordForm.value.newPassword !== passwordForm.value.confirmNewPassword
  ) {
    alert("新密码和确认密码不一致");
    return;
  }
  axios
    .post("http://localhost:8080/api/changePassword", null, {
      params: {
        username: passwordForm.value.username,
        oldpassword: passwordForm.value.password,
        newpassword: passwordForm.value.newPassword,
      },
      headers: {
        "Content-Type": "application/x-www-form-urlencoded", //表单数据格式
        Accept: "application/json", //返回json数据
      },
    })
    .then((response) => {
      if (response.data.success) {
        console.log("修改密码成功", response.data);
        alert(
          response.data.message + " " + "用户：" + passwordForm.value.username
        );
        loadingDialog3.value = false; // 关闭弹窗
        passwordForm.value.username = "";
        passwordForm.value.password = "";
        passwordForm.value.newPassword = "";
        passwordForm.value.confirmNewPassword = "";
      } else {
        console.log("修改密码失败", response.data);
        alert(response.data.message + "用户：" + passwordForm.value.username);
        passwordForm.value.username = "";
        passwordForm.value.password = "";
        passwordForm.value.newPassword = "";
        passwordForm.value.confirmNewPassword = "";
      }
    })
    .catch((error) => {
      alert("修改密码失败，请重试");
      console.error("登录错误:", error);
      passwordForm.value.username = "";
      passwordForm.value.password = "";
      passwordForm.value.newPassword = "";
      passwordForm.value.confirmNewPassword = "";
    });
};
const openRegisterDialog = () => {
  loadingDialog.value = true;
};

// 打开登录弹窗
const openLoginDialog = () => {
  loadingDialog2.value = true;
};
const openChangePasswordDialog = () => {
  loadingDialog3.value = true;
};
</script>

<style scoped>
* {
  padding: 0;
  margin: 0;
}
#app {
  font-family: "Arial", sans-serif;
}
/**背景轮播图 */
.background-slideshow {
  position: fixed;
  width: 100%;
  height: 100%;
  z-index: 0;
}
.slide {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
  background-size: cover;
  background-position: center;
  transition: opacity 0.8s ease-in-out;
  transform: scale(1);
  z-index: 0;
}
.slide.active {
  opacity: 1;
  z-index: 1;
  animation: zoomIn 8s linear forwards;
}
/**登陆界面 */
.main-content {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  color: white;
  text-align: center;
  z-index: 2;
  position: relative;
}

.hero-section {
  max-width: 900px;
}

.title {
  font-size: 100px;
  font-weight: bold;
  background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
  background-size: 400% 400%;
  animation: gradient 15s ease infinite;
  -webkit-background-clip: text;
  color: transparent;
}

.subtitle {
  font-size: 20px;
  margin-bottom: 70px;
  background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
  background-size: 400% 400%;
  animation: gradient 15s ease infinite;
  -webkit-background-clip: text;
  color: transparent;
}
.subtitles {
  font-size: 70px;
  margin: 50px 0;
}

.description {
  font-size: 20px;
  margin: 5px 0;
}

.buttons {
  margin-top: 40px;
  display: flex;
  justify-content: center;
  flex-direction: row;
  gap: 15px;
}

.btn {
  /* 初始状态 */
  margin: 10px 0;
  color: #dadbe0;
  background: linear-gradient(-45deg, transparent, transparent);
  background-size: 200% 200%;
  background-position: right top;
  transition: all 0.2s ease;
  position: relative;
  overflow: hidden;
  z-index: 1;
  width: 50%;
  height: 70px;
  font-size: 20px;
  font-weight: bold;
  opacity: 0.75;
}

.btn::before {
  content: "";
  position: absolute;
  left: -200%;
  top: 100%;
  width: 200%;
  height: 200%;
  background: white;
  transform: rotate(45deg); /* 关键：45度倾斜 */
  opacity: 1;
  z-index: -1;
  transition: all 0.2s ease;
}

.btn:hover {
  color: #2a639b; /* 蓝色字体 */
  opacity: 1;
}

.btn:hover::before {
  left: 0;
  top: 0;
}
.bottom {
  margin-top: 60px;
  display: flex;
  align-items: center;
  flex-direction: column;
}
.bottoms {
  width: 60%;
  font-size: 10px;
}
/** 框 */
.dialog {
  padding: 30px 10px;
}
.main-dialog {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}
.box {
  width: 50%;
  padding: 10px 40px 10px 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  border-right: 3px dashed gray;
}
.main-dialog up {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.avatar-uploader,
.avatar {
  width: 200px;
  height: 200px;
  margin-bottom: 10px;
  display: block;
}
.avatar-uploader,
.el-upload:hover {
  border-color: var(--el-color-primary);
}
.avatar-uploader,
.el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}
.el-icon.avatar-uploader-icon {
  font-size: 14px;
  color: #8c939d;
  width: 200px;
  height: 200px;
  text-align: center;
}
.register-input {
  width: 50%;
  padding: 10px 20px 10px 40px;
  display: flex;
  flex-direction: column;
}
.box .imgupld-up {
  color: #bc4372;
  margin-top: 5px;
  margin-bottom: 30px;
}
.tt {
  border-top: 1px dashed var(--el-border-color);
  font-size: 12px;
  color: gray;
}
.imgupld {
  font-size: 15px;
  margin-top: 5px;
  margin-bottom: 15px;
}
.label1 {
  font-weight: 600;
  font-size: 20px;
}
.dialog-footer {
  display: flex;
  text-align: center;
  width: 100%;
  gap: 15px;
  flex-direction: column;
}
.dialog-footer .el-button {
  width: 100%;
  border-radius: 10px;
  margin: 0;
}

.el-form-item {
  margin-bottom: 20px;
}

.el-input {
  width: 100%;
}

.el-date-picker {
  width: 100%;
}
.dialog-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0; /* 移除内边距 */
}

.el-icon-close {
  cursor: pointer;
  font-size: 20px;
  color: #909399;
}

.el-icon-close:hover {
  color: #f56c6c; /* 鼠标悬停时的颜色 */
}
/** login*/
.login {
  display: flex;
  flex-direction: row;
  padding: 20px;
}
.left-login {
  padding: 20px 40px;
  display: flex;
  width: 50%;
  gap: 20px;
  flex-direction: column;
  align-items: center;
  border-right: 3px dashed gray;
}
.left-login .imgupld-up {
  color: #bc4372;
  text-align: center;
  margin-bottom: 10px;
}
.login-input {
  width: 50%;
  padding: 20px 20px;
  display: flex;
  flex-direction: column;
}

.other {
  background-color: white;
  padding: 10px 0px;
  width: 100%;
  margin: 0;
  border-radius: 10px;
  font-weight: bold;
  color: black;
}
.other img {
  position: relative;

  height: 15px;
  width: 15px;
}
.other .qq {
  right: 10px;
  height: 23px;
  width: 15px;
}
.other .fb {
  right: 17px;
}
.other .ge {
  right: 25px;
}
/* .box a.other img.fb {
  position: absolute;
  left: 28px;
  top: 15%;
  height: 20px;
}
.box a.other img.gg {
  position: absolute;
  left: 28px;
  top: 15%;
  height: 20px;
} */
/**渐变布局 */
@keyframes gradient {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}
@keyframes zoomIn {
  from {
    transform: scale(1);
  }
  to {
    transform: scale(1.04); /* 放大8% */
  }
}
@media (max-width: 800px) {
  .title {
    font-size: 60px;
  }
  .subtitle {
    font-size: 10px;
  }
  .subtitles {
    font-size: 35px;
    margin: 30px 0;
  }

  .description {
    font-size: 10px;
    margin: 5px 0;
  }

  .buttons {
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .btn {
    width: 70%;
  }
  .main-dialog {
    flex-direction: column;
  }
  .box {
    width: 100%;
    border-bottom: 3px dashed gray;
    border-right: 0px;

    padding: 10px 0px;
  }

  .register-input {
    width: 100%;
    padding: 20px 0px;
  }
  .login {
    flex-direction: column;
    padding: 5px;
  }
  .left-login {
    padding: 20px 0;
    width: 100%;
    border-right: 0px;
    border-bottom: 3px dashed gray;
  }
  .login-input {
    width: 100%;
    padding: 20px 0;
  }
}
@media (max-width: 1200px) {
  .login {
    flex-direction: column;
    padding: 5px;
  }
  .left-login {
    padding: 20px 0;
    width: 100%;
    border-right: 0px;
    border-bottom: 3px dashed gray;
  }
  .login-input {
    width: 100%;
    padding: 20px 0;
  }
}

/**弹窗 */
</style>
