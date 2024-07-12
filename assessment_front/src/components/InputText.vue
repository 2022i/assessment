<template>
  <div id="app">
    <div class="background-container">
      <div class="content-container">
        <div style="margin-top: 30px; color: white;"></div>
        <h1 class="MyName">项目考核系统</h1>
        <div style="margin-top: 40px; color: white;"></div>
        <div class="InputText">
          <input
            type="text"
            maxlength="15"
            v-model="inputValue"
            placeholder="输入你的账号"
            style="border-radius: 2px; background-color: transparent; box-shadow: 0 0 5px #3f4548;"
          />
          <input
            type="password"
            maxlength="20"
            v-model="password"
            placeholder="输入你的密码"
            style="border-radius: 2px; background-color: transparent; box-shadow: 0 0 5px #3f4548;"
          />
          <div class="links-container">  <!-- 注册和忘记密码的容器 -->
            <router-link to="/register" class="register-link">立即注册</router-link>
            <router-link to="/resetpassword" class="resetpassword-link">忘记密码</router-link>
          </div>
          <div style="margin-top: 50px; color: white;"></div>
          <button class="new-submit-btn" @click="submit">登录</button>  <!-- 登录按钮 -->
          <div style="margin-top: 20px; color: white;"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.background-container {
  background-image: url('../assets/score-bg.jpg'); /* 背景图的路径 */
  background-size: cover; /* 背景图填充整个容器 */
  background-position: center center; /* 背景图居中对齐 */
  height: 100vh; /* 全屏高度 */
  display: flex;
  align-items: center;
  justify-content: center;
}

.content-container {
  background-color: rgba(255, 255, 255, 0.5); /* 白色半透明背景 */
  padding: 20px; /* 内边距 */
  border-radius: 10px; /* 圆角 */
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1); /* 阴影效果 */
  max-width: 400px; /* 最大宽度 */
  width: 100%; /* 宽度100% */
}

.MyName {
  font-size: 24px;
  margin-bottom: 20px;
}

.links-container {
  display: flex;
  justify-content: space-between;
  width: 100%;
  margin-top: 10px;
}

.register-link,.resetpassword-link {
  color: black;
  text-decoration: none;
  font-size: 14px;
}

.InputText {
  display: flex;
  flex-direction: column;
  align-items: center;
}

input[type="text"], input[type="password"] { 
  width: 100%; 
  margin-bottom: 10px; 
  height: 40px;
  padding: 0 10px;
  background-color: transparent;
  border: 1px solid gray;  
  border-radius: 2px;
  box-shadow: 0 0 5px #3f4548;
  transition: all 0.3s ease-in-out;
}

input[type="text"]:hover, input[type="password"]:hover {
  transform: scale(1.05);
  box-shadow: 0 0 15px rgba(24, 144, 255, 0.8);
}

.new-submit-btn {  
  height: 40px;
  padding: 0;
  background-color: white;  
  border: none;
  cursor: pointer;
  border-radius: 20px;  
  transform: scale(1);
  transition: all 0.3s ease-in-out;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
  width: 200px;  
  color: black;
  font-weight: bold;
}

.new-submit-btn:hover {
  transform: scale(1.2);
}
</style>

<script>
import axios from 'axios'; // 引入 axios

export default {
  name: 'InputText',
  data() {
    return {
      inputValue: '',
      password: ''
    };
  },
  methods: {
    async submit() {
      console.log('submit method triggered');
      console.log('Username:', this.inputValue, 'Password:', this.password);

      try {
        const response = await axios.post('http://localhost:8081/server/login/loginByUsername', {
          username: this.inputValue,
          password: this.password
        }, {
          headers: {
            'Content-Type': 'application/json'
          }
        });

        if (response.data.code === 200) {
          this.inputValue = '';
          this.password = '';

          // 保存 token 到本地存储
          localStorage.setItem('token', response.data.data);

          this.$message({
            message: '登录成功！',
            type: 'uccess',
            duration: 2000
          });

          // 使用路由跳转到 dashboard 页面
          this.$router.push({ name: 'Dashboard' });
        } else if (response.data.code === 401) {
          this.$message({
            message: '密码错误！',
            type: 'error',
            duration: 2000
          });
        } else if (response.data.code === 404) {
          this.$message({
            message: '不存在该用户！',
            type: 'error',
            duration: 2000
          });
        } else {
          this.$message({
            message: '登录失败，请重试！',
            type: 'error',
            duration: 2000
          });
        }
      } catch (error) {
        console.error('登录失败:', error);
        this.$message({
          message: '登录失败，请重试！',
          type: 'error',
          duration: 2000
        });
      }
    }
  }
};
</script>