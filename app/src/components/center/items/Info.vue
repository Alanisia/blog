<template>
  <div>
    <el-descriptions title="用户信息">
      <el-descriptions-item label="ID"></el-descriptions-item>
      <el-descriptions-item label="邮箱"></el-descriptions-item>
      <el-descriptions-item label="用户名"></el-descriptions-item>
      <el-descriptions-item label="性别"></el-descriptions-item>
      <el-descriptions-item label="发表博文"></el-descriptions-item>
      <el-descriptions-item label="收藏博文"></el-descriptions-item>
    </el-descriptions>
    <el-button type="primary">修改个人信息</el-button>
  </div>
</template>

<script>
import axios from "axios";
import util from '../../../util';
export default {
  name: "Info",
  data() {
    return {
      account: {
        id: 0,
        email: "",
        username: "",
        gender: "",
        avatar: "",
        publish: 0,
        star: 0,
      },
    };
  },
  created() {
    this.loadInfo();
  },
  methods: {
    loadInfo: function () {
      const id = localStorage.getItem(util.currentUser);
      axios.get(`/info/${id}`).then((res) => {
        const data = res.data.data;
        this.account = data;
      });
    },
  },
};
</script>

<style scoped>
</style>