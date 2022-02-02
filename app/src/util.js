export default {
  isEmail: email => {
    let emailReg = new RegExp(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/)
    return emailReg.test(email)
  },
  isPassword: password => {
    let pwdReg = new RegExp(/^[a-zA-Z0-9]\w{5,17}$/)
    return pwdReg.test(password)
  },
  commonToken: 'blog-auth-token',
  result: {
    "OK": 0,
    "ACCOUNT_NOT_FOUND": 1,
    "ACCOUNT_EXISTED": 2,
    "PASSWORD_INCORRECT": 4,
    "AUTHORIZE_FAILED": 8,
    "CAPTCHA_ERROR": 10,
    "TOKEN_EXPIRED": 15,
    "NO_PERMISSION": 16
  },
}
