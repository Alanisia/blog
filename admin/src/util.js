export default {
  isEmail: email => {
    let emailReg = new RegExp(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/)
    return emailReg.test(email)
  },
  isPassword: password => {
    let pwdReg = new RegExp(/^[a-zA-Z0-9]\w{5,17}$/)
    return pwdReg.test(password)
  }
}
