<template>
  <el-aside>
    <el-descriptions title="站点信息">
      <el-descriptions-item label="本站博文">{{ blogs }}</el-descriptions-item>
      <el-descriptions-item label="本站会员">{{
        members
      }}</el-descriptions-item>
      <el-descriptions-item label="日浏览量">{{ day }}</el-descriptions-item>
      <el-descriptions-item label="总浏览量">{{ total }}</el-descriptions-item>
    </el-descriptions>
  </el-aside>
</template>

<script>
import axios from 'axios';
export default {
  name: "SystemInfo",
  data() {
    return {
      blogs: 0,
      members: 0,
      day: 0,
      total: 0,
    };
  },
  created() {
    axios.get('/system/info');
  }
};
</script>

<style scoped>
</style>
