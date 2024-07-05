<template>
  <div class="box">
    <div class="zhuce">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="user">
          <el-input v-model="ruleForm.user" clearable></el-input>
        </el-form-item>
        <el-form-item label="账号密码" prop="pass">
          <el-input type="password" v-model="ruleForm.pass" clearable></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input type="password" v-model="ruleForm.checkPass" clearable></el-input>
        </el-form-item>
        <el-form-item label="电子邮箱" prop="email">
          <el-input v-model="ruleForm.email" clearable></el-input>
          <el-button class="verification-button" @click="getVerificationCode">获取验证码</el-button>
        </el-form-item>
        <el-form-item label="验证码" prop="code">
          <input v-model="ruleForm.code" class="code-input" />
        </el-form-item>
        <el-form-item class="form-actions">
          <el-button type="primary" @click="submitForm">注册</el-button>
          <el-button @click="returnForm">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Register',
  data() {
    return {
      ruleForm: {
        user: '',
        email: '',
        pass: '',
        checkPass: '',
        code: ''
      },
      rules: {
        user: [{ validator: this.checkUser, trigger: 'blur' }],
        email: [{ validator: this.checkEmail, trigger: 'blur' }],
        pass: [{ validator: this.validatePass, trigger: 'blur' }],
        checkPass: [{ validator: this.validatePass2, trigger: 'blur' }],
        code: [{ validator: this.checkCode, trigger: 'blur' }]
      }
    };
  },
  methods: {
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
    async submitForm() {
      try {
        const data = this.ruleForm;
        const response = await axios.post('/api/employee/login', data, { emulateJSON: false });
        console.log(response.data);
        alert('注册成功！');
        // 重置表单
        this.resetRuleForm();
      } catch (error) {
        console.error('提交表单时出错:', error);
        alert('出问题啦！！！');
      }
    },
    returnForm() {
      // 返回login界面
      this.$router.push('/');
    },
    resetRuleForm() {
      this.ruleForm = {
        user: '',
        email: '',
        pass: '',
        checkPass: '',
        code: ''
      };
    },
    checkUser(rule, value, callback) {
      if (!value) {
        return callback(new Error('请输入用户名'));
      }
      callback();
    },
    checkEmail(rule, value, callback) {
      const emailRegex = /^([\w-]+)@([\w-]+\.)+[\w-]{2,4}$/;
      if (!value) {
        return callback(new Error('请输入电子邮箱'));
      } else if (!emailRegex.test(value)) {
        return callback(new Error('请输入有效的电子邮箱'));
      }
      callback();
    },
    validatePass(rule, value, callback) {
      const passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]).{6,}$/;
      if (value === '') {
        callback(new Error('请输入密码'));
      } else if (!passwordRegex.test(value)) {
        callback(new Error('密码必须包含数字、字母和符号，且不少于6位'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    },
    validatePass2(rule, value, callback) {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    },
    checkCode(rule, value, callback) {
      if (!value) {
        return callback(new Error('请输入验证码'));
      }
      callback();
    }
  }
}
</script>

<style lang="scss" scoped>
.box {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
}
.zhuce {
  width: 450px;
  padding: 20px;
  background-color: #fff;
  border-radius: 3px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}
.el-form-item {
  margin-top: 20px;
  width: 100%;
}
.form-actions {
  display: flex;
  justify-content: space-between;
}
.verification-button {
  border-radius: 12px;
  padding: 5px 10px;
  background-color: #409eff;
  color: white;
  border: none;
  cursor: pointer;
  margin-left: 10px;
}
.verification-button:hover {
  background-color: #66b1ff;
}
.code-input {
  width: 50%;
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 8px;
  font-size: 14px;
}
</style>
