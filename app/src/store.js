import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const store = new Vuex.Store({
  state() {
    return {
      tagsChosen: []
    };
  },
  mutations: {
    tagAdd(state, tags) {
      for (let i = 0; i < tags.length; i++) 
        state.tagsChosen.push(tags[i]);
    },
    tagClean(state) {
      state.tagsChosen = [];
    }
  },
});

export default store;
