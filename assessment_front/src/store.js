import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

Vue.use(Vuex);

const registerModule = {
  state: {
    ruleForm: {
      user: '',
      email: '',
      pass: '',
      checkPass: '',
      code: ''
    },
    rules: {
      user: [{ validator: 'checkUser', trigger: 'blur' }],
      email: [{ validator: 'checkEmail', trigger: 'blur' }],
      pass: [{ validator: 'validatePass', trigger: 'blur' }],
      checkPass: [{ validator: 'validatePass2', trigger: 'blur' }],
      code: [{ validator: 'checkCode', trigger: 'blur' }]
    }
  },
  mutations: {
    SET_RULE_FORM(state, payload) {
      state.ruleForm = payload;
    },
    RESET_RULE_FORM(state) {
      state.ruleForm = {
        user: '',
        email: '',
        pass: '',
        checkPass: '',
        code: ''
      };
    }
  },
  actions: {
    async getVerificationCode() {
      try {
        const response = await axios.get('YOUR_BACKEND_API_ENDPOINT');
        console.log('验证码获取成功:', response.data);
        // 处理验证码逻辑
      } catch (error) {
        console.error('获取验证码时出错:', error);
        alert('获取验证码失败，请重试');
      }
    },
    async submitForm({ state, commit }) {
      try {
        const data = state.ruleForm;
        const response = await axios.post('/api/employee/login', data, { emulateJSON: false });
        console.log(response.data);
        alert('注册成功！');
        // 重置表单
        commit('RESET_RULE_FORM');
      } catch (error) {
        console.error('提交表单是出错:', error);
        alert('出问题啦！！！');
      }
    },
    async returnForm() {
      // 返回login界面
      this.$router.push('/');
    }
  },
  getters: {
    ruleForm: (state) => state.ruleForm,
    rules: (state) => state.rules
  }
};

export default new Vuex.Store({
  modules: {
    register: registerModule
  }
});
// --------------------------------------------------------------------------------------------