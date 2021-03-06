import moment from "moment"

export default {
  isEmail: email => {
    let emailReg = new RegExp(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/)
    return emailReg.test(email)
  },
  isPassword: password => {
    let pwdReg = new RegExp(/^[a-zA-Z0-9]\w{5,17}$/)
    return pwdReg.test(password)
  },
  setToken: token => localStorage.setItem('blog-auth-token', token),
  getToken: () => {
    return localStorage.getItem('blog-auth-token');
  },
  setCurrentUser: id => localStorage.setItem('blog-current-user', id),
  getCurrentUser: () => {
    return parseInt(localStorage.getItem('blog-current-user'));
  },
  dateFormat: date => {
    return moment(date).format("YYYY-MM-DD HH:mm:ss");
  },
  result: {
    "OK": 0,
    "UNKNOWN_ERROR": 1,
    "ACCOUNT_NOT_FOUND": 2,
    "ACCOUNT_EXISTED": 3,
    "PASSWORD_INCORRECT": 4,
    "AUTHORIZE_FAILED": 8,
    "CAPTCHA_ERROR": 10,
    "NULL_EXCEPTION": 14,
    "TOKEN_EXPIRED": 15,
    "NO_PERMISSION": 16
  },
  success: msg => {
    return {
      message: msg,
      type: "success"
    }
  },
  error: msg => {
    return {
      message: msg,
      type: "error"
    }
  }
}
