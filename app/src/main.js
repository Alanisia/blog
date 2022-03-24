import Vue from 'vue';
import router from './router';
import App from './App.vue';
import ElementUI from 'element-ui';
import axios from 'axios';
import util from './util';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);
Vue.config.productionTip = false;

axios.defaults.baseURL = 'http://127.0.0.1:9305';
axios.defaults.headers.post['Content-Type'] = 'application/json';
axios.defaults.timeout = 5000;
axios.interceptors.request.use(config => {
  const token = util.getToken();
  if (token && token.length > 0) config.headers.Authorization = token;
  return config;
});

new Vue({
  render: h => h(App), router
}).$mount('#app');
