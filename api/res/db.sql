drop database if exists `blog`;
create database `blog`;
use `blog`;

drop table if exists `role`;
create table `role`(
	`id` tinyint not null,
    `name` varchar(16) not null,
    `create_at` datetime not null,
    `update_at` datetime not null,
    primary key(`id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `operation`;
create table `operation`(
	`id` tinyint not null auto_increment,
    `name` varchar(32) not null,
    `create_at` datetime not null,
    `update_at` datetime not null,
    primary key(`id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `permission`;
create table `permission`(
	`id` tinyint not null auto_increment,
    `name` varchar(32) not null,
    `role_id` tinyint not null,
    `opr_id` tinyint not null,
    `create_at` datetime not null,
    `update_at` datetime not null,
    primary key(`id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `account`;
create table `account`(
	`id` bigint not null auto_increment,
    `permission_id` tinyint not null,
    `email` varchar(255) not null,
    `username` varchar(32) not null,
    `password` varchar(20) not null,
    `create_at` datetime not null,
    `update_at` datetime not null,
    primary key(`id`),
    key `key_email`(`email`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `user_detail`;
create table `user_detail`(
	`id` bigint not null auto_increment,
    `account_id` bigint not null,
    `gender` varchar(10) not null comment 'male/female',
    `create_at` datetime not null,
    `update_at` datetime not null,
    primary key(`id`),
    key `key_account_id`(`account_id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `tag`;
create table `tag`(
	`id` tinyint not null auto_increment,
    `name` varchar(16) not null,
    `create_at` datetime not null,
    `update_at` datetime not null,
    primary key(`id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `category`;
create table `category`(
	`id` tinyint not null auto_increment,
    `name` varchar(16) not null,
    `create_at` datetime not null,
    `update_at` datetime not null,
    primary key(`id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `blog`;
create table `blog`(
	`id` bigint not null auto_increment,
    `category_id` tinyint not null,
    `tag_id` tinyint not null,
    `title` varchar(128) not null,
    `content` text not null,
    `create_at` datetime not null,
    `update_at` datetime not null,
    primary key(`id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `comment`;
create table `comment`(
	`id` bigint not null auto_increment,
    `account_id` bigint not null,
    `blog_id` bigint not null,
    `content` text not null,
    `like` int not null,
    `create_at` datetime not null,
    `update_at` datetime not null,
    primary key(`id`),
    key `key_blog_id`(`blog_id`)
)engine=InnoDB default charset=utf8mb4;

drop table if exists `reply`;
create table `reply`(
	`id` bigint not null auto_increment,
    `comment_id` bigint not null,
    `account_id` bigint not null,
    `content` text not null,
    `like` int not null,
    `create_at` datetime not null,
    `update_at` datetime not null,
    primary key(`id`),
    key `key_comment_id`(`comment_id`)
)engine=InnoDB default charset=utf8mb4;