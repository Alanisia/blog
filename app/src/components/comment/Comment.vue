<template>
  <div>
    <p>{{ username }}</p>
    <p>{{ content }}</p>
    <p>
      <el-button type="mini">点赞 {{ like }}</el-button>
      <span>{{ time }}</span>
      <span @click="showReplies" v-if="replies > 0">
        展开全部{{ replies }}条回复
      </span>
    </p>
  </div>
</template>

<script>
export default {
  name: "Comment",
  data() {
    return {
      username: "",
      content: "",
      like: 0,
      time: "",
      id: 0,
      replies: 0,
    };
  },
  methods: {
    showReplies: function () {},
  },
};
</script>

<style>
</style>