<template>
  <div>
    <h2>分类</h2>
    <div class="cards">
      <el-card
        shadow="hover"
        v-for="category in categories"
        :key="category.id"
        @click="cardClick(category.id)"
      >
        {{ category.name }}
      </el-card>
    </div>
    <h1>文章列表</h1>
    <blog-list :blogs="list" />
    <el-pagination
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-size="30"
      layout="total, prev, pager, next, jumper"
      :total="total"
    >
    </el-pagination>
  </div>
</template>

<script>
import BlogList from "../common/BlogList";
import axios from "axios";
export default {
  name: "Category",
  components: { BlogList },
  data() {
    return {
      categoryId: 0,
      categories: [],
      list: [],
      currentPage: 1,
      total: 0,
      pages: 0,
    };
  },
  created() {
    this.loadCategories();
    this.loadList();
  },
  methods: {
    loadCategories: function () {
      axios.get("/categories").then((res) => {
        const data = res.data.data;
        this.categories = data;
      });
    },
    loadList: function () {
      axios
        .get(
          `/blog/category?categoryId=${this.categoryId}&page=${this.currentPage}`
        )
        .then((res) => {
          const data = res.data.data;
          this.list = data.items;
          this.pages = data.pages;
        });
    },
    cardClick: function (id) {
      this.categoryId = id;
      this.loadList();
    },
    handleCurrentChange: function (val) {
      this.currentPage = val;
    },
  },
};
</script>

<style scoped>
.cards {
  display: flex;
}

.cards .el-card {
  margin: 5px;
}

.el-pagination {
  text-align: center;
  margin: 10px;
}
</style>