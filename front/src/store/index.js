import { createStore } from "vuex";
export default createStore({
  //数据，相当于data
  state: {
    user: {
      username: null,
      email: null,
      birthday: null,
      url: null,
    },
  },
  //从state获取用户信息
  getters: {
    getUser(state) {
      return state.user; // 获取用户信息
    },
  },
  //里面定义方法，操作state方法
  mutations: {
    setUser(state, userData) {
      state.user = {
        username: userData.username,
        email: userData.email,
        birthday: userData.birthday,
        url: userData.url,
      };
    },
    clearUser(state) {
      state.user = {
        username: null,
        email: null,
        birthday: null,
        url: null,
      };
    },
  },
  // 操作异步操作mutation
  actions: {
    setUser({ commit }, userData) {
      commit("setUser", userData); // 提交 mutation 更新用户信息
    },
  },
  modules: {},
});
