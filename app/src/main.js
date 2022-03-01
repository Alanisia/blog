import Vue from 'vue';
import router from '@/router';
// import store from '@/store';
import App from './App.vue';
import ElementUI from 'element-ui';
import axios from 'axios';
import 'element-ui/lib/theme-chalk/index.css';
// import 'bulma/css/bulma.css';
import util from './util';

Vue.use(ElementUI);
Vue.config.productionTip = false;

axios.defaults.baseURL = 'http://127.0.0.1:9305';
axios.defaults.headers.post['Content-Type'] = 'application/json'; //'application/x-www-form-urlencoded';
axios.defaults.headers.common['Authorization'] = util.getToken();
axios.defaults.timeout = 5000;

new Vue({
  render: h => h(App), router // , store
}).$mount('#app');
