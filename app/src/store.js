import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    commentKey: 0,
    replyKey: 0
  },
  mutations: {
    incrementCommentKey(state) {
      state.commentKey++;
    },
    incrementReplyKey(state) {
      state.replyKey++;
    }
  }
});

export default store;
