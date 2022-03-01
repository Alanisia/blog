<template>
  <div class="comment">
    <p style="color: #409eff;"><strong>{{ username }}</strong></p>
    <p v-html="content"></p>
    <p>
      <el-button type="mini" @click="reply">回复</el-button>
      <el-button type="mini" @click="iLike">点赞 {{ like }}</el-button>
      <span style="margin: auto 30px;">更新时间：{{ time }}</span>
      <span @click="showReplies" v-if="replies > 0" style="float: right;">
        展开全部{{ replies }}条回复
      </span>
    </p>
  </div>
</template>

<script>
import { marked } from "marked";
export default {
  name: "Comment",
  props: ['comment'],
  data() {
    return {
      username: this.$props.comment.commenter,
      content: marked(this.$props.comment.content),
      like: this.$props.comment.like,
      time: this.$props.comment.createAt,
      id: this.$props.comment.id,
      replies: 0,
    };
  },
  methods: {
    showReplies: function () {},
    iLike: function() {},
    reply: function() {}
  },
};
</script>

<style scoped>
.comment {
  border: 1px solid #409EFF;
  padding: 10px;
}
</style>