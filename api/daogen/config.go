package main

import (
	"encoding/json"
	"io/ioutil"
)

const (
	INSERT = "insert"
	UPDATE = "update"
	DELETE = "delete"
	SELECT = "select"
)

type Data struct {
	Data []SQLType `json:"data"`
}

type SQLType struct {
	SQL       string            `json:"sql"`
	Model     string            `json:"model"`
	Operation string            `json:"operation"`
	Params    map[string]string `json:"params"`
}

func readJSONFile(path string) []byte {
	bts, err := ioutil.ReadFile(path)
	if err != nil {
		panic(err)
	}
	return bts
}

func Resolve(path string) []SQLType {
	var data Data
	if err := json.Unmarshal(readJSONFile(path), &data); err != nil {
		panic(err)
	}
	return data.Data
}
