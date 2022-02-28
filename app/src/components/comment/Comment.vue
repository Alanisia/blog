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
  props: ['comment'],
  data() {
    return {
      username: comment.commenter,
      content: comment.content,
      like: comment.like,
      time: comment.createAt,
      id: comment.id,
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