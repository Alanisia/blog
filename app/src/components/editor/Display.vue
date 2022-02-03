<template>
  <el-dialog title="演示" :visible.sync="displayVisible">
    <div v-html="mdHtml"></div>
  </el-dialog>
</template>

<script>
export default {
  name: "Display",
  data() {
    return {
      mdText: "",
      mdHtml: "",
      displayVisible: false
    };
  },
  methods: {
    mdRender: function () {},
  },
};
</script>

<style>
</style>
