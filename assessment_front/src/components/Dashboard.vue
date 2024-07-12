<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <el-container class="dashboard">
    <!-- Header区域 -->
    <el-header class="header">
      <div class="header-content">
        <span class="header-title">项目考核系统</span>
        <el-button type="danger" class="logout-button" @click="handleLogout">退出登录</el-button>
      </div>
    </el-header>

    <!-- 主体内容区域 -->
    <el-container>
      <!-- 侧边栏 -->
      <el-aside width="200px" class="sidebar">
        <div class="search-container">
          <input
            placeholder="搜索项目"
            v-model="searchQuery"
            @keyup.enter="handleSearch"
            clearable
            class="dashboard-search-input"
          />
          <el-icon class="search-icon" name="el-icon-search"></el-icon>
        </div>
        <el-menu class="el-menu-vertical" style="height: calc(100vh - 120px); overflow-y: auto;">
          <el-menu-item v-for="project in filteredProjects" :key="project.label">
            <span class="project-name">{{ project.name }}</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <!-- 内容显示区 -->
      <el-main class="main-content">
        <div style="margin-top: -120px; color: white;"></div>
        <div style="position: absolute; top: 117px; left: 249px; width: 177px; height: 20px; color: #1D2129; font-family: PingFang SC; font-weight: medium; font-size: 17px; line-height: normal; letter-spacing: 0px; text-align: left; z-index: 10;">项目编号</div> <!-- 插入项目编号的 div -->
        <div style="position: absolute; top: 117px; left: 446px; width: 177px; height: 20px; color: #1D2129; font-family: PingFang SC; font-weight: medium; font-size: 17px; line-height: normal; letter-spacing: 0px; text-align: left; z-index: 10;">项目名</div> 
        <div style="position: absolute; top: 117px; left: 643px; width: 177px; height: 20px; color: #1D2129; font-family: PingFang SC; font-weight: medium; font-size: 17px; line-height: normal; letter-spacing: 0px; text-align: left; z-index: 10;">项目分类</div> 
        <div style="position: absolute; top: 117px; left: 839px; width: 840px; height: 20px; color: #1D2129; font-family: PingFang SC; font-weight: medium; font-size: 17px; line-height: normal; letter-spacing: 0px; text-align: left; z-index: 10;">项目类型</div> 
        <div style="position: absolute; top: 117px; left: 1037px; width: 1037px; height: 20px; color: #1D2129; font-family: PingFang SC; font-weight: medium; font-size: 17px; line-height: normal; letter-spacing: 0px; text-align: left; z-index: 10;">操作</div> 
        <el-table :data="paginatedProjects" style="width: 100%;" show-header="false">

          <!-- 隐藏表头将show-header属性设置为false -->
          <el-table-column prop="label" label="项目编号" class="reduced-padding"></el-table-column>
          <el-table-column prop="name" label="项目名"></el-table-column>
          <el-table-column prop="category" label="项目分类"></el-table-column>
          <el-table-column prop="type" label="项目类型"></el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button size="mini" @click="handleAction(scope.row)">项目详情</el-button>
            </template>
          </el-table-column>
        </el-table>

        <!-- 分页控件 -->
        <el-pagination
          @current-change="handlePageChange"
          @size-change="handleSizeChange"
          :current-page="currentPage"
          :page-sizes="[10, 20, 30, 40]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="filteredProjects.length"
          class="pagination-wrapper"
        />
      </el-main>
    </el-container>
  </el-container>
</template>

