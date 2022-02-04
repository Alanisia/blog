import Vue from 'vue';
import VueRouter from 'vue-router';

import Main from '@/components/main/Main';
import Index from '@/components/main/Index';
import Editor from '@/components/editor/Editor';
import Center from '@/components/center/Center';
import Info from '@/components/center/items/Info';
import Blog from '@/components/center/items/Blog';
import Message from '@/components/center/items/Message';
import Login from '@/components/account/Login';
import Register from '@/components/account/Register';
import Page404 from '@/components/Page404';

Vue.use(VueRouter);

const routes = [
  {path: '/', component: Main, children: [
    {path: '/center', component: Center, children: [
      {path: '/info', component: Info},
      {path: '/message', component: Message},
      {path: '/blog', component: Blog}
    ]},
    {path: '/editor', component: Editor},
    {path: '/', component: Index},
  ]},
  {path: '/login', component: Login},
  {path: '/register', component: Register},
  {path: '/404', component: Page404}
];

const router = new VueRouter({routes});
export default router;
