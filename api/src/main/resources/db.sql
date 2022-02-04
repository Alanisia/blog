drop database if exists `blog`;
create database `blog`;
use `blog`;

drop table if exists `role`;
create table `role`(
	  `id` tinyint not null,
    `name` varchar(16) not null,
    `create_at` datetime not null default current_timestamp,
    `update_at` datetime not null default current_timestamp on update current_timestamp,
    primary key(`id`)
)engine=InnoDB default charset=utf8mb4;

insert into `role`(`id`, `name`) values(1, '超级管理员'), (2, '管理员'), (3, '用户');

drop table if exists `operation`;
create table `operation`(
	  `id` tinyint not null,
    `name` varchar(32) not null,
    `create_at` datetime not null default current_timestamp,
    `update_at` datetime not null default current_timestamp on update current_timestamp,
    primary key(`id`)
)engine=InnoDB default charset=utf8mb4;

insert into `operation`(`id`, `name`) values(1, '系统管理'), (2, '日志管理'), (3, '角色管理'),
  (4, '用户管理'), (5, '博文管理'), (6, '评论管理'), (7, '模块管理'), (8, '消息管理');

drop table if exists `permission`;
create table `permission`(
	  `id` tinyint not null auto_increment,
    `role_id` tinyint not null,
    `operation_id` tinyint not null,
    `create_at` datetime not null default current_timestamp,
    `update_at` datetime not null default current_timestamp on update current_timestamp,
    primary key(`id`),
    key `key_role_id`(`role_id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `account`;
create table `account`(
	  `id` bigint not null auto_increment,
    `role_id` tinyint not null,
    `email` varchar(255) not null,
    `username` varchar(32) not null,
    `password` varchar(128) not null,
    `create_at` datetime not null default current_timestamp,
    `update_at` datetime not null default current_timestamp on update current_timestamp,
    primary key(`id`),
    key `key_email`(`email`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `user_detail`;
create table `user_detail`(
	  `id` bigint not null auto_increment,
    `account_id` bigint not null,
    `gender` varchar(10) not null comment 'male/female',
    `avatar` varchar(255) not null,
    `create_at` datetime not null default current_timestamp,
    `update_at` datetime not null default current_timestamp on update current_timestamp,
    primary key(`id`),
    key `key_account_id`(`account_id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `tag`;
create table `tag`(
	  `id` tinyint not null auto_increment,
    `name` varchar(16) not null,
    `create_at` datetime not null default current_timestamp,
    `update_at` datetime not null default current_timestamp on update current_timestamp,
    primary key(`id`)
)engine=InnoDB default charset=utf8mb4;

insert into `tag`(`name`) values('C/C++'), ('Java'), ('Python'), ('C#'), ('Go'), ('Node.JS'),
  ('前端'), ('后端'), ('网络安全'), ('Rust'), ('Android'), ('iOS'), ('Windows'), ('Linux'), ('Swift'),
  ('Objective-C'), ('PHP'), ('R'), ('大数据'), ('机器学习'), ('人工智能'), ('区块链'), ('分布式'),
  ('汇编'), ('网络编程'), ('计算机网络'), ('计算机组成原理'), ('操作系统'), ('数据库'), ('NoSQL'), ('MySQL'),
  ('Redis'), ('Shell'), ('ZooKeeper'), ('Ruby'), ('运维'), ('测试');

drop table if exists `category`;
create table `category`(
	  `id` tinyint not null auto_increment,
    `name` varchar(16) not null,
    `route` varchar(16) not null,
    `create_at` datetime not null default current_timestamp,
    `update_at` datetime not null default current_timestamp on update current_timestamp,
    primary key(`id`)
)engine=InnoDB default charset=utf8mb4;

insert into `category`(`name`, `route`) values('技术文章', 'tech'), ('个人随笔', 'personal');

drop table if exists `blog`;
create table `blog`(
	  `id` bigint not null auto_increment,
    `account_id` bigint not null,
    `category_id` tinyint not null,
    `title` varchar(128) not null,
    `content` text not null,
    `like` int not null,
    `star` int not null,
    `create_at` datetime not null default current_timestamp,
    `update_at` datetime not null default current_timestamp on update current_timestamp,
    primary key(`id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `star_blog`;
create table `star_blog`(
    `id` bigint not null,
    `blog_id` bigint not null,
    `account_id` bigint not null,
    `create_at` datetime not null default current_timestamp,
    primary key(`id`),
    key `key_account_id`(`account_id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `blog_history`;
create table `blog_history`(
    `id` bigint not null,
    `account_id` bigint not null,
    `blog_id` bigint not null,
    `create_at` datetime not null default current_timestamp,
    primary key(`id`),
    key `key_account_id`(`account_id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `tag_blog`;
create table `tag_blog` (
  `id` bigint not null auto_increment,
  `blog_id` bigint not null,
  `tag_id` tinyint not null,
  `create_at` datetime not null default current_timestamp,
  `update_at` datetime not null default current_timestamp on update current_timestamp,
  primary key(`id`),
  key `key_tag_id`(`tag_id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `comment`;
create table `comment`(
	  `id` bigint not null auto_increment,
    `account_id` bigint not null,
    `comment_id` bigint not null,
    `target_id` bigint not null,
    `blog_id` bigint not null,
    `content` text not null,
    `like` int not null,
    `create_at` datetime not null default current_timestamp,
    primary key(`id`),
    key `key_blog_id`(`blog_id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `message`;
create table `message`(
  `id` bigint not null auto_increment,
  `account_id` bigint not null,
  `sender_id` bigint not null,
  `content` text not null,
  `create_at` datetime not null default current_timestamp,
  primary key(`id`),
  key `key_account_id`(`account_id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `opr_log`;
create table `opr_log`(
  `id` bigint not null auto_increment,
  `operator_id` bigint not null,
  `operation_id` bigint not null,
  `action` varchar(128) not null,
  `create_at` datetime not null default current_timestamp,
  primary key(`id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `user_conf`;
create table `user_conf`(
    `id` bigint not null,
    `histories` int not null,
    `newest` int not null,
    `create_at` datetime not null default current_timestamp,
    `update_at` datetime not null default current_timestamp on update current_timestamp,
    primary key(`id`)
)engine=InnoDB default charset=utf8mb4;