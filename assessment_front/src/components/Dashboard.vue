<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <el-container class="dashboard">
    <!-- Header区域 -->
    <el-header class="header">
      <div class="header-content">
        <!-- <i class="fas fa-clipboard-check header-icon"></i> -->
        <span class="header-title">项目考核系统</span>
      </div>
    </el-header>

    <!-- 主体内容区域 -->
    <el-container>
      <!-- 侧边栏 -->
      <el-aside width="200px" class="sidebar">
        <input
          placeholder="搜索项目"
          v-model="searchQuery"
          clearable
          class="dashboard-search-input"
        />
        <el-menu class="el-menu-vertical" style="height: calc(100vh - 120px); overflow-y: auto;">
          <el-menu-item v-for="project in filteredProjects" :key="project.label">
            <span class="project-name">{{ project.name }}</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <!-- 内容显示区 -->
      <el-main class="main-content">
        <el-table :data="paginatedProjects" style="width: 100%;" show-header="false">
          <!-- 隐藏表头将show-header属性设置为false -->
          <el-table-column prop="label" label="项目编号"></el-table-column>
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
          :current-page="currentPage"
          :page-size="pageSize"
          layout="prev, pager, next"
          :total="filteredProjects.length"
          class="pagination-wrapper"
        />
      </el-main>
    </el-container>
  </el-container>
</template>





<script>
export default {
  data() {
    return {
      searchQuery: "",
      projects: [], //这将由搜索结果填充
      allProjects: [
        { label: '1', name: '项目A', category: '类别A', type: '类型A' },
        { label: '2', name: '项目B', category: '类别B', type: '类型B' },
        { label: '3', name: '项目C', category: '类别C', type: '类型C' },
        { label: '4', name: '项目D', category: '类别D', type: '类型D' },
        { label: '5', name: '项目E', category: '类别E', type: '类型E' },
        { label: '6', name: '项目F', category: '类别F', type: '类型F' },
        { label: '7', name: '项目G', category: '类别G', type: '类型G' },
        { label: '8', name: '项目H', category: '类别H', type: '类型H' },
        { label: '9', name: '项目I', category: '类别I', type: '类型I' },
        { label: '10', name: '项目J', category: '类别J', type: '类型J' },
        { label: '11', name: '项目K', category: '类别K', type: '类型K' },
        { label: '12', name: '项目L', category: '类别L', type: '类型L' },
        // 更多项目可以在此处添加
      ],
      currentPage: 1,
      pageSize: 10,
    };
  },
  created() {
    this.projects = [...this.allProjects]; // 初始化项目
  },
  computed: {
    filteredProjects() {
      if (this.searchQuery) {
        return this.allProjects.filter(proj =>
          proj.name.toLowerCase().includes(this.searchQuery.toLowerCase())
        );
      }
      return this.projects;
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
      this.$router.push({ name: 'ProjectDetail', params: { projectId: row.label } });
    },
    handlePageChange(newPage) {
      this.currentPage = newPage;
    }
  },
};
</script>

<style scoped>
/* 之前已有的样式保持不变 */

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

::v-deep .el-table thead th {
  padding: 2px 4px !important; /* 进一步缩减内边距 */
  line-height: normal !important;
  height: 50px !important; /* 设定一个固定的较小高度 */
}


.header-icon {
  font-size: 24px;
}

.header-title {
  flex: 1;
  text-align: right; /* 右对齐项目考核系统标题 */
  font-weight: bold;
}

.dashboard {
  height: 100vh;
  display: flex;
  flex-direction: column;
  padding: 10px; /* 缩小外边距 */
  background-color: #e0e0e0; /* 更淡的灰色背景色 */
  border-radius: 8px; /* 圆角 */
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.header {
  background: #f2f2f2; /* 更淡的灰色背景色 */
  padding: 15px; /* 缩小内边距 */
  border-radius: 8px; /* 圆角 */
  margin-bottom: 5px; /* 缩小外边距 */
}

.sidebar {
  background: #f2f2f2; /* 更淡的灰色背景色 */
  padding: 5px; /* 缩小内边距 */
  border-radius: 8px; /* 圆角 */
  margin-right: 5px; /* 缩小外边距 */
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.el-menu-vertical {
  border: none;
}

.el-menu-item {
  border-radius: 4px; /* 子项圆角 */
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px; /* 调整项目之间的间距 */
  border-bottom: 1px solid #e0e0e0; /* 将项目之间用淡灰色线条区分 */
  background-color: #f2f2f2; /* 添加项目按钮背景色 */
}

.el-menu-item:last-child {
  border-bottom: none; /* 移除最后一项的下边框 */
}

.project-name {
  flex: 1;
  text-align: left; /* 居中对齐项目名 */
}

.main-content {
  padding: 10px; /* 缩小内边距 */
  background: #f2f2f2; /* 更淡的灰色背景色 */
  border-radius: 8px; /* 圆角 */
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  margin: 5px; /* 缩小外边距 */
}

.pagination-wrapper {
  margin-top: 20px;
  text-align: center;
}

.dashboard-search-input {
  width: 90%;
  background-color: white;
  border: 1px solid #ccc; /* 调整边框粗细 */
  border-radius: 0; /* 去掉圆角 */
  padding: 8px; /* 增加高度 */
  margin-bottom: 10px;
}


</style>
