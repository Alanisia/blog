<template>
  <el-menu mode="horizontal" router>
    <span></span>

    <el-menu-item index="/">首页</el-menu-item>
    <template v-for="item in categories">
      <el-menu-item :index="item.route" :key="item.route">{{
        item.name
      }}</el-menu-item>
    </template>
    <template v-if="hasLogin">
      <el-menu-item index="login" style="float: right" @click="logout">退出登录</el-menu-item>
      <el-menu-item index="center" style="float: right">个人中心</el-menu-item>
    </template>
    <template v-else>
      <el-menu-item index="login" style="float: right">登录</el-menu-item>
      <el-menu-item index="register" style="float: right">注册</el-menu-item>
    </template>
  </el-menu>
</template>

<script>
import axios from 'axios';
import util from '../../util';
export default {
  name: "Navbar",
  data() {
    return {
      categories: [],
      hasLogin: localStorage.getItem(util.commonToken) !== ""
    };
  },
  created() {
    this.getCategories();
  },
  methods: {
    getCategories: function () {
      axios.get('/categories').then(res => {
        const data = res.data.data;
        this.categories = data;
      });
    },
    logout: function() {
      localStorage.setItem(util.commonToken, '');
    }
  },
};
</script>

<style>
</style>
