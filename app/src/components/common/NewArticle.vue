<template>
  <div>
    <el-button
      @click="newArticle"
      type="primary"
      icon="el-icon-circle-plus"
      style="float: right"
    >
      新建文章
    </el-button>
  </div>
</template>

<script>
import util from "../../util";
export default {
  name: "NewArticle",
  methods: {
    newArticle: function () {
      const token = util.getToken();
      if (token === null || token === "") {
        this.$message(util.error("您还未登录，请先登录！"));
        this.$router.push("/login");
      } else this.$router.push("/editor/create/0");
    },
  },
};
</script>

<style scoped>
</style>
