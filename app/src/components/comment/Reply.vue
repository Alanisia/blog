<template>
  <div>
      <el-image :src='avatar'/>
      <p>{{username}} 回复： {{target}}</p>
      <p>{{ content }}</p>
      <p><i class="">{{like}}</i><span>{{time}}</span></p>
  </div>
</template>

<script>
export default {
  name: "Reply",
  data() {
    return {
      avatar: "",
      username: "",
      target: "",
      content: "",
      time: "",
      like: "",
      id: 0,
      commentId: 0,
      target: 0,
    };
  },
  methods: {
  },
};
</script>

<style>
</style>