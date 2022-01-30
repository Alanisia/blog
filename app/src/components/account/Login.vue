<template>
  <div id="login">
    <el-form
      ref="loginForm"
      label-width="84px"
      :model="loginForm"
      status-icon
      :rules="rules"
    >
      <legend style="text-align: center; margin-bottom: 30px">
        <strong>用户登录</strong>
      </legend>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="loginForm.email" placeholder="请输入邮箱"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          v-model="loginForm.password"
          placeholder="请输入密码"
          show-password
        ></el-input>
      </el-form-item>
      <el-form-item label="验证码" prop="captchaCode">
        <el-input v-model="loginForm.captchaCode" placeholder="请输入验证码">
          <el-image
            slot="append"
            @click="updateCaptcha"
            :src="loginForm.captchaImage"
          ></el-image>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="login">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

                                                                                                                    <script>
import util from "@/util";

export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        account: "",
        password: "",
        captchaText: "",
        captchaImage: "",
      },
      rules: {
        email: [
          {
            validator: (rule, value, callback) => {
              if ("" === value) callback(new Error("请输入邮箱！"));
              else if (!util.isEmail(value))
                callback(new Error("邮箱格式非法！"));
              else callback();
            },
            trigger: "blur",
          },
        ],
        password: [
          {
            validator: (rule, value, callback) => {
              if ("" === value) callback(new Error("请输入密码！"));
              else if (!util.isPassword(value))
                callback(new Error("密码格式不正确！"));
              else callback();
            },
            trigger: "blur",
          },
        ],
        captchaCode: [
          {
            validator: (rule, value, callback) => {
              if ("" === value) callback(new Error("请输入验证码！"));
              else callback();
            },
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    login: function () {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.$router.push("/");
          return true;
        } else return false;
      });
    },
    updateCaptcha: function () {
      fetch("")
        .then((res) => res.json())
        .then((data) => {
          this.login.captchaCode = data.captchaCode;
          this.login.captchaImage = data.captchaImage;
        })
        .catch((err) => console.error(err));
    },
  },
};
</script>

<style scoped>
#login .el-form {
  width: 600px;
  margin-left: auto;
  margin-right: auto;
  position: relative;
  padding: 50px;
  border: 1px solid #409eff;
  border-radius: 30px;
}
</style>
