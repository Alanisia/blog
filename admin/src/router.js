import Vue from 'vue';
import VueRouter from 'vue-router';
import Login from '@/components/account/Login.vue';
import Admin from '@/components/admin/Admin.vue';
import Index from '@/components/admin/Index.vue';
import CategoriesAndTags from '@/components/admin/CategoriesAndTags.vue';
import Users from '@/components/admin/Users.vue';
import System from '@/components/admin/System.vue';
import Comments from '@/components/admin/Comments.vue';
import Blogs from '@/components/admin/Blogs.vue';

Vue.use(VueRouter);

const routes = [
  {path: '/', component: Admin, children: [
    {path: '/index', component: Index},
    {path: '/categories_and_tags', component: CategoriesAndTags},
    {path: '/blogs', component: Blogs},
    {path: '/users', component: Users},
    {path: '/comments', component: Comments},
    {path: '/system', component: System}
  ]},
  {path: '/login', component: Login}
];
const router = new VueRouter({routes});

export default router;
