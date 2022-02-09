<template>
  <el-main>
    <legend class="blog-legend">
      <strong>{{ blog.title }}</strong>
    </legend>
    <!-- 
    <el-descriptions>
      <el-descriptions-item label="分类">{{ blog.category }}</el-descriptions-item>
      <el-descriptions-item icon="el-icon-star-off">{{
        blog.stars
      }}</el-descriptions-item>
      <el-descriptions-item icon="">{{ blog.likes }}</el-descriptions-item>
      <el-descriptions-item icon="el-icon-chat-dot-square">{{
        blog.comments
      }}</el-descriptions-item>
      <el-descriptions-item label="更新时间"></el-descriptions-item>
    </el-descriptions>
    -->
    <legend class="blog-legend">分类：{{ blog.category }}</legend>
    <div v-html="blog.content"></div>
    <p>
      更新时间：{{ blog.updateTime }}
      <span style="float: right">
        <el-button icon="" @click="star">收藏 {{ blog.stars }}</el-button>
        <el-button icon="" @click="like">点赞 {{ blog.likes }}</el-button>
      </span>
    </p>
    <h3>评论区（登录后方可参与评论）（共{{ blog.comments }}条评论）</h3>
  </el-main>
</template>

<script>
import axios from "axios";
export default {
  name: "Detail",
  data() {
    return {
      haveStarred: false,
      haveLiked: false,
      blog: {
        id: 0,
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
    this.loadComment();
  },
  methods: {
    loadBlog: function () {},
    loadComment: function () {},
    star: function () {
      axios.post("/blog/star", {}).then((res) => {
        const data = res.data;
        if (!data.code) {
          if (this.haveStarred) {
            this.blog.stars--;
            this.$message({
              message: "取消收藏成功！",
              type: "success",
            });
          } else {
            this.blog.stars++;
            this.$message({
              message: "收藏成功！",
              type: "success",
            });
          }
          this.haveStarred = !this.haveStarred;
        } else {
          if (this.haveStarred) {
            this.$message({
              message: "取消收藏失败！",
              type: "error",
            });
          } else {
            this.$message({
              message: "收藏失败！",
              type: "error",
            });
          }
        }
      });
    },
    like: function () {
      if (this.haveLiked) this.blog.likes--;
      else this.blog.likes++;
      this.haveLiked = !this.haveLiked;
    },
    comment: function () {},
  },
};
</script>

<style>
.blog-legend {
  text-align: center;
}
</style>
