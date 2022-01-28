<template>
  <el-container>
    <el-aside style="width: 250px">
      <el-menu router class="left-menu">
      <div style="height: 60px"></div>
        <el-menu-item index="index"><span>Index</span></el-menu-item>
        <el-menu-item index="categories_and_tags"
          ><span>Categories & Tags</span></el-menu-item
        >
        <el-menu-item index="blogs"><span>Blogs</span></el-menu-item>
        <el-menu-item index="users"><span>Users</span></el-menu-item>
        <el-menu-item index="comments"><span>Comments</span></el-menu-item>
        <el-menu-item index="system"><span>System</span></el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header>
        <el-menu mode="horizontal" router>
          <el-menu-item @click="logout" style="float: right;"><span>Logout</span></el-menu-item>
        </el-menu>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "Admin",
  data() {
    return {};
  },
  methods: {
    logout: function () {},
  },
};
</script>

<style scoped>
.left-menu {
  height: 100vh;
}
.el-header {
  padding: 0;
}
</style>
