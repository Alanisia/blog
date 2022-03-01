<template>
  <el-dialog>
    <el-form :model="replyForm" :rules="rules">
      <el-form-item prop="reply">
        <el-input
          type="textarea"
          maxlength="500"
          minlength="1"
          v-model="replyForm.content"
          placeholder="请输入回复内容"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="reply">回复</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
export default {
  name: "ReplyDialog",
  props: ["comment"],
  data() {
    return {
      dialogVisible: false,
      replyForm: {
        content: "",
      },
      rules: {
        reply: [
          {
            validator: (rule, value, callback) => {
              if ("" === value) callback(new Error("请输入回复！"));
              else callback();
            },
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    reply: function () {},
  },
};
</script>

<style>
</style>