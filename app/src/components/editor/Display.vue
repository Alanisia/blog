<template>
  <el-dialog title="演示" :visible.sync="displayVisible">
    <div v-html="mdHtml"></div>
  </el-dialog>
</template>

<script>
import { marked } from "marked";

export default {
  name: "Display",
  data() {
    return {
      mdText: "",
      mdHtml: "",
      displayVisible: false,
    };
  },
  watch: {
    mdText: function() {
      this.mdHtml = marked(this.mdText);
    }
  },
};
</script>

<style>
</style>
