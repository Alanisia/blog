<template>
  <div>
    <search />
    <router-view></router-view>
  </div>
</template>

<script>
import Search from "../common/Search";

export default {
  name: "Index",
  components: { Search },
};
</script>

<style scoped>
</style>
