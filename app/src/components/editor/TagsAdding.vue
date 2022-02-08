<template>
  <el-dialog title="选择标签" :visible.sync="tagsAddVisible">
    <el-tag
      class="blog-tag"
      type="warning"
      effect="dark"
      v-for="tag in tags"
      :key="tag.id"
      @click="addTag(tag)"
    >
      {{ tag.name }}
    </el-tag>
    <div>
      <span class="el-group__title">已选</span>
      <el-tag closable class="blog-tag" v-for="tag in tagsChosen" :key="tag.id" @close="closeTag(tag)">
        {{ tag.name }}
      </el-tag>
    </div>
  </el-dialog>
</template>

<script>
import axios from "axios";
export default {
  name: "TagsAdding",
  data() {
    return {
      tags: [],
      tagsChosen: this.$store.state.tagsChosen,
      tagsAddVisible: false,
    };
  },
  mounted() {
    this.loadTags();
  },
  methods: {
    loadTags: function () {
      axios.get("/tags").then((res) => {
        const data = res.data.data;
        this.tags = data;
      });
    },
    addTag: function (tag) {
      this.tagsChosen.push(tag);
    },
    closeTag: function (tag) {
      this.tagsChosen.splice(this.tagsChosen.indexOf(tag), 1);
    }
  },
};
</script>

<style>
.blog-tag {
  margin: 5px;
}
</style>
