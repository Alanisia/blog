package service

import (
	"blog/db"
	"blog/model"
	"time"

	"github.com/mojocn/base64Captcha"
	"go.uber.org/zap"
)

func Login(email, password, code, image string) (token string) {
   return token
}

func tokenAuth(token string) bool {
  return false
}

func Register(email, username, password, code, image string) bool {
  if db.AccountGetByWhere("email", email) != nil {
    
    return false
  }
  account := &model.Account{
    Email: email,
    Username: username,
    Password: password,
    CreateAt: time.Now(),
    UpdateAt: time.Now(),
  }
  db.AccountInsert(account)
  return true
}

func captchaCheck(id, b64 string) bool {
  if id == "" || b64 == "" {
    return false
  } else {
    return true
  }
}

func GenerateCaptcha() (id, b64 string) {
  store :=  base64Captcha.DefaultMemStore
  driver := base64Captcha.DefaultDriverDigit
  captcha := base64Captcha.NewCaptcha(driver, store)
  id, b64, err := captcha.Generate()
  if err != nil {}
  return id, b64
}
