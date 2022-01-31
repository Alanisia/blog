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
    search: function () {},
    newArticle: function () {
    this.$router.push('/editor');
    },
  },
};
</script>

<style scoped>
</style>
