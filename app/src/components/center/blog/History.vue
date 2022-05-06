<template>
  <div>
    <h3>博文浏览历史</h3>
    <p>共有数据{{ count }}条</p>
    <blog-list :blogs="items" type="history" ref="blogsRef" />
  </div>
</template>

<script>
import axios from "axios";
import BlogList from "./BlogList.vue";
import util from "../../../util";
export default {
  name: "History",
  components: { BlogList },
  data() {
    return {
      count: 0,
      items: [],
    };
  },
  created() {
    this.loadHistory();
  },
  methods: {
    loadHistory: function () {
      axios.get(`/history/${util.getCurrentUser()}`).then((res) => {
        const data = res.data.data;
        this.count = data.count;
        this.items = data.items;
        this.$refs.blogsRef.total = this.count;
        this.$refs.blogsRef.blogItems = this.items;
        this.$refs.blogsRef.handleCurrentChange(1);
      });
    },
  },
};
</script>

<style>
</style>
