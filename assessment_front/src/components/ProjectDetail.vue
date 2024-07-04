<template>
  <el-container style="height: 100vh;">
    <el-header class="header">
      <div class="menu-container">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
          <el-menu-item index="1">
            <router-link to="/project-detail/score">项目总览</router-link>
          </el-menu-item>
          <el-menu-item index="2">
            <router-link to="/project-detail/timeliness">及时率考核</router-link>
          </el-menu-item>
          <el-menu-item index="3">
            <router-link to="/project-detail/defect-rate">缺陷率考核</router-link>
          </el-menu-item>
          <el-menu-item index="4">
            <router-link to="/project-detail/cost-usage">人员成本考核</router-link>
          </el-menu-item>
        </el-menu>
      </div>
      <el-button type="primary" @click="goBack" class="back-button">
        返回
      </el-button>
    </el-header>
    <el-main>
      <router-view></router-view> <!-- 动态渲染路由匹配的组件 -->
    </el-main>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      activeIndex: '1'  // 默认选中第一个
    };
  },
  watch: {
    // 监听路由变化，更新 activeIndex
    $route(to) {
      const routesMap = {
        '/project-detail/score': '1',
        '/project-detail/timeliness': '2',
        '/project-detail/defect-rate': '3',
        '/project-detail/cost-usage': '4',
      };
      this.activeIndex = routesMap[to.path] || '1';
    },
  },
  created() {
    // 初始化时，根据当前路由设置 activeIndex
    const routesMap = {
      '/project-detail/score': '1',
      '/project-detail/timeliness': '2',
      '/project-detail/defect-rate': '3',
      '/project-detail/cost-usage': '4',
    };
    this.activeIndex = routesMap[this.$route.path] || '1';
  },
  methods: {
    goBack() {
      this.$router.push('/dashboard');
    }
  }
};
</script>

<style>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #B3C0D1;
  color: #333;
}

.menu-container {
  flex-grow: 1;
  display: flex;
  justify-content: center;
}

.el-menu-demo {
  flex-shrink: 0;
}

.back-button {
  margin-right: 20px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
}
</style>


