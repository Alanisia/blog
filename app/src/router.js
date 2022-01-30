import Vue from 'vue';
import VueRouter from 'vue-router';

import Main from '@/components/main/Main';
import Index from '@/components/main/Index';
import Login from '@/components/account/Login';
import Register from '@/components/account/Register';

Vue.use(VueRouter);

const routes = [
  {path: '/', component: Main, children: [
    {path: '/', component: Index},
  ]},
  {path: '/login', component: Login},
  {path: '/register', component: Register},
];

const router = new VueRouter({routes});
export default router;
