<template>
  <div>
    <p>{{ username }} 回复： {{ target }}</p>
    <p>{{ content }}</p>
    <p>
      <el-button
        size="mini"
        type="danger"
        @click="remove"
        v-if="currentUser === this.$props.reply.accountId"
      >
        删除
      </el-button>
      <el-button size="mini" @click="replyIt">回复</el-button>
      <el-button size="mini" @click="iLike" :style="liked ? 'background-color: #409EFF' : ''">点赞 {{ like }}</el-button>
      <span style="float: right">更新时间：{{ time }}</span>
    </p>
    <reply-dialog ref="replyDialog" :comment="this.$props.reply" />
  </div>
</template>

<script>
import axios from "axios";
import util from "../../util";
import ReplyDialog from "./ReplyDialog.vue";
export default {
  components: { ReplyDialog },
  name: "Reply",
  props: ["reply"],
  data() {
    return {
      currentUser: util.getCurrentUser(),
      username: this.$props.reply.commenter,
      target: this.$props.reply.target,
      content: this.$props.reply.content,
      time: util.dateFormat(this.$props.reply.createAt),
      like: this.$props.reply.like,
      id: this.$props.reply.id,
      commentId: this.$props.reply.commentId,
      targetId: this.$props.reply.targetId,
      liked: false,
    };
  },
  created() {
    this.loadLiked();
  },
  methods: {
    loadLiked: function () {
      if (util.getToken()) {
        axios
          .get(
            `/comment/liked?accountId=${this.currentUser}&commentId=${this.id}`
          )
          .then((res) => (this.liked = res.data.data));
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
    replyIt: function () {
      this.$refs.replyDialog.dialogVisible = true;
    },
    remove: function () {
      this.$confirm("确定删除此回复?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          axios
            .post("/reply/delete", {
              blogId: this.$props.reply.blogId,
              commentId: this.commentId,
              replyId: this.id,
            })
            .then((res) => {
              const data = res.data;
              if (!data.code) this.$message(util.success("删除成功"));
              else this.$message(util.error(`删除失败，错误码：${data.code}`));
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

<style>
</style>