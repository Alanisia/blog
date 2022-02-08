<template>
  <div>
    <el-descriptions title="用户信息">
      <el-descriptions-item label="ID">{{account.id}}</el-descriptions-item>
      <el-descriptions-item label="邮箱">{{account.email}}</el-descriptions-item>
      <el-descriptions-item label="用户名">{{account.username}}</el-descriptions-item>
      <el-descriptions-item label="性别">{{account.gender === 'male' ? '男' : '女'}}</el-descriptions-item>
      <el-descriptions-item label="发表博文">{{account.publish}}</el-descriptions-item>
      <el-descriptions-item label="收藏博文">{{account.star}}</el-descriptions-item>
    </el-descriptions>
    <el-button type="primary" @click="modify">修改个人信息</el-button>
    <info-edit ref="infoEdit" :account="account"/>
  </div>
</template>

<script>
import axios from "axios";
import util from '../../../util';
import InfoEdit from './InfoEdit.vue';
export default {
  components: { InfoEdit },
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
      const id = util.getCurrentUser();
      axios.get(`/info/${id}`).then((res) => {
        const data = res.data.data;
        this.account.id = data.id;
        this.account.email = data.email;
        this.account.username = data.username;
        this.account.gender = data.gender;
        this.account.avatar = data.avatar;
        this.account.publish = data.publish;
        this.account.star = data.star;
      });
    },
    modify: function() {
      this.$refs.infoEdit.dialogVisible = true;
    }
  },
};
</script>

<style scoped>
</style>
