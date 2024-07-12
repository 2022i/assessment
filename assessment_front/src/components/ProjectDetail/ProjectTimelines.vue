<template>
  <div class="project-summary-container">
    <el-card class="project-summary-card">
      <div style="margin-top: -70px; color: white;"></div>
      <div slot="header" class="clearfix">
        <div style="margin-top: -60px;"></div>
        <h2>项目及时率报告</h2>
      </div>
      <div class="chart-section">
        <!-- 考核得分 -->
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
              <th>计划开始时间</th>
              <td>{{ plannedStartTime }}</td>
            </tr>
            <tr>
              <th>计划结束时间</th>
              <td>{{ plannedEndTime }}</td>
            </tr>
            <tr>
              <th>实际开始时间</th>
              <td>{{ actualStartTime }}</td>
            </tr>
            <tr>
              <th>实际结束时间</th>
              <td>{{ actualEndTime }}</td>
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
  border-radius: 10px; 
}
.project-summary-card.el-card__header {
  padding: 10px 20px;
  border-bottom: none; 
  position: relative;  /* 为了相对定位标题 */
}
.chart-section {
  display: flex;
  justify-content: space-between;
  gap: 10px; 
}
.chart-item {
  width: calc(33.33% - 20px); 
  padding: 20px;
  background-color: #fff;
  border-radius: 10px; 
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
  line-height: 60px;  /* 减小行高 */
}
.time-table th,
.time-table td {
  border: 1px solid #ccc;
  padding: 4px; 
}
</style>

<script>
import { Card } from 'element-ui';
import * as echarts from 'echarts';
import instance from '@/api';

export default {
  name: 'ProjectTimelines',
  components: {
    [Card.name]: Card
  },
  data() {
    return {
      score: 0, // 
      ratio: 0, // 指标比例
      plannedStartTime: '',
      plannedEndTime: '',
      actualStartTime: '',
      actualEndTime: ''
    };
  },
  computed: {
    projectId() {
      return this.$store.getters['projectId'];
    },
  },
  mounted() {
    this.fetchProjectData();
  },
  methods: {
    fetchProjectData() {
      instance.get('http://127.0.0.1:8081/server/projectTime/getProjectTimeById', {
        params: {
          id: this.projectId
        }
      }).then(response => {
        const data = response.data;
        this.score = data.assessmentScore;
        this.plannedStartTime = data.pstart;
        this.plannedEndTime = data.pend;
        this.actualStartTime = data.astart;
        this.actualEndTime = data.aend;
        const weightId = data.weightId;
        // 从 Vuex 中获取 timely_rate
        const timelyRate = this.$store.state.data.find(item => item.id === weightId)?.timely_rate;
        // 计算 ratio
        this.ratio = timelyRate ? timelyRate * 100 : 0;

        this.$nextTick(() => {
          this.drawBarChart();
          this.drawPieChart();
        });
      }).catch(error => {
        console.error('Error fetching project data:', error);
      });
    },
    drawBarChart() {
      const chartDom = document.getElementById('bar-chart');
      const myChart = echarts.init(chartDom);
      const option = {
        xAxis: {
          type: 'category',
          data: ['考核得分']
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
              { value: this.ratio, name: '该指标占比', itemStyle: { color: '#409EFF' } },
              { value: 100 - this.ratio, name: '剩余指标占比', itemStyle: { color: '#67C23A' } }
            ]
          }
        ]
      };
      myChart.setOption(option);
    }
  }
};
</script>


