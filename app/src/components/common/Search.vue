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
      <el-button
        @click="newArticle"
        type="primary"
        icon="el-icon-circle-plus"
        style="float: right"
      >
        新建文章
      </el-button>
    </el-form>
  </div>
</template>

<script>
import axios from 'axios';
import util from '../../util';
export default {
  name: "Search",
  data() {
    return {
      searchForm: {
        keyword: "",
      },
    };
  },
  methods: {
    search: function () {
      axios.get(`/blog/search?keyword=${this.searchForm.keyword}`).then(res => {
        const data = res.data.data;
        // TODO: show data with blog list component
        console.log(data);
      });
    },
    newArticle: function () {
      const token = util.getToken(); 
      if (token === null || token === "") {
        this.$message({
          message: "您还未登录，请先登录！",
          type: "error"
        });
        this.$router.push('/login');
      } else this.$router.push('/editor');
    },
  },
};
</script>

<style scoped>
</style>
