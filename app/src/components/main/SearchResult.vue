<template>
  <div>
    <el-form :model="searchForm" :inline="true">
      <el-form-item>
        <el-input v-model="searchForm.keyword" placeholder="请输入搜索词">
          <i class="el-icon-search" slot="prepend"></i>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search">搜索</el-button>
      </el-form-item>
    </el-form>
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
import axios from 'axios';
import BlogList from "../common/BlogList.vue";
import util from '../../util';
export default {
  name: "SearchResult",
  components: { BlogList },
  data() {
    return {
      result: [],
      currentPage: 1,
      searchForm: {
        keyword: "",
      },
    };
  },
  methods: {
    handleCurrentChange: function () {},
    search: function () {
      const keyword = this.searchForm.keyword;
      if (keyword.length === 0)
        this.$message(util.error("搜索关键词不能为空！"));
      else
        axios.get(`/blog/search?keyword=${keyword}`).then((res) => {
          const data = res.data;
          if (!data.code) {
            const result = data.data;
            this.result = result;
          } else util.error("搜索失败，请稍候重试！");
        });
    },
  },
};
</script>

<style>
.el-pagination {
  text-align: center;
  margin: 10px;
}
</style>