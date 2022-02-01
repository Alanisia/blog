package db

import (
	"blog/model"
)

func AccountList() {
}

func AccountDelete(id int64) {
	account := new(model.Account)
	if _, err := Db.ID(id).Delete(account); err != nil {
	}
}

func AccountUpdate(id int64, account *model.Account) {
	if _, err := Db.ID(id).Update(account); err != nil {
	}
}

func AccountInsert(account *model.Account) {
	if _, err := Db.Insert(account); err != nil {
	}
}

func AccountGetByPrimary(id int64) *model.Account {
	account := new(model.Account)
	if has, err := Db.ID(id).Get(account); err != nil {
	} else {
		if has {
			return account
		}
	}
	return nil
}

func AccountGetByWhere(where, value string) *model.Account {
	account := new(model.Account)
	if has, err := Db.Where(where+"=?", value).Get(account); err != nil {

	} else {
		if has {
			return account
		}
	}
	return nil
}
