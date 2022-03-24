<template>
  <div>
    <h3>我发表的博文</h3>
    <p>共有数据{{ count }}条</p>
    <div>
      <blog-list :blogs="items" type="publish" ref="blogsRef" />
    </div>
  </div>
</template>

<script>
import axios from "axios";
import BlogList from "./BlogList.vue";
import util from "../../../util";
export default {
  name: "Mine",
  components: { BlogList },
  data() {
    return {
      count: 0,
      items: [],
    };
  },
  created() {
    this.loadPublish();
  },
  methods: {
    loadPublish: function () {
      axios.get(`/publish/${util.getCurrentUser()}`).then((res) => {
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
