<template>
  <el-main>
    <legend>
      <strong>{{ blog.title }}</strong>
    </legend>
    <el-descriptions>
      <el-descriptions-item></el-descriptions-item>
      <el-descriptions-item icon="el-icon-star-off">{{
        blog.stars
      }}</el-descriptions-item>
      <el-descriptions-item icon="">{{ blog.likes }}</el-descriptions-item>
      <el-descriptions-item icon="el-icon-chat-dot-square">{{
        blog.comments
      }}</el-descriptions-item>
      <el-descriptions-item label="更新时间"></el-descriptions-item>
    </el-descriptions>
    <div>{{ blog.content }}</div>
    <h3>评论区（登录后方可参与评论）</h3>
  </el-main>
</template>

<script>
export default {
  name: "Detail",
  data() {
    return {
      blog: {
        id: 0,
        title: "",
        updateTime: "",
        tags: [],
        content: "",
        comments: 0,
        likes: 0,
        stars: 0,
      },
    };
  },
  created() {

  },
  methods: {
    loadBlog: function() {},
    loadComment: function() {},
    star: function() {},
    like: function() {},
    comment: function() {}
  }
};
</script>

<style>
</style>
