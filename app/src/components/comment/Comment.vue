<template>
  <div class="comment">
    <p style="color: #409eff;"><strong>{{ username }}</strong></p>
    <p v-html="content"></p>
    <p>
      <el-button size="mini" @click="reply">回复</el-button>
      <el-button size="mini" @click="iLike">点赞 {{ like }}</el-button>
      <el-button size="mini" type="danger" @click="remove" v-if="currentUser === this.$props.comment.accountId">
        删除
      </el-button> 
      <span style="margin: auto 30px;">更新时间：{{ time }}</span>
      <span @click="showReplies" v-if="replies.length > 0" style="float: right;">
        展开全部{{ replies.length }}条回复 <i class="el-icon-caret-left"></i>
      </span>
    </p>
    <reply-dialog ref="replyDialog" :comment="this.$props.comment"/>
  </div>
</template>

<script>
import { marked } from "marked";
import ReplyDialog from './ReplyDialog.vue';
import axios from 'axios';
import util from '../../util';
export default {
  components: { ReplyDialog },
  name: "Comment",
  props: ['comment'],
  data() {
    return {
      currentUser: util.getCurrentUser(),
      username: this.$props.comment.commenter,
      content: marked(this.$props.comment.content),
      like: this.$props.comment.like,
      time: this.$props.comment.createAt,
      id: this.$props.comment.id,
      replies: [],
      replyHasExpended: false
    };
  },
  created() {
    console.log(this.$props.comment);
    this.loadReplies();
  },
  methods: {
    showReplies: function () {
      this.replyHasExpended = !this.replyHasExpended;
    },
    loadReplies: function () {
      axios.get(`/replies?commentId=${this.id}`).then(res => {
        const data = res.data.data;
        this.replies = data;
      });
    },
    iLike: function() {},
    reply: function() {
      this.$refs.replyDialog.dialogVisible = true;
    },
    remove: function () {}
  },
};
</script>

<style scoped>
.comment {
  border: 1px solid #409EFF;
  padding: 10px;
}
</style>