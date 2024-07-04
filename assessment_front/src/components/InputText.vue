<template>
  <div id="app">
    <h1 class="MyName">Project Assessment System</h1>
    <div class="InputText">
      <router-link to="/register" class="register-link">注册账户</router-link>
      <input
        type="text"
        maxlength="15"
        v-model="inputValue"
        placeholder="输入你的账号"
      />
      <input
        type="password"
        maxlength="20"
        v-model="password"
        placeholder="输入你的密码"
      />
      <input
        type="text"
        maxlength="6"
        v-model="verificationCode"
        placeholder="输入验证码"
      />
      <img
        src="../assets/send.png"
        class="submit-btn"
        @click="submit"
      />
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

body {
  background: rgb(244, 189, 255);
  background: linear-gradient(
    90deg,
    rgb(205, 205, 206) 0%,
    #c7c7c9 50%,
    rgb(185, 186, 186) 100%
  );
}
.register-link {
  color: black; /* 字体颜色设置为黑色 */
  margin: 10px; /* 外边距设置 */
}

// 设置全局滚动条
body::-webkit-scrollbar {
  width: 5px;
  background-color: #f5f5f5;
}

body::-webkit-scrollbar-thumb {
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  background-color: #1d8054;
}
.InputText {
  position: fixed;
  bottom: 10px;
  left: 0;
  right: 0;
  display: flex;
  flex-direction: column; /* 更改布局为列方向 */
  align-items: center;
  justify-content: space-between;
  padding: 10px;
  background-color: transparent;
  width: 300px;
  margin: 0 auto;
  z-index: 999;
}

input[type="text"], input[type="password"] { /* 添加对 input[type="password"] 的样式 */
  width: 100%; /* 设置宽度为容器的 100% */
  margin-bottom: 10px; /* 设置下外边距 */
  height: 40px;
  padding: 0 10px;
  background-color: rgba(178, 173, 173, 0.7);
  border: 1px solid transparent;
  border-radius: 20px;
  box-shadow: 0 0 10px #3f4548;
  transition: all 0.3s ease-in-out;
}

input[type="text"]:hover, input[type="password"]:hover { /* 添加对 input[type="password"]:hover 的样式 */
  transform: scale(1.05);
  box-shadow: 0 0 15px rgba(24, 144, 255, 0.8);
}

.submit-btn {
  height: 40px;
  padding: 2px 0px 0px 10px;
  background-color: transparent;
  border: none;
  cursor: pointer;
  transform: scale(1);
  transition: all 0.3s ease-in-out;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}

.submit-btn:hover {
  transform: scale(1.2);
}
</style>

<script>
export default {
  name: 'InputText',
  data() {
    return {
      inputValue: '',
      password: '',
      verificationCode: ''
    };
  },
  methods: {
    submit() {
      console.log('submit method triggered');
      console.log('Username:', this.inputValue, 'Password:', this.password);

      if (this.inputValue === 'admin' && this.password === '1234') {
        this.inputValue = '';
        this.password = '';
        this.verificationCode = '';

        this.$message({
          message: '登录成功！',
          type: 'success',
          duration: 2000
        });

        // 使用路由跳转到 dashboard 页面
        this.$router.push({ name: 'Dashboard' });
      } else {
        this.$message({
          message: '账号或密码错误！',
          type: 'error',
          duration: 2000
        });
      }
    }
  }
};
</script>



