<template>
  <el-table :data="blogs">
    <el-table-column prop="id" label="ID"></el-table-column>
    <el-table-column prop="category" label="分类"></el-table-column>
    <!-- <el-table-column prop="tags" label="标签"></el-table-column> -->
    <el-table-column prop="title" label="标题"></el-table-column>
    <el-table-column prop="author" label="作者"></el-table-column>
    <el-table-column prop="star" label="收藏"></el-table-column>
    <el-table-column prop="like" label="赞"></el-table-column>
    <el-table-column prop="comment" label="评论"></el-table-column>
    <el-table-column prop="publishAt" label="发表时间"></el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "BlogList",
  props: ['blogs']
};
</script>

<style scoped>
</style>
