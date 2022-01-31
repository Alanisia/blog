package validator

import (
	"blog/common"

	"github.com/go-playground/validator/v10"
)

func CheckPasswordValid(fl validator.FieldLevel) bool {
  value := fl.Field().String()
  return common.IsPassword(value)
}
