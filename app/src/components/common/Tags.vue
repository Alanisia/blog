<template>
  <div>
    <template v-for="tag in tags">
      <el-tag @click="tagOnClick(tag.id)" :key='tag.id'>{{ tag.name }}</el-tag>
    </template>
  </div>
</template>

<script>
export default {
  name: "Tags",
  data() {
    return {
      tags: [],
    };
  },
  methods: {
    tagOnClick: function (id) {
    console.log(id);
    },
  },
};
</script>

<style>
</style>
