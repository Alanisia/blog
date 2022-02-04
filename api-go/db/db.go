package db

import (
	"blog/common"
	"database/sql"
	"fmt"

	_ "github.com/go-sql-driver/mysql"
	"xorm.io/xorm"
)

type DataSource struct {
	Username, Password, Database string
	Engine                       *xorm.Engine
	Handler                      *sql.DB
}

func (self *DataSource) url() string {
	return fmt.Sprintf("%s:%s@tcp(127.0.0.1:3306)/%s?charset=utf8&loc=Asia%%2FShanghai&parseTime=true",
		self.Username, self.Password, self.Database)
}

func (self *DataSource) Query(sql string, args ...interface{}) *sql.Rows {
	stmt, err := self.Handler.Prepare(sql)
	common.HandleError(err)
	defer stmt.Close()
	rows, err := stmt.Query(args)
	common.HandleError(err)
	return rows
}

func (self *DataSource) Execute(sql string, args ...interface{}) {
	stmt, err := self.Handler.Prepare(sql)
	common.HandleError(err)
	defer stmt.Close()
	_, err = stmt.Exec(args)
	common.HandleError(err)
}

func DefaultDb() DataSource {
	db := DataSource{
		Username: "root",
		Password: "",
		Database: "blog",
	}
	engine, err := xorm.NewEngine("mysql", db.url())
	common.PanicError(err)
	db.Engine = engine
	return db
}

var Db = DefaultDb().Engine
