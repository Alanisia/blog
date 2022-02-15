<template>
  <el-dialog>
    <el-form :model="replyForm" :rules="rules">
      <el-form-item>
        <el-input
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
      rules: {},
    };
  },
  methods: {
    reply: function () {},
  },
};
</script>

<style>
</style>