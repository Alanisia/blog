<template>
  <el-dialog :visible.sync="dialogVisible" title="修改个人信息">
    <el-form label-width="60px" :model="infoForm" ref="infoForm">
      <el-form-item label="邮箱">
        <el-input disabled :value="account.email"></el-input>
      </el-form-item>
      <el-form-item label="用户名">
        <el-input
          placeholder="修改用户名"
          v-model="infoForm.username"
        ></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio v-model="infoForm.gender" label="male">男</el-radio>
        <el-radio v-model="infoForm.gender" label="female">女</el-radio>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="modify">修改</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
import axios from "axios";
import util from "../../../util";
export default {
  name: "InfoEdit",
  props: ["account"],
  data() {
    return {
      infoForm: {
        username: this.$props.account.username,
        gender: this.$props.account.gender,
      },
      dialogVisible: false,
    };
  },
  methods: {
    modify: function () {
      const id = util.getCurrentUser();
      axios
        .post(`/info/modify/${id}`, {
          id: id,
          detail: {
            username: this.infoForm.username,
            gender: this.infoForm.gender,
          },
        })
        .then((res) => {
          const data = res.data;
          if (!data.code) {
            this.$message({
              message: "修改个人信息成功！",
              type: "success",
            });
            this.dialogVisible = false;
          } else {
            this.$message({
              message: `修改个人信息失败，错误码：${data.code}`,
              type: "error",
            });
          }
        });
    },
  },
};
</script>

<style>
</style>
