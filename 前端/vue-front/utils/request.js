import axios from 'axios'
import cookie from 'js-cookie'
import { MessageBox, Message } from 'element-ui'

const service = axios.create({
    baseURL: 'http://localhost:8088',
    timeout: 20000
})

// http request 拦截器
service.interceptors.request.use(
  config => {
  //debugger
  if (cookie.get('guli_token')) {
    
    config.headers['token'] = cookie.get('guli_token');
  }
    return config
  },
  err => {
  return Promise.reject(err);
})



export default service