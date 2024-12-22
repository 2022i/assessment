import axios from 'axios';

// 创建Axios实例
const instance = axios.create({
  headers: {
    'Content-Type': 'application/json'
  }
});

// 请求拦截器
instance.interceptors.request.use(config => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers['X-Token'] = token;
  }
  return config;
}, error => {
  return Promise.reject(error);
});

// 响应拦截器
instance.interceptors.response.use(response => {
  return response;
}, error => {
  return Promise.reject(error);
});



export default instance;
