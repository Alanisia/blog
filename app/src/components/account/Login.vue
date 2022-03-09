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
        <el-input v-model="loginForm.captchaCode" placeholder="请输入验证码（三分钟内有效）">
          <el-image
            slot="append"
            style="width: 56px"
            @click="updateCaptcha"
            :src="sourceImage"
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
import util from "../../util";
import axios from "axios";
import md5 from "blueimp-md5";

export default {
  name: "Login",
  data() {
    return {
      sourceImage: "",
      captchaImage: "",
      loginForm: {
        email: "",
        password: "",
        captchaCode: "",
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
  created() {
    this.updateCaptcha();
  },
  methods: {
    login: function () {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          axios
            .post("/login", {
              email: this.loginForm.email,
              password: md5(this.loginForm.password),
              captchaCode: this.loginForm.captchaCode,
              captchaImage: this.captchaImage,
            })
            .then((res) => {
              const data = res.data;
              if (!data.code) {
                this.$message(util.success("登录成功！"));
                util.setToken(data.data.token);
                util.setCurrentUser(data.data.accountId);
                this.$router.push("/");
                return true;
              } else {
                switch (data.code) {
                  case util.result.ACCOUNT_NOT_FOUND: {
                    this.$message(util.error("账户不存在！"));
                    break;
                  }
                  case util.result.PASSWORD_INCORRECT: {
                    this.$message(util.error("密码错误！"));
                    break;
                  }
                  case util.result.CAPTCHA_ERROR: {
                    this.$message(util.error("验证码错误，或者验证码已过期！"));
                    this.updateCaptcha();
                    break;
                  }
                  default: {
                    this.$message(util.error(`未知错误，错误码：${data.code}`));
                    break;
                  }
                }
                return false;
              }
            });
        } else return false;
      });
    },
    updateCaptcha: function () {
      axios
        .post("/captcha", { captchaImage: this.captchaImage })
        .then((res) => {
          const data = res.data;
          this.captchaImage = data.data;
          this.sourceImage = `data:image/jpg;base64,${data.data}`;
        });
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
