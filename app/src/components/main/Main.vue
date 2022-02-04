<template>
  <el-container>
    <el-header>
      <navbar />
    </el-header>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
import Navbar from "@/components/common/Navbar";

export default {
  name: "Main",
  components: { Navbar },
  data() {
    return {
      token: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style>
</style>
