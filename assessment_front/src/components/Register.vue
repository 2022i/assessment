<template>
  <div class="box">
    <div class="zhuce">
      <div style="margin-top: 30px; color: white;"></div>  <!-- 注册字体改为白色 -->
      <div style="font-size: 24px; color: white;">注册</div>
      <div style="margin-top: 40px;"></div>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item>
          <el-input v-model="ruleForm.user" clearable placeholder="用户名" style="width: 400px; margin-left: -100px;"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input type="password" v-model="ruleForm.pass" clearable placeholder="账号密码" style="width: 400px; margin-left: -100px;"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input type="password" v-model="ruleForm.checkPass" clearable placeholder="确认密码" style="width: 400px; margin-left: -100px;"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="ruleForm.email" clearable placeholder="电子邮箱" style="width: 400px; margin-left: -100px;"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="ruleForm.code" clearable placeholder="验证码" style="width: 280px; margin-left: -100px;"></el-input>
          <el-button class="verification-button" style="height: 40px; margin-left: 10px; border-radius: 2px;" @click="getVerificationCode">获取验证码</el-button>  <!-- 获取验证码按钮圆角改为 2px -->
        </el-form-item>
        <el-form-item class="form-actions">
          <el-button type="primary" style="width: 400px; margin-left: -100px;" @click="submitForm">注册</el-button>
        </el-form-item>
        <p style="text-align: left; margin-left: 328px; font-size: 14px; color: white;">已有账号，<a style="color: #409eff;" @click="returnForm">登录</a></p>  <!-- 已有账号字体改为白色 -->
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
      },
      verificationButtonText: '获取验证码',
      verificationButtonDisabled: false,
      loading: false
    };
  },
  methods: {
    async getVerificationCode() {
      if (!this.ruleForm.email) {
        alert('请先输入电子邮箱');
        return;
      }

      this.verificationButtonDisabled = true;
      let countdown = 60;
      this.verificationButtonText = `${countdown}秒后重新获取`;
      const timer = setInterval(() => {
        countdown--;
        if (countdown > 0) {
          this.verificationButtonText = `${countdown}秒后重新获取`;
        } else {
          clearInterval(timer);
          this.verificationButtonText = '获取验证码';
          this.verificationButtonDisabled = false;
        }
      }, 1000);

      try {
        const response = await axios.post('http://127.0.0.1:8081/server/register/mailForRegister',
         { email: this.ruleForm.email });
        if (response.data.code === 200) {
          alert('验证码发送成功');
        } else {
          alert(response.data.msg);
          clearInterval(timer);
          this.verificationButtonText = '获取验证码';
          this.verificationButtonDisabled = false;
        }
      } catch (error) {
        console.error('获取验证码时出错:', error);
        alert('获取验证码失败，请重试');
        clearInterval(timer);
        this.verificationButtonText = '获取验证码';
        this.verificationButtonDisabled = false;
      }
    },
    async submitForm() {
      this.loading = true;
      try {
        const data = {
          username: this.ruleForm.user,
          email: this.ruleForm.email,
          password: this.ruleForm.pass,
          code: this.ruleForm.code
        };
        const response = await axios.post(' http://127.0.0.1:8081/server/register/registerByEmail', data);
        if (response.data.code === 200) {
          alert('注册成功！');
          this.resetRuleForm();
        } else {
          alert(response.data.msg);
        }
      } catch (error) {
        console.error('提交表单时出错:', error);
        alert('提交表单失败，请检查后重试');
      } finally {
        this.loading = false;
      }
    },
    returnForm() {
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
  width: 500px; /* 固定宽度 */
  height: 600px; /* 固定高度 */
  display: flex;
  justify-content: center;
  align-items: center;
  margin: auto; /* 居中对齐 */
  padding: 20px;
  background: url('@/assets/score-bg.jpg') no-repeat center center; /* 设置背景图 */
  background-size: cover; /* 背景图覆盖 */
  border: 1px solid #ccc; /* 边框 */
  border-radius: 8px; /* 圆角 */
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1); /* 阴影 */
  position: absolute; /* 绝对定位 */
  top: 50%; /* 垂直居中 */
  left: 50%; /* 水平居中 */
  transform: translate(-50%, -50%); /* 将容器中心对准视口中心 */
}

.zhuce {
  width: 100%;
  padding: 20px;
  background-color: rgba(255, 255, 255, 0); /* 半透明白色背景，增强对比 */
  border-radius: 3px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0);
}

.el-form-item {
  margin-top: 30px;
  width: 100%;
  label{
    color: rgb(8, 0, 0);
    font-size: smaller;
  }
}

.form-actions {
  display: flex;
  justify-content: space-between;
}

.verification-button {
  border-radius: 12px;
  padding: 10px 20px; /* 调整按钮的内边距 */
  background-color: #409eff;
  color: white;
  border: none;
  cursor: pointer;
  margin-left: 10px;
}

.verification-button:hover {
  background-color: #66b1ff;
}

.large-button {
  font-size: 16px; /* 调整按钮文字大小 */
}

.register-link,
.InputText input {
  color: white;
}
</style>