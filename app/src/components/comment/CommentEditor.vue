<template>
  <div>
    <el-form ref="commentForm" :model="commentForm">
      <el-form-item>
        <editor ref="contentEditor" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary">发表评论（字数500以内）</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from 'axios';
import Editor from "../common/CommonEditor.vue";
import util from '../../util';

export default {
  components: { Editor },
  name: "CommentEditor",
  data() {
    return {
      commentForm: {
        comment: this.$refs.contentEditor.content,
      },
    };
  },
  methods: {
    publish: function () {
      axios.post("/comment", {
        accountId: util.getCurrentUser(),
        blogId: null,
      }).then(res => {
        const data = res.data;
        if (!data.code) {
          this.$message({
            message: "评论成功",
            type: "success"
          });
        } else {
          this.$message({
            message: `评论失败，错误码: ${data.code}`,
            type: "error"
          });
        }
      });
    }
  },
};
</script>

<style>
</style>