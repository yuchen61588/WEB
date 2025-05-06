import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "/",
    redirect: "/loginView",
  },
  {
    path: "/loginView",
    name: "login",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/loginView.vue"), //基于router目录出发
  },
  {
    path: "/main",
    name: "main",
    component: () => import("@/views/main.vue"), //基于scr目录出发
    children: [
      {
        path: "",
        redirect: "/main/home",
      },
      {
        path: "home",
        name: "home",
        component: () => import("@/views/home.vue"),
        meta: { title: "首页" },
      },
      {
        path: "users",
        name: "users",
        component: () => import("@/views/users.vue"),
        meta: { title: "用户管理" },
      },
      {
        path: "profile",
        name: "profile",
        component: () => import("@/views/profile.vue"),
        meta: { title: "个人中心" },
      },
      {
        path: "articles",
        name: "articles",
        component: () => import("@/views/articles.vue"),
        meta: { title: "文章管理" },
      },
      {
        path: "articles_post",
        name: "articles_post",
        component: () => import("@/views/articles_post.vue"),
        meta: { title: "用户文章管理" },
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
