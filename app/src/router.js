import Vue from 'vue';
import VueRouter from 'vue-router';

import Main from '@/components/main/Main';
import Index from '@/components/main/Index';
import Newest from '@/components/main/Newest';
import Category from '@/components/main/Category';
import Editor from '@/components/editor/Editor';
import Detail from '@/components/detail/Detail';
import Center from '@/components/center/Center';
import Info from '@/components/center/info/Info';
import History from '@/components/center/blog/History';
import Star from '@/components/center/blog/Star';
import Mine from '@/components/center/blog/Mine';
import Draft from '@/components/center/blog/Draft';
import Comment from '@/components/center/comment/Comment';
import Login from '@/components/account/Login';
import Register from '@/components/account/Register';
import Page404 from '@/components/Page404';

Vue.use(VueRouter);

const routes = [
  {path: '/', component: Main, children: [
    {path: '/center', component: Center, children: [
      {path: '/info', component: Info},
      {path: '/comment', component: Comment},
      {path: '/history', component: History},
      {path: '/star', component: Star},
      {path: '/mine', component: Mine},
      {path: '/draft', component: Draft},
    ]},
    {path: '/detail/:id', component: Detail},
    {path: '/404', component: Page404},
    {path: '/editor/:type/:id', component: Editor},
    {path: '/', component: Index, children: [
      {path: '/', component: Newest},
      {path: '/categories', component: Category}
    ]},
  ]},
  {path: '/login', component: Login},
  {path: '/register', component: Register}
];

const router = new VueRouter({routes});
export default router;
