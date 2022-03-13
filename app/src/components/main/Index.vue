<template>
  <div>
    <new-article/>
    <router-view></router-view>
  </div>
</template>

<script>
import NewArticle from "../common/NewArticle";

export default {
  name: "Index",
  components: { NewArticle },
};
</script>

<style scoped>
</style>
