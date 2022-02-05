<template>
  <el-container>
    <el-aside>
      <el-menu style="width: 200px" router>
        <el-menu-item index="info">个人信息</el-menu-item>
        <el-submenu index="">
          <template slot="title"><span>博文管理</span></template>
          <el-menu-item index="history">博文浏览历史</el-menu-item>
          <el-menu-item index="star">博文收藏</el-menu-item>
          <el-menu-item index="mine">我发布的博文</el-menu-item>
          <el-menu-item index="draft">博文草稿箱</el-menu-item>
        </el-submenu>
        <el-menu-item index="message">消息管理</el-menu-item>
      </el-menu>
    </el-aside>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: "Center",
};
</script>

<style>
</style>