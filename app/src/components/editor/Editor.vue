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
        <codemirror
          :options="cmOptions"
          v-model="editorForm.content"
        ></codemirror>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="display">演示</el-button>
        <el-button type="primary" @click="save">保存</el-button>
        <el-button type="primary" @click="publish">发表</el-button>
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
import {codemirror} from "vue-codemirror";
import util from "../../util";

import "codemirror/lib/codemirror.css";
import "codemirror/addon/hint/show-hint.css";
import "codemirror/addon/fold/foldgutter.css";
import "codemirror/addon/selection/active-line";
import "codemirror/addon/edit/matchbrackets";
import "codemirror/addon/edit/closebrackets";
import "codemirror/addon/fold/foldcode";
import "codemirror/addon/fold/foldgutter";
import "codemirror/addon/fold/brace-fold";
import "codemirror/addon/fold/comment-fold";
import "codemirror/addon/hint/show-hint";
import "codemirror/addon/hint/anyword-hint";
import "codemirror/keymap/sublime";
import "codemirror/mode/markdown/markdown";

export default {
  name: "Editor",
  components: { Display, TagsAdding, codemirror },
  data() {
    return {
      categories: [],
      editorForm: {
        title: "",
        category: 0,
        content: "",
      },
      cmOptions: {
        mode: "markdown",
        lineNumbers: true,
        indentWithTabs: true,
        indentUnit: 4,
        tabSize: 4,
        styleActiveLine: true,
        matchBrackets: true,
        autoCloseBrackets: true,
        foldGutter: true,
        lint: true,
        hintOptions: {
          completeSingle: false,
        },
        gutters: [
          "CodeMirror-linenumbers",
          "CodeMirror-foldgutter",
          "CodeMirror-lint-markers",
        ],
      },
    };
  },
  created() {
    this.getCategories();
  },
  methods: {
    display: function () {
      this.$refs.displayDialog.mdText = this.editorForm.content;
      this.$refs.displayDialog.displayVisible = true;
    },
    publish: function () {
      if (localStorage.getItem(util.commonToken) === null) {
        this.$message({
          message: "未登录，请先登录",
          type: "error",
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
            if (data.code === util.result.AUTHORIZE_FAILED) {
              this.$message({
                message: "令牌已过期，请重新登录",
                type: "error",
              });
              this.$router.push("/login");
            } else {
              this.$message({
                message: "发表成功",
                type: "success",
              });
              this.$router.push("/");
            }
          });
      }
    },
    save: function () {
      if (localStorage.getItem(util.commonToken) === null) {
        this.$message({
          message: "未登录，请先登录",
          type: "error",
        });
        this.$router.push("/login");
      } else {
        axios
          .post("/blog/save", {
            title: this.editorForm.title,
            category: this.editorForm.category,
            content: this.editorForm.content,
          })
          .then((res) => {
            const data = res.data;
            if (data.code === util.result.AUTHORIZE_FAILED) {
              this.$message({
                message: "令牌已过期，请重新登录",
                type: "error",
              });
            } else {
              this.$message({
                message: "成功保存至草稿箱，可前往个人中心查看",
                type: "success",
              });
            }
          });
      }
    },
    addTags: function () {
      this.$refs.tagsAddDialog.tagsAddVisible = true;
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
