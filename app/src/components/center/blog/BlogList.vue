<template>
  <el-table :data="this.$props.blogs" @row-click="rowClick">
    <el-table-column prop="id" label="ID"></el-table-column>
    <el-table-column prop="category" label="分类"></el-table-column>
    <el-table-column prop="title" label="标题"></el-table-column>
    <el-table-column prop="author" label="作者"></el-table-column>
    <el-table-column prop="star" label="收藏"></el-table-column>
    <el-table-column prop="like" label="赞"></el-table-column>
    <el-table-column prop="comment" label="评论"></el-table-column>
    <el-table-column prop="updateAt" label="更新时间"></el-table-column>
    <el-table-column align="right" label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          v-show="showUpdate()"
          @click="handleUpdate(scope.row.id)"
        >
          更新
        </el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.row.id)"
        >
          删除
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import axios from "axios";
import util from "../../../util";

export default {
  name: "BlogList",
  props: ["blogs", "type"],
  methods: {
    showUpdate: function () {
      return this.$props.type === "draft" || this.$props.type === "publish";
    },
    rowClick: function(row) {
      let id = row.id;
      this.$router.push(`/detail/${id}`);
    },
    handleDelete: function (id) {
      const accountId = util.getCurrentUser();
      const type = this.$props.type;
      let requestURL = `/remove/${util.getCurrentUser()}/${type}/${id}`;
      axios
        .post(requestURL, {
          id: accountId,
          type: type,
          blogId: id,
        })
        .then((res) => {
          const data = res.data;
          if (!data.code) {
            this.$message(util.success("删除成功！"));
            // this.$forceUpdate();
          } else {
            this.$message(util.error("删除失败！"));
          }
        });
    },
    handleUpdate: function (id) {
      // console.log(this.$props.type, id);
      this.$router.push(`/editor/update/${id}`);
    },
  },
};
</script>

<style>
</style>
