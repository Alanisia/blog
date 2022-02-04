package main

import (
	"flag"
	"fmt"
	"os"

	. "github.com/dave/jennifer/jen"
)

func params(sql SQLType, name string) []Code {
	params := sql.Params
	paramCodes := []Code{}
	for k := range params {
		paramCodes = append(paramCodes, Id(name).Dot(k))
	}
	return paramCodes
}

func SQLInsert(f *File, sql SQLType) {
	f.Func().Id(sql.Model + "Insert").Params(Id("item").Id("model").Dot(sql.Model)).Block(
		Id("sql").Op(":=").Lit(sql.SQL),
        Id("Db").Dot("Execute").Call(Id("sql"), List(params(sql, "item")...)),
	)
}

func SQLUpdate(f *File, sql SQLType) {
	f.Func().Id(sql.Model + "Update").Params().Block(
		Id("sql").Op(":=").Lit(sql.SQL),
		Id("db").Dot("Execute").Call(Id("sql"), List()),
	)
}

func SQLDelete(f *File, sql SQLType) {
	f.Func().Id(sql.Model + "Delete").Params().Block(
		Id("sql").Op(":=").Lit(sql.SQL),
		Id("db").Dot("Execute").Call(Id("sql"), List()),
	)
}

func SQLSelect(f *File, sql SQLType) {
	f.Func().Id(sql.Model + "Selete").Params().Block(
		Id("sql").Op(":=").Lit(sql.SQL),
		Id("db").Dot("Execute").Call(Id("sql"), List()),
	)
}

const DEFAULT_CONFIG_PATH = "config.json"

func main() {
	flag.Parse()
	args := os.Args[1:]
	var sqls []SQLType
	if len(args) == 0 {
		sqls = Resolve(DEFAULT_CONFIG_PATH)
	} else {
		sqls = Resolve(args[0])
	}
	f := NewFile("main")
	f.Var().Id("db").Op("=").Id("Db.Handler")

	for _, v := range sqls {
		switch v.Operation {
		case INSERT:
		 SQLInsert(f, v)
            
		case UPDATE:
			// statement = SQLUpdate(v)
		case DELETE:
			// statement = SQLDelete(v)
		case SELECT:
			// statement = SQLSelect(v)

		}

	}
    fmt.Printf("%#v", f)
}
