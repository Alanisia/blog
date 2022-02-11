<template>
  <div>
    <h1>最新文章</h1>
    <blog-list :blogs="newest" />
  </div>
</template>

<script>
import BlogList from "../common/BlogList";
import axios from "axios";

export default {
  name: "Newest",
  components: { BlogList },
  data() {
    return {
      newest: [],
    };
  },
  created() {
    this.loadNewestArticles();
  },
  methods: {
    loadNewestArticles: function () {
      axios.get("/blog/newest").then((res) => {
        const data = res.data.data;
        this.newest = data;
      });
    },
  },
};
</script>

<style>
</style>