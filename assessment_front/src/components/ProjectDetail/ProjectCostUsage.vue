<template>
  <div class="project-summary-container">
    <el-card class="project-summary-card">
      <div style="margin-top: -70px; color: white;"></div>
      <div slot="header" class="clearfix card-header">
        <div style="margin-top: -60px;"></div>
        <h2>人员成本使用率报告</h2>
      </div>
      <div class="chart-section">
        <!-- 指标得分 -->
        <div class="chart-item">
          <div style="margin-top: -60px;"></div>
          <h3>考核得分：</h3>
          <div id="bar-chart" class="chart"></div>
        </div>
        <!-- 指标占比 -->
        <div class="chart-item">
          <div style="margin-top: -60px;"></div>
          <h3>指标占比：</h3>
          <div id="pie-chart" class="chart"></div>
        </div>
        <!-- 项目计划和实际时间 -->
        <div class="chart-item">
          <div style="margin-top: -60px;"></div>
          <h3>项目计划和实际时间：</h3>
          <table class="time-table">
            <tr>
              <th>计划人员成本</th>
              <td>{{ pStaff }}</td>
            </tr>
            <tr>
              <th>实际人员成本</th>
              <td>{{ aStaff }}</td>
            </tr>
            <tr>
              <th>人员成本使用率</th>
              <td>{{ usageRate }}</td>
            </tr>
            <tr>
              <th>指标得分</th>
              <td>{{ indicatorScore }}</td>
            </tr>
            <tr>
              <th>考核得分</th>
              <td>{{ assessmentScore }}</td>
            </tr>
          </table>
        </div>
      </div>
    </el-card>
  </div>
</template>

<style scoped>
.project-summary-container {
  padding: 20px;
}
.project-summary-card {
  border-radius: 10px; /* 添加圆角 */
}
.project-summary-card .el-card__header {
  padding: 5px 10px; /* 缩小内边距 */
  border-bottom: none; /* 移除底部边框 */
}
.card-title {
  font-size: 30px; /* 缩小字体 */
}
.chart-section {
  display: flex;
  justify-content: space-between;
  gap: 10px; /* 增加间距 */
}
.chart-item {
  width: calc(33.33% - 20px); /* 使宽度等分并减去间距 */
  padding: 20px;
  background-color: #fff;
  border-radius: 10px; /* 添加圆角 */
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.chart-item h3 {
  margin-bottom: 10px;
}
.chart {
  width: 100%;
  height: 300px;
}
.time-table {
  width: 100%;
  border-collapse: collapse;
  border-radius: 10px; 
  overflow: hidden; 
  margin-bottom: 20px;
  font-size: 12px; 
  line-height: 45px;  /* 减小行高 */
}
.time-table th,
.time-table td {
  border: 1px solid #ccc;
  padding: 4px; /* 调整单元格内边距 */
}
</style>

<script>
import { Card } from 'element-ui';
import * as echarts from 'echarts';
import instance from '@/api';

export default {
  name: 'ProjectCostUsage',
  components: {
    [Card.name]: Card
  },
  data() {
    return {
      score: 0, // 考核得分
      ratio: 0, // 指标占比
      pStaff: 0, // 计划人员成本
      aStaff: 0, // 实际人员成本
      usageRate: 0, // 人员成本使用率
      indicatorScore: 0,  // 指标得分
      assessmentScore: 0, // 考核得分
    };
  },
  computed: {
    projectId() {
      return this.$store.getters['projectId'];
    },
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      instance.get('http://127.0.0.1:8081/server/utilization/getUtilization', {
        params: {
          id: this.projectId
        }
      }).then(response => {
        const data = response.data;
        this.pStaff = data.pstaff;
        this.aStaff = data.astaff;
        this.usageRate = data.usageRate;
        this.indicatorScore = data.indicatorScore;
        this.assessmentScore = Math.min(data.assessmentScore,100);
        this.score = Math.min(data.assessmentScore,100);
        const weightId = data.weightId;
        // 从 Vuex 中获取 timely_rate
        const utilization = this.$store.state.data.find(item => item.id === weightId)?.utilization;
        // 计算 ratio
        this.ratio = utilization ? utilization* 100 : 0;

        this.$nextTick(() => {
          this.drawBarChart();
          this.drawPieChart();
        });
      }).catch(error => {
        console.error('Error fetching data:', error);
      });
    },
    drawBarChart() {
      const chartDom = document.getElementById('bar-chart');
      const myChart = echarts.init(chartDom);
      const option = {
        xAxis: {
          type: 'category',
          data: ['指标得分']
        },
        yAxis: {
          type: 'value',
          max: 100
        },
        series: [
          {
            data: [this.score],
            type: 'bar',
            barWidth: '50%',
            label: {
              show: true,
              position: 'top',
              formatter: '{c}'
            },
            itemStyle: {
              color: '#409EFF'
            }
          }
        ]
      };
      myChart.setOption(option);
    },
    drawPieChart() {
      const chartDom = document.getElementById('pie-chart');
      const myChart = echarts.init(chartDom);
      const option = {
        tooltip: {
          trigger: 'item'
        },
        legend: {
          top: '5%',
          left: 'center'
        },
        series: [
          {
            name: '指标占比',
            type: 'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: '40',
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: [
              { value: this.ratio, name: '该指标占比' },
              { value: 100 - this.ratio, name: '剩余指标占比' }
            ]
          }
        ]
      };
      myChart.setOption(option);
    }
  }
};
</script>



 