<template>
  <codemirror :options="cmOptions" v-model="content"></codemirror>
</template>

<script>
import { codemirror } from "vue-codemirror";

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
  name: "CommonEditor",
  components: { codemirror },
  data() {
    return {
      content: "",
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
};
</script>

<style>
.CodeMirror-sizer {
  line-height: 22px;
}
</style>