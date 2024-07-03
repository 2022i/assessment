<!-- eslint-disable vue/multi-word-component-names -->
<!-- eslint-disable vue/multi-word-component-names -->
<template>
    <div class="box">
      <div class="zhuce">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="用户名" prop="user">
            <el-input v-model="ruleForm.user" clearable></el-input>
          </el-form-item>
          <el-form-item label="电子邮箱" prop="email">
            <el-input v-model="ruleForm.email" clearable></el-input>
            <el-button class="verification-button" @click="getVerificationCode">获取验证码</el-button>
          </el-form-item>
          <el-form-item label="账号密码" prop="pass">
            <el-input type="password" v-model="ruleForm.pass" clearable></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="checkPass">
            <el-input type="password" v-model="ruleForm.checkPass" clearable></el-input>
          </el-form-item>
          <el-form-item label="验证码" prop="code">
            <el-input v-model="ruleForm.code" clearable></el-input>
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
      var checkUser = (rule, value, callback) => {
        const regUser = /^[a-zA-Z0-9_-]{3,16}$/
        if (regUser.test(value)) {
          return callback()
        }
        callback(new Error('用户名不能为空'))
      }
      var checkEmail = (rule, value, callback) => {
        const regUser = /^([a-zA-Z0-9]+[-_]?)+@[a-zA-Z0-9]+\.[a-z]+$/
        if (regUser.test(value)) {
          return callback()
        }
        callback(new Error('邮箱不能为空'))
      }
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'))
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass')
          }
          callback()
        }
      }
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'))
        } else if (value !== this.ruleForm.pass) {
          callback(new Error('两次输入密码不一致!'))
        } else {
          callback()
        }
      }
      var checkCode = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入验证码'))
        } else {
          callback()
        }
      }
      return {
        ruleForm: {
          user: '',
          email: '',
          pass: '',
          checkPass: '',
          code: ''
        },
        rules: {
          user: [{ validator: checkUser, trigger: 'blur' }],
          email: [{ validator: checkEmail, trigger: 'blur' }],
          pass: [{ validator: validatePass, trigger: 'blur' }],
          checkPass: [{ validator: validatePass2, trigger: 'blur' }],
          code: [{ validator: checkCode, trigger: 'blur' }]
        }
      }
    },
    methods: {
      returnForm() {
        // 返回login界面
        this.$router.push('/')
      },
      submitForm() {
        console.log(this.ruleForm)
        var data = this.ruleForm
        this.$http.post('/api/employee/login', data, { emulateJSON: false }).then(
          (response) => {
            console.log(response.body)
            this.grouplist = response.body
            alert('注册成功！')
            this.$router.push('/')
          },
          (response) => {
            console.log(response)
            alert('出问题啦！！！')
          }
        )
      },
      etVerificationCode() {
      console.log('获取验证码按钮点击');
      axios.get('YOUR_BACKEND_API_ENDPOINT')
        .then(response => {
          console.log('验证码获取成功:', response.data);
          // 根据返回的验证码进行相应的处理
        })
        .catch(error => {
          console.error('获取验证码时出错:', error);
          alert('获取验证码失败，请重试');
        });
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
    border-radius: 12px; /* 设置按钮圆角 */
    padding: 5px 10px;
    background-color: #409eff;
    color: white;
    border: none;
    cursor: pointer;
    margin-left: 10px; /* 设置左边距与邮箱输入框分开 */
  }
  
  .verification-button:hover {
    background-color: #66b1ff;
  }
  </style>
  