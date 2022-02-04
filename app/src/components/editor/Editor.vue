<template>
  <el-main>
    <el-form ref="editorForm" :model="editorForm" label-width="80px">
      <legend style="padding-bottom: 30px; text-align: center">
        <strong>博文编辑</strong>
      </legend>
      <el-form-item label="标题">
        <el-input
          placeholder="请输入标题"
          v-model="editorForm.title"
        ></el-input>
      </el-form-item>
      <el-form-item label="分类">
        <el-select v-model="editorForm.category" placeholder="请选择分类">
          <el-option
            v-for="category in categories"
            :key="category.id"
            :label="category.name"
            :value="category.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="标签">
        <el-button icon="el-icon-circle-plus" @click="addTags" type="warning">
          添加标签
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-input v-model="editorForm.content" autosize></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="display">演示</el-button>
        <el-button type="primary" @click="save">保存</el-button>
        <el-button type="primary" @click="publish">发表</el-button>
        <el-button @click="reset">重置</el-button>
      </el-form-item>
    </el-form>
    <Display :displayVisible="false" ref="displayDialog" />
    <TagsAdding :tagsAddVisible="false" ref="tagsAddDialog" />
  </el-main>
</template>

<script>
import Display from "@/components/editor/Display";
import TagsAdding from "@/components/editor/TagsAdding";
import axios from "axios";
import util from "../../util";

export default {
  name: "Editor",
  components: { Display, TagsAdding },
  data() {
    return {
      categories: [],
      editorForm: {
        title: "",
        category: 0,
        content: "",
      },
    };
  },
  created() {
    this.getCategories();
  },
  methods: {
    reset: function () {
      // TODO: useless
      this.$refs.editorForm.resetFields();
    },
    display: function () {
      this.$refs.displayDialog.displayVisible = true;
    },
    publish: function () {
      if (localStorage.getItem(util.commonToken) === null) {
        this.$message({
          message: "未登录，请先登录",
          type: 'error'
        });
        this.$router.push("/login");
      } else {
        axios
          .post("/blog/publish", {
            title: this.editorForm.title,
            category: this.editorForm.category,
            content: this.editorForm.content,
          })
          .then((res) => {
            const data = res.data;
            if (data.code === util.result.TOKEN_EXPIRED) {
              this.$message({
                message: '令牌已过期，请重新登录',
                type: 'error'
              });
              this.$router.push('/login');
            } else {
              this.$message({
                message: '发表成功',
                type: 'success'
              })
              this.$router.push('/');
            }
          });
      }
    },
    save: function () {
      if (localStorage.getItem(util.commonToken) === null) {
        this.$message({
          message: "未登录，请先登录",
          type: 'error'
        });
        this.$router.push("/login");
      } else {
        axios.post("/blog/save", {
          title: this.editorForm.title,
          category: this.editorForm.category,
          content: this.editorForm.content,
        }).then(res => {
          const data = res.data;
          if (data.code === util.result.TOKEN_EXPIRED) {
              this.$message({
                message: '令牌已过期，请重新登录',
                type: 'error'
              });
            } else {
              this.$message({
                message: '保存成功',
                type: 'success'
              })
            }
        });
      }
    },
    addTags: function () {
      this.$refs.tagsAddDialog.tagsAddVisible = true;
    },
    getCategories: function () {
      axios.get('/categories').then(res => {
        const data = res.data.data;
        this.categories = data;
      });
    }
  },
};
</script>

<style scoped>
</style>
