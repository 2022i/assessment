<template>
  <el-container style="height: 100vh;">
    <el-container>
      <el-header class="header">
        <div class="header-container">
          <div class="left-container">
            <div v-if="showDropdown" class="custom-select">
              <select v-model="selectedRate">
                <option value="" disabled selected>选择你想要分配的考核比例</option>
                <option v-for="item in data" :key="item.id" :value="item.id">
                  及时: {{ item.timely_rate }}, 缺陷: {{ item.defect }}, 成本: {{ item.utilization }}
                </option>
              </select>
            </div>
          </div>
          <div class="center-container">
            <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
              <el-menu-item index="1">
                <router-link to="/project-detail/score" class="router-link-active">项目总览</router-link>
              </el-menu-item>
              <el-menu-item index="2">
                <router-link to="/project-detail/timeliness" class="router-link-active">及时率考核</router-link>
              </el-menu-item>
              <el-menu-item index="3">
                <router-link to="/project-detail/defect-rate" class="router-link-active">缺陷率考核</router-link>
              </el-menu-item>
              <el-menu-item index="4">
                <router-link to="/project-detail/cost-usage" class="router-link-active">人员成本考核</router-link>
              </el-menu-item>
            </el-menu>
          </div>
          <div class="right-container">
            <el-button type="primary" @click="goBack" class="back-button">
              返回
            </el-button>
            <el-button v-if="showSubmitButton" type="primary" @click="submitChange" class="submit-button">
              提交修改
            </el-button>
          </div>
        </div>
      </el-header>
      <el-main>
        <router-view></router-view> <!-- 动态渲染路由匹配的组件 -->
      </el-main>
    </el-container>
  </el-container>
</template>

<style>
.header {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #B3C0D1;
  color: #333;
  border-radius: 5px; 
  padding: 5px; 
  margin: 5px; 
  background-color: #F0F0F0; 
}

.header-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}

.left-container,.center-container,.right-container {
  display: flex;
  align-items: center;
}

.left-container {
  justify-content: center;
  flex: 1;
}

.center-container {
  justify-content: center;
  flex: 2;
}

.right-container {
  justify-content: flex-end;
  flex: 1;
}

.custom-select {
  position: relative;
  width: 300px;
}

.custom-select select {
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  background-color: #fff;
  border: 1px solid #999;
  border-radius: 5px;
  padding: 12px 15px;
  font-size: 14px;
  outline: none;
  width: 100%;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.custom-select::after {
  content: '\25BC';
  position: absolute;
  top: 50%;
  right: 10px;
  transform: translateY(-50%);
  pointer-events: none;
  color: #999;
}

.custom-select select option {
  padding: 10px 15px;
  width: 300px;
  display: flex;
  justify-content: space-between;
}

.custom-select select option span:first-child {
  flex: 1;
}

.custom-select select option span:last-child {
  flex: 1;
  text-align: right;
}

.el-menu-item {
  text-decoration: none!important; 
}

.router-link-active {
  text-decoration: none;
}

.el-menu-item a {
  font-size: 16px;
  font-weight: bold;
}

.back-button,.submit-button {
  border-radius: 5px; 
  padding: 5px 10px; 
  margin: 5px; 
  width: 80px; 
}

.el-main {
  background-color: #F0F0F0; 
  color: #333;
  text-align: center;
  line-height: 160px;
  border-radius: 5px; 
  padding: 5px; 
  margin: 5px; 
  font-size: 18px;
  font-weight: bold;
}

.el-menu-item:hover {
  background-color: #409eff; 
  color: white; 
}

/* 新增样式 */
.el-menu-item .router-link-active {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
}
</style>

<script>
/* eslint-disable no-unused-vars */
import { mapState } from 'vuex';
import instance from '@/api.js';// 确保路径正确
/* eslint-enable no-unused-vars */

export default {
  data() {
    return {
      activeIndex: '1',  // 默认选中第一个
      selectedRate: '',  // 选中的比例
      showDropdown: false, // 控制下拉框的显示与隐藏
      showSubmitButton: true, // 控制提交修改按钮的显示与隐藏
      projects: [
        { id: 1, name: '项目A' },
        { id: 2, name: '项目B' },
        // 其他项目
      ],
    };
  },
  computed: {
    ...mapState(['projectId']),
    ...mapState(['data'])
  },
  mounted() {
    console.log('Data from Vuex:', this.data);
    this.updateDropdownVisibility(this.$route);
  },
  watch: {
    // 监听路由变化，更新 activeIndex 和下拉框的显示与隐藏
    $route(to) {
      const routesMap = {
        '/project-detail/score': '1',
        '/project-detail/timeliness': '2',
        '/project-detail/defect-rate': '3',
        '/project-detail/cost-usage': '4',
      };
      this.activeIndex = routesMap[to.path] || '1';
      this.updateDropdownVisibility(to);
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

    // 默认渲染 score 组件
    if (this.$route.path === '/project-detail') {
      this.$router.replace('/project-detail/score');
    }

    this.updateDropdownVisibility(this.$route);
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    goBack() {
      this.$router.push('/dashboard');
    },
    async submitChange() {
      const projectId = parseInt(this.projectId, 10);
      const weightId = this.selectedRate; // 修正拼写错误

      try {
        const response = await instance.post('http://127.0.0.1:8081/server/change/changeWeight', {
          projectId,
          weightId
        }, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded' // 修正请求头
          }
        });

        if (response.data.code === 200) {
          alert('操作成功');
          this.$store.dispatch('fetchProjectDetails', projectId);
        } else {
          alert('操作失败');
        }

        console.log('Response:', response.data);
      } catch (error) {
        console.error('Error:', error);
        alert('操作失败');
      }
    },
    updateDropdownVisibility(route) {
      this.showDropdown = route.path === '/project-detail/score';
      this.showSubmitButton = route.path === '/project-detail/score'; // 新增逻辑
    }
  }
};
</script>
