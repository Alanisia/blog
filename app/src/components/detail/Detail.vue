<template>
  <el-main>
    <h1 style="text-align: center">{{ blog.title }}</h1>
    <legend style="text-align: center">分类：{{ blog.category }}</legend>
    <div v-html="contentHTML"></div>
    <p>
      <span>更新时间：{{ blog.updateTime }}</span>
      <span style="float: right">
        <el-button icon="el-icon-star-off" @click="star">
          收藏 {{ blog.stars }}
        </el-button>
        <el-button icon="" @click="like">点赞 {{ blog.likes }}</el-button>
      </span>
    </p>
    <h3>评论区（登录后方可参与评论）（共{{ blog.comments }}条评论）</h3>
    <comment v-for="comment in comments" :comment="comment" :key="comment.id" />
    <h4>Markdown编辑器</h4>
    <el-form :model="commentForm" ref="commentForm">
      <el-form-item>
        <common-editor ref="commentEditor" />
      </el-form-item>
      <el-form-item>
        <el-button @click="display">演示</el-button>
        <el-button @click="comment" type="primary">发表评论</el-button>
      </el-form-item>
    </el-form>
    <display :displayVisible="false" ref="displayDialog" />
  </el-main>
</template>

<script>
import { marked } from "marked";
import axios from "axios";
import util from "../../util";
import CommonEditor from "../common/CommonEditor.vue";
import Display from "../editor/Display.vue";
import Comment from "../comment/Comment.vue";
export default {
  components: { CommonEditor, Display, Comment },
  name: "Detail",
  data() {
    return {
      haveStarred: false,
      haveLiked: false,
      contentHTML: "",
      blog: {
        id: this.$route.params.id,
        author: "",
        title: "",
        updateTime: "",
        category: "",
        content: "",
        comments: 0,
        likes: 0,
        stars: 0,
      },
      commentForm: {
        mdText: "",
        mdHtml: "",
      },
      comments: [],
    };
  },
  created() {
    this.loadBlog();
    this.loadStarredOrLiked();
    this.loadComments();
    this.insertHistory();
  },
  watch: {
    "blog.content": function () {
      this.contentHTML = marked(this.blog.content);
    },
    "commentForm.mdText": function () {
      this.commentForm.mdHtml = marked(this.commentForm.mdText);
    },
  },
  methods: {
    display: function () {
      this.commentForm.mdText = this.$refs.commentEditor.content;
      this.$refs.displayDialog.mdText = this.commentForm.mdText;
      this.$refs.displayDialog.displayVisible = true;
    },
    loadBlog: function () {
      axios.get(`/blog/detail/${this.blog.id}`).then((res) => {
        const data = res.data.data;
        if (data === null) {
          this.$router.push("/404");
        } else {
          this.blog.id = data.id;
          this.blog.author = data.author;
          this.blog.title = data.title;
          this.blog.category = data.category;
          this.blog.content = data.content;
          this.blog.comments = data.comments;
          this.blog.stars = data.stars;
          this.blog.likes = data.likes;
          this.blog.updateTime = data.updateTime;
        }
      });
    },
    loadStarredOrLiked: function () {
      if (util.getToken() !== null)
        axios
          .get(
            `/blog/starredOrLiked?accountId=${util.getCurrentUser()}&blogId=${
              this.blog.id
            }`
          )
          .then((res) => {
            const data = res.data.data;
            this.haveStarred = data.haveStarred;
            this.haveLiked = data.haveLiked;
          });
    },
    loadComments: function () {
      axios.get(`/comments?blogId=${this.blog.id}`).then((res) => {
        const data = res.data.data;
        this.comments = data;
      });
    },
    star: function () {
      if (util.getToken() === null) {
        this.$message(util.error("未登录，请先登录"));
        this.$router.push("/login");
      } else {
        if (!this.haveStarred) {
          axios
            .post("/blog/star", {
              accountId: util.getCurrentUser(),
              blogId: this.blog.id,
            })
            .then((res) => {
              const data = res.data;
              if (!data.code) {
                this.blog.stars++;
                this.$message(util.success("收藏成功！"));
                this.haveStarred = true;
              } else if (data.code === util.result.AUTHORIZE_FAILED) {
                this.$message(util.error("令牌已过期，请重新登录"));
                this.$router.push("/login");
              } else this.$message(util.error("收藏失败！"));
            });
        } else {
          axios
            .post("/blog/star/cancel", {
              accountId: util.getCurrentUser(),
              blogId: this.blog.id,
            })
            .then((res) => {
              const data = res.data;
              if (!data.code) {
                this.blog.stars--;
                this.$message(util.success("取消收藏成功！"));
                this.haveStarred = false;
              } else if (data.code === util.result.AUTHORIZE_FAILED) {
                this.$message(util.error("令牌已过期，请重新登录"));
                this.$router.push("/login");
              } else this.$message(util.error("取消收藏失败！"));
            });
        }
      }
    },
    like: function () {
      if (util.getToken() === null) {
        this.$message(util.error("未登录，请先登录"));
        this.$router.push("/login");
      } else {
        if (!this.haveliked) {
          axios
            .post("/blog/like", {
              accountId: util.getCurrentUser(),
              blogId: this.blog.id,
            })
            .then((res) => {
              const data = res.data;
              if (!data.code) {
                this.blog.likes++;
                this.$message(util.success("点赞成功！"));
                this.haveliked = true;
              } else if (data.code === util.result.AUTHORIZE_FAILED) {
                this.$message(util.error("令牌已过期，请重新登录"));
                this.$router.push("/login");
              } else this.$message(util.error("点赞失败!"));
            });
        } else {
          axios
            .post("/blog/like/cancel", {
              accountId: util.getCurrentUser(),
              blogId: this.blog.id,
            })
            .then((res) => {
              const data = res.data;
              if (!data.code) {
                this.blog.likes--;
                this.$message(util.success("取消点赞成功！"));
                this.haveliked = false;
              } else if (data.code === util.result.AUTHORIZE_FAILED) {
                this.$message(util.error("令牌已过期，请重新登录"));
                this.$router.push("/login");
              } else this.$message(util.error("取消点赞失败！"));
            });
        }
      }
    },
    insertHistory: function () {
      if (util.getToken() !== null) {
        axios.post("/history/insert", {
          accountId: util.getCurrentUser(),
          blogId: this.blog.id,
        });
      }
    },
    comment: function () {
      if (util.getToken() === null) {
        this.$message(util.error("未登录，请先登录"));
        this.$router.push("/login");
      } else {
        const content = this.commentForm.mdText;
        if (content === "") this.$message(util.error("评论不能为空！"));
        else {
          axios
            .post("/comment", {
              accountId: util.getCurrentUser(),
              blogId: this.blog.id,
              content: content,
            })
            .then((res) => {
              const data = res.data;
              if (!data.code) {
                this.$message(util.success("评论成功！"));
              } else if (data.code === util.result.AUTHORIZE_FAILED) {
                this.$message(util.error("令牌已过期，请重新登录"));
                this.$router.push("/login");
              } else return new Error(`评论失败，错误码：${data.code}`);
            })
            .then(() => {
              this.loadComments();
            })
            .catch((err) => {
              this.$message(util.error(err));
            });
        }
      }
    },
  },
};
</script>

<style>
</style>
