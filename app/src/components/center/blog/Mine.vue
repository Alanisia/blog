<template>
  <div>
    <p>共有数据{{ count }}条</p>
    <div>
      <blog-list :blogs="items" type="publish"/>
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
        console.log(this.items);
      });
    },
  },
};
</script>

<style>
</style>
