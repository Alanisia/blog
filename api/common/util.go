package common

import "regexp"

func IsPassword(pwd string) bool {
  if r, err := regexp.Compile(`^[a-zA-Z0-9]\w{5,17}$`); err != nil {
    // log.fatal
    return false
  } else {
    return r.Match([]byte(pwd))
  }
}

// func HandleError(e error) {
//   if e != nil {
//     log.Fatal(e)
//   }
// }

func PanicError(e error) {
  if e != nil {
    panic(e)
  }
}
