<template>
  <div>
    <tags />
    <search />
    <h1>最新文章</h1>
    <blog-list />
  </div>
</template>

<script>
import Tags from "../common/Tags";
import Search from "../common/Search";
import BlogList from "../common/BlogList";

export default {
  name: "Index",
  components: { Tags, Search, BlogList },
  data() {
    return {};
  },
  methods: {},
};
</script>

<style scoped>
</style>
