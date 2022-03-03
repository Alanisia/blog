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
            :value="category"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <common-editor ref="mdEditor" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="display">演示</el-button>
        <el-button type="primary" @click="save">保存</el-button>
        <el-button type="primary" @click="publish">发表</el-button>
      </el-form-item>
    </el-form>
    <display :displayVisible="false" ref="displayDialog" />
  </el-main>
</template>

<script>
import Display from "@/components/editor/Display";
import axios from "axios";
import util from "../../util";
import CommonEditor from "../common/CommonEditor.vue";

export default {
  name: "Editor",
  components: { Display, CommonEditor },
  data() {
    return {
      categories: [],
      accountId: util.getCurrentUser(),
      editorForm: {
        title: "",
        content: "",
        category: 0,
      },
      type: this.$route.params.type, // update/create/draft
    };
  },
  created() {
    this.getCategories();
  },
  methods: {
    display: function () {
      this.editorForm.content = this.$refs.mdEditor.content;
      this.$refs.displayDialog.mdText = this.editorForm.content;
      this.$refs.displayDialog.displayVisible = true;
    },
    publish: function () {
      this.editorForm.content = this.$refs.mdEditor.content;
      if (util.getToken() === null) {
        this.$message(util.error("未登录，请先登录"));
        this.$router.push("/login");
      } else {
        const content = this.editorForm.content;
        if (content === "") this.$message(util.error("博文不能为空！"));
        else {
          axios
            .post("/blog/publish", {
              accountId: this.accountId,
              category: this.editorForm.category,
              title: this.editorForm.title,
              content: content,
              draft: 0,
            })
            .then((res) => {
              const data = res.data;
              if (data.code === util.result.AUTHORIZE_FAILED) {
                this.$message(util.error("令牌已过期，请重新登录"));
                this.$router.push("/login");
              } else {
                this.$message(util.success("发表成功"));
                this.$router.push("/");
              }
            });
        }
      }
    },
    save: function () {
      this.editorForm.content = this.$refs.mdEditor.content;
      if (util.getToken() === null) {
        this.$message(util.error("未登录，请先登录"));
        this.$router.push("/login");
      } else {
        const content = this.editorForm.content;
        if (content === "") this.$message(util.error("博文不能为空！"));
        else {
          axios
            .post("/blog/save", {
              accountId: this.accountId,
              category: this.editorForm.category,
              title: this.editorForm.title,
              content: content,
              draft: 1,
            })
            .then((res) => {
              const data = res.data;
              if (data.code === util.result.AUTHORIZE_FAILED) {
                this.$message(util.error("令牌已过期，请重新登录"));
              } else {
                this.$message(
                  util.success("成功保存至草稿箱，可前往个人中心查看")
                );
                this.$router.push("/");
              }
            });
        }
      }
    },
    getCategories: function () {
      axios.get("/categories").then((res) => {
        const data = res.data.data;
        this.categories = data;
      });
    },
  },
};
</script>

<style>
</style>
