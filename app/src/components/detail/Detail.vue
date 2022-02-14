<template>
  <el-main>
    <h1 style="text-align: center">{{ blog.title }}</h1>
    <legend style="text-align: center">分类：{{ blog.category }}</legend>
    <div v-html="contentHTML"></div>
    <p>
      更新时间：{{ blog.updateTime }}
      <span style="float: right">
        <el-button icon="el-icon-star-off" @click="star"
          >收藏 {{ blog.stars }}</el-button
        >
        <el-button icon="" @click="like">点赞 {{ blog.likes }}</el-button>
      </span>
    </p>
    <h3>评论区（登录后方可参与评论）（共{{ blog.comments }}条评论）</h3>
  </el-main>
</template>

<script>
import { marked } from "marked";
import axios from "axios";
import util from "../../util";
export default {
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
    };
  },
  created() {
    this.loadBlog();
    this.loadComments();
    this.insertHistory();
  },
  watch: {
    'blog.content': function() {
      this.contentHTML = marked(this.blog.content);
    }
  },
  methods: {
    loadBlog: function () {
      axios.get(`/blog/${this.blog.id}`).then(res => {
        const data = res.data.data;
        this.blog.id = data.id;
        this.blog.author = data.author;
        this.blog.title = data.title;
        this.blog.category = data.category;
        this.blog.content = data.content;
        this.blog.comments = data.comments;
        this.blog.stars = data.stars;
        this.blog.likes = data.likes;
        this.blog.updateTime = data.updateTime;
      });
    },
    loadComments: function () {},
    star: function () {
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
              this.$message({
                message: "收藏成功！",
                type: "success",
              });
              this.haveStarred = true;
            } else {
              this.$message({
                message: "收藏失败！",
                type: "error",
              });
            }
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
              this.$message({
                message: "取消收藏成功！",
                type: "success",
              });
              this.haveStarred = false;
            } else {
              this.$message({
                message: "取消收藏失败！",
                type: "error",
              });
            }
          });
      }
    },
    like: function () {
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
              this.$message({
                message: "点赞成功！",
                type: "success",
              });
              this.haveliked = true;
            } else {
              this.$message({
                message: "点赞失败！",
                type: "error",
              });
            }
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
              this.$message({
                message: "取消点赞成功！",
                type: "success",
              });
              this.haveliked = false;
            } else {
              this.$message({
                message: "取消点赞失败！",
                type: "error",
              });
            }
          });
      }
    },
    insertHistory: function() {
      axios.post("/history/insert", {
        accountId: util.getCurrentUser(),
        blogId: this.blog.id
      });
    },
    comment: function () {},
  },
};
</script>

<style>
</style>