<style scoped>
.dashboard {
  background-color: #f0f2f5;
  display: flex;
  flex-direction: column;
  padding: 10px;  /* 减少整体的内边距 */
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.header {
  background: url('@/assets/header-bg.jpg');
  padding: 10px;  /* 减少头部内边距 */
  border-radius: 8px;
  margin-bottom: 5px;  /* 减少头部与下方内容的间距 */
  justify-content: center; 
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}

.logout-button {
  margin-left: 10px;  /* 减少按钮的左边距 */
}

.sidebar {
  background-color: #fff;  
  color: black;  
  padding: 5px;  /* 减少侧边栏内边距 */
  border-radius: 8px;
  margin-right: 5px;  /* 减少侧边栏与内容区的间距 */
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.el-menu-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 5px;  /* 减少菜单项内边距 */
  border-bottom: 1px solid #f4f3f3; 
  background-color: #fff; 
}

.el-menu-item:hover,
.el-menu-item:active {
  background-color: #10aff3; 
}

.el-menu-item:last-child {
  border-bottom: none; 
}

.main-content {
  background-color: #f0f2f5;
  padding: 10px;  /* 减少内容区的内边距 */
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  margin: 5px;  /* 减少内容区与侧边栏的间距 */
}

.header-title {
  font-size: 20px;  /* 减小标题字体大小 */
  font-weight: bold;
  color: white;
}

.project-name {
  font-size: 12px;  /* 减小项目名称字体大小 */
  text-align: left;
  color: #070606;
}

.el-button {
  background-color: #5bacfd;
  color: #ffffff;
  border: none;
  padding: 5px 10px;  /* 减小按钮内边距 */
}

.el-button:hover {
  background-color: #66b1ff;
}

.search-container {
  position: relative;
  display: flex;
  align-items: center;
}

.dashboard-search-input {
  height: 15px;  /* 减小搜索输入框高度 */
  width: 90%;
  background-color: white;
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 5px;  /* 减小输入框内边距 */
  margin-bottom: 5px;  /* 减少搜索框与下方的间距 */
}

.search-icon {
  position: absolute;
  right: 5px;  /* 调整搜索图标位置 */
  cursor: pointer;
  color: #ccc; 
}

.reduced-padding {
  padding-top: 0;
  padding-bottom: 0;
}

/* 新增样式 */
.el-table th,.el-table td {
  text-align: center;
  padding: 5px;  /* 减小表格单元格内边距 */
}

.pagination-wrapper {
  display: flex;
  justify-content: center;
  margin-top: 10px;  /* 减少分页控件与上方的间距 */
}
.pagination-wrapper {
  margin-left: 500px;  /* 往左增加 100px 的空白，实现往右移动的效果 */
}

</style>


<script>
import { mapGetters } from 'vuex';
import instance from '@/api';

export default {
  data() {
    return {
      searchQuery: "",
      currentPage: 1,
      pageSize: 10,
      searchResults: [],
    };
  },
  computed: {
    ...mapGetters(['allProjects']),
    filteredProjects() {
      if (this.searchQuery) {
        return this.searchResults;
      }
      return this.allProjects;
    },

    paginatedProjects() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.filteredProjects.slice(start, end);
    },
  },
  methods: {
    handleAction(row) {
      // 定义操作按钮的具体功能
      this.$store.dispatch('updateProjectId', row.label);
      this.$router.push({ name: 'ProjectDetail' });
    },
    handlePageChange(newPage) {
      this.currentPage = newPage;
    },
    handleSizeChange(newSize) {
      this.pageSize = newSize;
      this.currentPage = 1; // 重置到第一页
    },
    handleLogout() {
      const token = localStorage.getItem('token'); // 假设token存储在localStorage中
      instance.post('http://127.0.0.1:8081/server/out/signOut', null, {
        headers: {
          'X-Token': token
        }
      }).then(response => {
        if (response.data.code === 200) {
          localStorage.removeItem('token'); // 清除token
          alert('已退出登录'); // 显示退出登录成功的消息
          this.$router.push({ name: 'Home' }); // 跳转到登录页面
        }
      }).catch(error => {
        console.error('退出登录失败', error);
      });
    },
    handleSearch() {
      if (this.searchQuery) {
        instance.get('http://127.0.0.1:8081/server/search/fuzzySearchProjectByName', {
          params: {
            name: this.searchQuery
          }
        }).then(response => {
          console.log(response.data); // 打印搜索结果
          this.searchResults = response.data.map(project => ({
            label: project.id,
            name: project.name || 'Unknown',
            category: project.typesSection || 'Uncategorized',
            type: project.projectType || 'Unknown'
          }));
          this.currentPage = 1; // 重置分页到第一页
        }).catch(error => {
          console.error('搜索项目失败', error);
        });
      } else {
        this.searchResults = this.allProjects;
      }
    }
  },
  created() {
    this.$store.dispatch('fetchProjects');
  },
};
</script>

