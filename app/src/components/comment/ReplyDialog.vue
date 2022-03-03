<template>
  <el-dialog :visible.sync="dialogVisible" title="回复评论" id="reply-dialog">
    <p style="margin-top: 0">回复{{ this.$props.comment.commenter }}</p>
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
import axios from 'axios';
import util from '../../util';
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
    reply: function () {
      const comment = this.$props.comment;
      axios.post("/reply", {
        accountId: util.getCurrentUser(),
        blogId: comment.blogId,
        commentId: comment.id,
        targetId: comment.accountId,
        content: this.replyForm.content,
      }).then(res => {
        const data = res.data;
        if (!data.code) {
          this.$message(util.success("回复成功！"));
          this.dialogVisible = false;
        } else {
          this.$message(util.error(`回复失败，错误码：${data.code}`));
        }
      })
    },
  },
};
</script>

<style>
#reply-dialog .el-dialog__body {
  padding: 10px 20px;
}
</style>