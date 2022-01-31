<template>
  <el-dialog title="选择标签" :visible.sync="tagsAddVisible"> </el-dialog>
</template>

<script>
export default {
  name: "TagsAdding",
  data() {
    return {
      tags: [],
      tagsAddVisible: false,
    };
  },
  methods: {},
};
</script>

<style>
</style>
