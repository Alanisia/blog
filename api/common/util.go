package common

import "log"

func HandleError(e error) {
  if e != nil {
    log.Fatal(e)
  }
}

func PanicError(e error) {
  if e != nil {
    panic(e)
  }
}
