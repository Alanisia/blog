package router

import (
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/gin-gonic/gin/binding"
	"github.com/go-playground/validator/v10"
)

type LoginPost struct {
	Email        string `json:"email" binding:"required,email"`
	Password     string `json:"password" binding:"required,password"`
	CaptchaCode  string `json:"captcha_code" binding:"required"`
	CaptchaImage string `json:"captcha_image" binding:"required"`
}

type RegisterPost struct {
	Email        string `json:"email" binding:"required,email"`
	Username     string `json:"username" binding:"required"`
	Password     string `json:"password" binding:"required,password"`
	CaptchaCode  string `json:"captcha_code" binding:"required"`
	CaptchaImage string `json:"captcha_image" binding:"required"`
}

func Login(c *gin.Context) {
	var loginPost LoginPost
	if err := c.ShouldBindJSON(&loginPost); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{})
	} else {
		v := validator.New()
		if err := v.Struct(loginPost); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{})
		}
	}
	

}

func Register(c *gin.Context) {
  var registerPost RegisterPost
  if err := c.ShouldBindJSON(&registerPost); err != nil {
    c.JSON(http.StatusBadRequest, gin.H{})
  } else {
    v := validator.New()
    if err != v.Struct(registerPost); err != nil {
      c.JSON(http.StatusBadRequest, gin.H{})
    }
  }
}
