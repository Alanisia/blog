<template>
  <div>
    <h3>我的评论</h3>
    <el-table :data="commentsPerPage" highlight-current-row @row-click="rowClick">
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="target" label="评论对象"></el-table-column>
      <el-table-column prop="title" label="博文标题"></el-table-column>
      <el-table-column prop="blogId" label="博文ID"></el-table-column>
      <el-table-column prop="createAt" label="评论时间" :formatter="dateFormat"></el-table-column>
    </el-table>
    <el-pagination
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-size="pageSize"
      layout="total, prev, pager, next, jumper"
      :total="total"
    ></el-pagination>
  </div>
</template>

<script>
import axios from 'axios';
import util from '../../../util'
export default {
  name: "Comment",
  data() {
    return {
      id: util.getCurrentUser(),
      currentPage: 1,
      total: 0,
      pageSize: 20,
      comments: [],
      commentsPerPage: []
    };
  },
  created() {
    this.loadComments();
  },
  methods: {
    loadComments: function () {
      axios.get(`/my_comment/${this.id}`).then(res => {
        const data = res.data.data;
        this.comments = data;
        this.total = this.comments.length;
        this.handleCurrentChange(1);
      });
    },
    rowClick: function(row) {
      let id = row.blogId;
      this.$router.push(`/detail/${id}`);
    },
    dateFormat: function(row) {
      let date = row.createAt;
      return date ? util.dateFormat(date) : "";
    },
    handleCurrentChange: function (val) {
      let temp = [];
      this.currentPage = val;
      for (let i = (val - 1) * this.pageSize; i < this.total && i < val * this.pageSize; i++)
        temp.push(this.comments[i]);
      this.commentsPerPage = temp;
    },
  }
}
</script>

<style scoped>
.el-pagination {
  text-align: center;
  margin: 10px;
}
</style>