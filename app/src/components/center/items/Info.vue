<template>
  <div>
    <el-descriptions title="用户信息">

      <el-descriptions-item label="ID"></el-descriptions-item>
      <el-descriptions-item label="邮箱"></el-descriptions-item>
      <el-descriptions-item label="用户名"></el-descriptions-item>
      <el-descriptions-item label="性别"></el-descriptions-item>
    </el-descriptions>
  </div>
</template>

<script>
export default {
  name: "Info",
  data() {
    return {
      account: {},
    };
  },
  methods: {
      loadInfo: function() {}
  }
};
</script>

<style scoped>
</style>