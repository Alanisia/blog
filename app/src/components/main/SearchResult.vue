<template>
  <div>
    <h1>搜索结果</h1>
    <blog-list :blogs="result" /> 
    <el-pagination
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-size="30"
      layout="total, prev, pager, next, jumper"
      :total="total"
    ></el-pagination>
  </div>
</template>

<script>
import BlogList from "../common/BlogList.vue";
export default {
  name: "SearchResult",
  components: { BlogList },
  data() {
    return {
      result: [],
      currentPage: 1,
    };
  },
  methods: {
    handleCurrentChange: function () {},
  },
};
</script>

<style>
</style>