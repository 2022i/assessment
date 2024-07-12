<template>
  <div class="project-score">
    <div class="header">
      <div class="header-item"> 项目名称: {{ projectDetails.name }}</div>
      <div class="header-item">项目种类：{{ projectDetails.projectType }}</div>
      <div class="header-item">项目得分：{{ projectDetails.totalScore }}</div>
    </div>

    <div class="main-content">
      <div class="metrics-container">
        <div class="metric-card" v-for="(metric, key) in metrics" :key="key" :style="{ margin: '0 10px' }">  <!-- 减小板块间距，原为 20px，现改为 10px -->
          <div class="metric-header">
            <div style="height: 30px; line-height: 30px; text-align: center;">{{ metric.title }}</div>
          </div>
          <div class="metric-data">
            <div style="line-height: 62px; font-size: 14px;">指标权重: {{ metric.weight }}</div>  <!-- 减小字体 -->
            <div style="line-height: 62px; font-size: 14px;">指标得分: {{ metric.score }}</div>  <!-- 减小字体 -->
            <div style="line-height: 62px; font-size: 14px;">考核得分: {{ metric.assessment }}</div>  <!-- 减小字体 -->
            <div style="line-height: 62px; font-size: 14px;">{{ metric.customLabel }}: {{ metric.customValue.toFixed(2) }}%</div>  <!-- 减小字体 -->
            <div class="chart-container">
              <!-- 这里可以插入图表组件，例如使用 Chart.js 或 D3.js -->
              <!-- <chart-component :data="metric.score"></chart-component> -->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.project-score {
  font-family: 'Arial', sans-serif;
  margin: 20px auto;
  padding: 10px;
  background-color: #fff;
  max-width: 800px;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  background: url('@/assets/score-bg.jpg');
  border-bottom: 1px solid #ccc;
  flex-wrap: wrap;
}

.header-item {
  flex: 1 1 33%;
  text-align: center;
  font-size: 1em;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
 


.main-content {
  margin-top: 20px;
}

.metrics-container {
  display: flex;
  justify-content: space-around;
}

.metric-card {
  background-color: #fff;
  border: 1px solid transparent;  
  padding: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  border-radius: 4px;
  width: 30%;
  min-height: 200px;
}

.metric-header {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 15px;
  background-color: #fff;
  border-bottom: 1px solid #ccc;
}

.metric-data {
  padding: 15px;
}

.chart-container {
  margin-top: 15px;
}
</style>

<script>
import { mapState, mapActions } from 'vuex';

export default {
  name: 'ProjectScore',

  data() {
    return {
      metrics: [
        { title: '项目及时率指标', weight: '', score: '', assessment: '', customLabel: '项目及时率', customValue: '' },
        { title: '项目缺陷率指标', weight: '', score: '', assessment: '', customLabel: '缺陷关闭率', customValue: '' },
        { title: '人员成本使用率指标', weight: '', score: '', assessment: '', customLabel: '成本使用率', customValue: '' }
      ]
    };
  },
  computed: {
    projectId() {
      return this.$store.getters['projectId'];
    },
...mapState(['projectDetails'])
  },
  methods: {
...mapActions(['fetchProjectDetails'])
  },
  watch: {
    projectDetails(newDetails) {
      if (newDetails) {
        this.metrics[0].score = newDetails.ist;
        this.metrics[0].assessment = Math.min(newDetails.ast, 100);
        this.metrics[0].customValue = newDetails.timelinessRate * 100;
        this.metrics[1].score = newDetails.isd;
        this.metrics[1].assessment = Math.min(newDetails.asd, 100);
        this.metrics[1].customValue = newDetails.defectCloseRate * 100;
        this.metrics[2].score = newDetails.isu;
        this.metrics[2].assessment = Math.min(newDetails.asu, 100);
        this.metrics[2].customValue = newDetails.usageRate * 100;
        const weightId = newDetails.wd;
        this.metrics[0].weight = (this.$store.state.data.find(item => item.id === weightId)?.timely_rate * 100) + '%';
        this.metrics[1].weight = (this.$store.state.data.find(item => item.id === weightId)?.defect * 100) + '%';
        this.metrics[2].weight = (this.$store.state.data.find(item => item.id === weightId)?.utilization * 100) + '%';
      }
    }
  },
  mounted() {
    this.fetchProjectDetails(this.projectId);
  }
};
</script>