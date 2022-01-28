package main

import "time"

type Role struct {
	Id       int
	Name     string
	CreateAt time.Time
	UpdateAt time.Time
}

type Operation struct {
	Id       int
	Name     string
	CreateAt time.Time
	UpdateAt time.Time
}

type Permission struct {
	Id       int
	Name     string
	RoleId   int
	OprId    int
	CreateAt time.Time
	UpdateAt time.Time
}

type Account struct {
	Id           int64
	PermissionId int
	Email        string
	Username     string
	Password     string
	CreateAt     time.Time
	UpdateAt     time.Time
}

type UserDetail struct {
	Id        int64
	AccountId int64
	Gender    string
	CreateAt  time.Time
	UpdateAt  time.Time
}

type Category struct {
	Id       int
	Name     string
	CreateAt time.Time
	UpdateAt time.Time
}

type Tag struct {
	Id       int
	Name     string
	CreateAt time.Time
	UpdateAt time.Time
}

type Blog struct {
	Id         int64
	CategoryId int
	TagId      int
	Title      string
	Content    string
	CreateAt   time.Time
	UpdateAt   time.Time
}

type Comment struct {
	Id        int64
	AccountId int64
	BlogId    int64
	Content   string
	Like      int
	CreateAt  time.Time
	UpdateAt  time.Time
}

type Reply struct {
	Id        int64
	CommentId int64
	AccountId int64
	Content   string
	Like      int
	CreateAt  time.Time
	UpdateAt  time.Time
}
