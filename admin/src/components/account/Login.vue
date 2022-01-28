<template>
  <div>
    <el-form
      :rules="rules"
      label-width="120px"
      :model="loginForm"
      ref="loginForm"
    >
      <el-form-item label="Account">
        <el-input
          placeholder="Please input your admin account"
          v-model="loginForm.account"
        ></el-input>
      </el-form-item>
      <el-form-item label="Password">
        <el-input
          placeholder="Please input your password"
          show-password
          v-model="loginForm.password"
        ></el-input>
      </el-form-item>
      <el-form-item label="Captcha Code">
        <el-input
          placeholder="Please input captcha code"
          v-model="loginForm.captchaCode"
        >
          <el-image
            slot="append"
            :src="loginForm.captchaImage"
            @click="updateCaptcha"
          ></el-image>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="login" type="primary">Login</el-button>
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
        captchaImage: "",
        captchaCode: "",
      },
      rules: {
        account: [
          {
            validator: (rule, value, callback) => {
              if ("" === value)
                callback(new Error("Please enter your account!"));
              // else {
              //   if (!utils.isEmail(this.login.account))
              //     callback(new Error(""));
              else callback();
              // }
            },
            trigger: "blur",
          },
        ],
        password: [
          {
            validator: (rule, value, callback) => {
              if ("" === value)
                callback(new Error("Please enter your password!"));
              else {
                if (!util.isPassword(value))
                  callback(new Error("Password invalid!"));
                else callback();
              }
            },
            trigger: "blur",
          },
        ],
        captchaCode: [
          {
            validator: (rule, value, callback) => {
              if (value === "")
                callback(new Error("Please enter captcha code!"));
              else callback();
            },
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    updateCaptcha: function () {},
    login: function () {},
  },
};
</script>

<style scoped>
.el-form {
  width: 600px;
  margin-left: auto;
  margin-right: auto;
  position: relative;
  top: 200px;
  padding: 50px;
  border: 1px solid #409eff;
  border-radius: 30px;
}
</style>
