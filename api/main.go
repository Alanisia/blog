package main

import (
	"blog/router"
    bv "blog/validator"

	"github.com/gin-gonic/gin"
	"github.com/gin-gonic/gin/binding"
	"github.com/go-playground/validator/v10"
)

func main() {
  r := gin.Default()


  if v, ok := binding.Validator.Engine().(*validator.Validate); ok {
    v.RegisterValidation("password", bv.CheckPasswordValid)
  }

  r.POST("/login", router.Login)
  r.POST("/register", router.Register)

  r.Run(":9300")
}
