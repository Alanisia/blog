<template>
  <el-table :data="blogs" highlight-current-row @row-click="rowClick">
    <el-table-column prop="id" label="ID"></el-table-column>
    <el-table-column prop="category" label="分类"></el-table-column>
    <el-table-column prop="title" label="标题"></el-table-column>
    <el-table-column prop="author" label="作者"></el-table-column>
    <el-table-column prop="star" label="收藏"></el-table-column>
    <el-table-column prop="like" label="赞"></el-table-column>
    <el-table-column prop="comment" label="评论"></el-table-column>
    <el-table-column prop="updateAt" label="更新时间" :formatter="dateFormat"></el-table-column>
  </el-table>
</template>

<script>
import util from '../../util';
export default {
  name: "BlogList",
  props: ['blogs'],
  methods: {
    rowClick: function(row) {
      let id = row.id;
      this.$router.push(`/detail/${id}`);
    },
    dateFormat: function(row) {
      let date = row.updateAt;
      return date ? util.dateFormat(date) : "";
    }
  } 
};
</script>

<style scoped>
</style>
