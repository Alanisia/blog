<template>
  <div class="comment">
    <div>
      <p style="color: #409eff">
        <strong>{{ username }}</strong>
      </p>
      <p v-html="content"></p>
      <p>
        <el-button
          size="mini"
          type="danger"
          @click="remove"
          v-if="currentUser === this.$props.comment.accountId"
        >
          删除
        </el-button>
        <el-button size="mini" @click="reply">回复</el-button>
        <el-button
          size="mini"
          @click="iLike"
          :style="liked ? 'background-color: #8ac4ff' : ''"
          >点赞 {{ like }}</el-button
        >
        <span style="margin: auto 30px">更新时间：{{ time }}</span>
        <el-button
          @click="showReplies"
          v-if="replies.length > 0"
          style="float: right"
          size="mini"
        >
          展开全部{{ replies.length }}条回复
          <i
            :class="
              replyHasExpended ? 'el-icon-caret-bottom' : 'el-icon-caret-left'
            "
          ></i>
        </el-button>
      </p>
    </div>
    <div v-if="replyHasExpended">
      <reply v-for="reply in replies" :key="reply.id" :reply="reply"></reply>
    </div>
    <reply-dialog ref="replyDialog" :comment="this.$props.comment" type="COMMENT"/>
  </div>
</template>

<script>
import { marked } from "marked";
import ReplyDialog from "./ReplyDialog.vue";
import axios from "axios";
import util from "../../util";
import Reply from "./Reply.vue";
export default {
  components: { ReplyDialog, Reply },
  name: "Comment",
  props: ["comment"],
  data() {
    return {
      currentUser: util.getCurrentUser(),
      username: this.$props.comment.commenter,
      content: marked(this.$props.comment.content),
      like: this.$props.comment.like,
      time: util.dateFormat(this.$props.comment.createAt),
      id: this.$props.comment.id,
      replies: [],
      replyHasExpended: false,
      liked: false,
    };
  },
  created() {
    this.loadLiked();
    this.loadReplies();
  },
  methods: {
    showReplies: function () {
      this.replyHasExpended = !this.replyHasExpended;
    },
    loadReplies: function () {
      axios.get(`/replies?commentId=${this.id}`).then((res) => {
        const data = res.data.data;
        this.replies = data;
      });
    },
    loadLiked: function () {
      if (util.getToken()) {
        axios
          .get(
            `/comment/liked?accountId=${this.currentUser}&commentId=${this.id}`
          )
          .then((res) => (this.liked = res.data.data.liked));
      }
    },
    iLike: function () {
      if (!util.getToken()) {
        this.$message(util.error("未登录，请先登录！"));
        this.$router.push("/login");
      } else {
        if (this.liked) {
          axios
            .post("/comment/like/cancel", {
              accountId: this.currentUser,
              commentId: this.id,
            })
            .then((res) => {
              const data = res.data;
              if (!data.code) {
                this.like--;
                this.liked = false;
                this.$message(util.success("取消点赞成功！"));
              } else if (data.code === util.result.AUTHORIZE_FAILED) {
                this.$message(util.error("令牌已过期，请重新登录！"));
                this.$router.push("/login");
              } else
                this.$message(util.error(`取消点赞失败，错误码：${data.code}`));
            });
        } else {
          axios
            .post("/comment/like", {
              accountId: this.currentUser,
              commentId: this.id,
            })
            .then((res) => {
              const data = res.data;
              if (!data.code) {
                this.like++;
                this.liked = true;
                this.$message(util.success("点赞成功！"));
              } else if (data.code === util.result.AUTHORIZE_FAILED) {
                this.$message(util.error("令牌已过期，请重新登录！"));
                this.$router.push("/login");
              } else
                this.$message(util.error(`点赞失败，错误码：${data.code}`));
            });
        }
      }
    },
    reply: function () {
      this.$refs.replyDialog.dialogVisible = true;
    },
    remove: function () {
      this.$confirm("确定删除此评论?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          axios
            .post("/comment/delete", {
              blogId: this.$props.comment.blogId,
              commentId: this.id,
              replyId: 0,
            })
            .then((res) => {
              const data = res.data;
              if (!data.code) {
                this.$message(util.success("删除成功"));
                this.$parent.loadComments();
              } else
                this.$message(util.error(`删除失败，错误码：${data.code}`));
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
  },
};
</script>

<style scoped>
.comment {
  border: 1px solid #409eff;
  padding: 10px;
}
</style>