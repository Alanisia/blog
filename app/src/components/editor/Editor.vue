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
        <el-button icon="el-icon-circle-plus" @click="addTags" type="warning"
          >添加标签</el-button
        >
      </el-form-item>
      <el-form-item> </el-form-item>
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

export default {
  name: "Editor",
  components: { Display, TagsAdding },
  data() {
    return {
      categories: [],
      editorForm: {
        title: "",
        category: 0,
      },
    };
  },
  methods: {
    reset: function () {
      this.$refs.editorForm.resetFields();
    },
    display: function () {
      this.$refs.displayDialog.displayVisible = true;
    },
    publish: function () {},
    save: function () {},
    addTags: function () {
      this.$refs.tagsAddDialog.tagsAddVisible = true;
    },
  },
};
</script>

<style scoped>
</style>
