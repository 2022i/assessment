
import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import router from './router';
import * as echarts from 'echarts';
import store from '@/store.js';

Vue.prototype.$echarts = echarts


Vue.use(ElementUI);

Vue.config.productionTip = false;

new Vue({
  store,
  router,
  render: h => h(App)
}).$mount('#app');

