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
    `create_at` datetime not null default current_timestamp,
    `update_at` datetime not null default current_timestamp on update current_timestamp,
    primary key(`id`),
    key `key_account_id`(`account_id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `category`;
create table `category`(
	  `id` tinyint not null auto_increment,
    `name` varchar(16) not null,
    `route` varchar(16) not null,
    `create_at` datetime not null default current_timestamp,
    `update_at` datetime not null default current_timestamp on update current_timestamp,
    primary key(`id`)
)engine=InnoDB default charset=utf8mb4;

insert into `category`(`name`, `route`) values('个人随笔', 'personal');

drop table if exists `blog`;
create table `blog`(
	  `id` bigint not null auto_increment,
    `account_id` bigint not null,
    `category_id` tinyint not null,
    `title` varchar(128) not null,
    `content` text not null,
    `draft` bool not null,
    `create_at` datetime not null default current_timestamp,
    `update_at` datetime not null default current_timestamp on update current_timestamp,
    primary key(`id`),
    key `key_account_id`(`account_id`),
)engine=InnoDB default charset=utf8mb4;

drop table if exists `star_blog`;
create table `star_blog`(
    `id` bigint not null auto_increment,
    `blog_id` bigint not null,
    `account_id` bigint not null,
    `create_at` datetime not null default current_timestamp,
    primary key(`id`),
    key `key_account_id`(`account_id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `like_blog`;
create table `like_blog`(
  `id` bigint not null auto_increment,
  `blog_id` bigint not null,
  `account_id` bigint not null,
  `create_at` datetime not null default current_timestamp,
  primary key(`id`),
  key `key_blog_id_account_id`(`blog_id`, `account_id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `blog_history`;
create table `blog_history`(
    `id` bigint not null auto_increment,
    `account_id` bigint not null,
    `blog_id` bigint not null,
    `create_at` datetime not null default current_timestamp,
    primary key(`id`),
    key `key_account_id`(`account_id`),
    key `key_account_id_blog_id`(`account_id`, `blog_id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `comment`;
create table `comment`(
	  `id` bigint not null auto_increment,
    `account_id` bigint not null,
    `comment_id` bigint not null,
    `target_id` bigint not null,
    `blog_id` bigint not null,
    `content` text not null,
    `create_at` datetime not null default current_timestamp,
    primary key(`id`),
    key `key_blog_id`(`blog_id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `comment_like`;
create table `comment_like`(
    `id` bigint not null auto_increment,
    `account_id` bigint not null,
    `comment_id` bigint not null,
    `create_at` datetime not null default current_timestamp,
    primary key(`id`),
    key `key_comment_id`(`comment_id`),
    key `key_account_id_comment_id`(account_id, comment_id)
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
