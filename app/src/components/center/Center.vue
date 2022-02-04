<template>
  <el-container>
    <el-aside>
        <el-menu style="width: 200px;">
            <el-menu-item index="info">个人信息</el-menu-item>
            <el-menu-item index="blog">博文管理</el-menu-item>
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