<template>
  <el-menu mode="horizontal" router>
    <span></span> <!-- TODO: logo -->
    <el-menu-item index="/">首页</el-menu-item>
    <el-menu-item index="/categories">分类</el-menu-item>
    <template v-if="hasLogin">
      <el-menu-item index="/login" style="float: right" @click="logout">退出登录</el-menu-item>
      <el-menu-item index="/center" style="float: right">个人中心</el-menu-item>
    </template>
    <template v-else>
      <el-menu-item index="/login" style="float: right">登录</el-menu-item>
      <el-menu-item index="/register" style="float: right">注册</el-menu-item>
    </template>
  </el-menu>
</template>

<script>
import util from '../../util';
export default {
  name: "Navbar",
  data() {
    return {
      categories: [],
      hasLogin: util.getToken() !== "" 
    };
  },
  methods: {
    logout: function() {
       util.setToken('');
    }
  },
};
</script>

<style>
</style>
