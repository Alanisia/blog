<template>
  <div id="register">
    <el-form
      :rules="rules"
      :model="registerForm"
      label-width="80px"
      status-icon
      ref="registerForm"
    >
      <legend style="text-align: center; margin-bottom: 30px">
        <strong>用户注册</strong>
      </legend>
      <el-form-item label="邮箱" prop="email">
        <el-input
          v-model="registerForm.email"
          placeholder="请输入邮箱"
        ></el-input>
      </el-form-item>
      <el-form-item label="用户名" prop="username">
        <el-input
          v-model="registerForm.username"
          placeholder="请输入用户名"
        ></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          v-model="registerForm.password"
          placeholder="请输入密码"
          show-password
        ></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPwd">
        <el-input
          v-model="registerForm.checkPwd"
          placeholder="请确认密码"
          show-password
        ></el-input>
      </el-form-item>
      <el-form-item label="验证码" prop="captchaCode">
        <el-input v-model="registerForm.captchaCode" placeholder="请输入验证码">
          <el-image
            slot="append"
            :src="registerForm.captchaImage"
            @click="updateCaptcha"
          />
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="register"> 注册 </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from "axios";
import md5 from 'blueimp-md5';
import util from "@/util";

export default {
  name: "Register",
  data() {
    return {
      registerForm: {
        email: "",
        username: "",
        password: "",
        checkPwd: "",
        captchaCode: "",
        captchaImage: "",
      },
      rules: {
        email: [
          {
            validator: (rule, value, callback) => {
              if ("" === value) callback(new Error("请输入注册邮箱！"));
              else if (!util.isEmail(value))
                callback(new Error("邮箱格式非法！"));
              else callback();
            },
            trigger: "blur",
          },
        ],
        username: [
          {
            validator: (rule, value, callback) => {
              if ("" === value) callback(new Error("请输入注册用户名！"));
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
                callback(new Error("密码格式错误！"));
              else callback();
            },
            trigger: "blur",
          },
        ],
        checkPwd: [
          {
            validator: (rule, value, callback) => {
              if ("" === value) callback(new Error("请确认密码！"));
              else if (value !== this.registerForm.password)
                callback(new Error("两次密码不一致！"));
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
    register: function () {
      this.$refs.registerForm.validate((valid) => {
        if (!valid) return false;
        else {
          axios
            .post("/register", {
              'email': this.registerForm.email,
              'username': this.registerForm.username,
              'password': md5(this.registerForm.password),
              'captchaCode': this.registerForm.captchaCode,
              'captchaImage': this.registerForm.captchaImage
            })
            .then((res) => {
              const data = res.data;
              if (!data.code) {
                this.$router.push("/login");
                return true;
              } else {
                this.$message({
                  message: data.message,
                  type: 'error',
                });
                return false;
              }
            });
        }
      });
    },
    updateCaptcha: function () {
      axios
      .get('/captcha')
      .then(res => {
        const data = res.data;
        this.registerForm.captchaImage = `data:image/jpg;base64,${data}`;
      });
    },
  },
};
</script>

<style scoped>
#register .el-form {
  width: 600px;
  margin-left: auto;
  margin-right: auto;
  position: relative;
  padding: 50px;
  border: 1px solid #409eff;
  border-radius: 30px;
}
</style>
