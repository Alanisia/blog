import Vue from 'vue';
import router from '@/router';
import App from './App.vue';
import ElementUI from 'element-ui';
import axios from 'axios';
import 'element-ui/lib/theme-chalk/index.css';
import 'bulma/css/bulma.css';
import util from './util';

Vue.use(ElementUI);
Vue.config.productionTip = false;

axios.defaults.baseURL = 'http://127.0.0.1:9305';
axios.defaults.headers.post['Content-Type'] = 'application/json';
axios.defaults.headers.common['Authorization'] = localStorage.getItem(util.commonToken);
axios.defaults.timeout = 5000;

new Vue({
  render: h => h(App), router
}).$mount('#app');
