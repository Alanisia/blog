<template>
  <el-dialog :visible.sync="dialogVisible" title="回复评论" id="reply-dialog">
    <p style="margin-top: 0">回复{{ this.$props.comment.commenter }}（Markdown编辑器）</p>
    <el-form :model="replyForm" ref="replyForm">
      <el-form-item>
        <common-editor ref="commentEditor" />
      </el-form-item>
      <el-form-item>
        <el-button @click="display">演示</el-button>
        <el-button type="primary" @click="reply">回复</el-button>
      </el-form-item>
    </el-form>
    <display :displayVisible="false" ref="displayDialog" />
  </el-dialog>
</template>

<script>
import { marked } from "marked";
import axios from "axios";
import util from "../../util";
import CommonEditor from "../common/CommonEditor.vue";
import Display from "../editor/Display.vue";

export default {
  components: { CommonEditor, Display },
  name: "ReplyDialog",
  props: ["comment", "type"],
  data() {
    return {
      dialogVisible: false,
      replyForm: {
        mdText: "",
        mdHtml: "",
      }
    };
  },
  watch: {
    "replyForm.mdText": function () {
      this.replyForm.mdHtml = marked(this.replyForm.mdText);
    },
  },
  methods: {
    display: function () {
      this.replyForm.mdText = this.$refs.commentEditor.content;
      this.$refs.displayDialog.mdText = this.replyForm.mdText;
      this.$refs.displayDialog.displayVisible = true;
    },
    reply: function () {
      this.replyForm.mdText = this.$refs.commentEditor.content;
      const comment = this.$props.comment;
      const content = this.replyForm.mdText;
      if (!content) this.$message(util.error("评论不能为空！"));
      else if (!util.getToken()) {
        this.$message(util.error("未登录，请先登录！"));
        this.$router.push("/login");
      } else {
        axios
          .post("/reply", {
            accountId: util.getCurrentUser(),
            blogId: comment.blogId,
            commentId: comment.id,
            targetId: comment.accountId,
            content: this.replyForm.mdText,
          })
          .then((res) => {
            const data = res.data;
            if (!data.code) {
              this.$message(util.success("回复成功！"));
              const type = this.$props.type;
              if (type === "COMMENT") this.$parent.loadReplies();
              else if (type === "REPLY") this.$parent.$parent.loadReplies();
              this.dialogVisible = false;
            } else if (data.code === util.result.AUTHORIZE_FAILED) {
              this.$message(util.error("令牌已过期，请重新登录"));
              this.$router.push("/login");
            } else {
              this.$message(util.error(`回复失败，错误码：${data.code}`));
            }
          });
      }
    },
  },
};
</script>

<style>
#reply-dialog .el-dialog__body {
  padding: 10px 20px;
}
</style>