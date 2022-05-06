<template>
  <el-menu mode="horizontal" router>
    <el-menu-item index="/">首页</el-menu-item>
    <el-menu-item index="/categories">分类</el-menu-item>
    <el-menu-item index="/search">搜索</el-menu-item>
    <template v-if="hasLogin">
      <el-menu-item index="/login" style="float: right" @click="logout">
        退出登录
      </el-menu-item>
      <el-menu-item index="/center" style="float: right">个人中心</el-menu-item>
    </template>
    <template v-else>
      <el-menu-item index="/login" style="float: right">登录</el-menu-item>
      <el-menu-item index="/register" style="float: right">注册</el-menu-item>
    </template>
  </el-menu>
</template>

<script>
import axios from "axios";
import util from "../../util";
export default {
  name: "Navbar",
  data() {
    return {
      categories: [],
      hasLogin: !!util.getToken(),
    };
  },
  methods: {
    logout: function () {
      axios
        .post("/logout", {
          id: util.getCurrentUser(),
        })
        .then((res) => {
          const data = res.data;
          if (!data.code) {
            util.setToken("");
            util.setCurrentUser("");
            util.success("登出成功！");
          } else {
            util.error("登出失败！");
          }
        })
        .catch((e) => {
          util.error("登出失败！");
          console.err(e);
        });
    },
  },
};
</script>

<style>
</style>
