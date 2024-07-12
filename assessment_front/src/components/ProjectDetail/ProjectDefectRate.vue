<template>
  <div class="project-summary-container">
    <el-card class="project-summary-card">
      <div style="margin-top: -70px; color: white;"></div>
      <div slot="header" class="clearfix">
        <div style="margin-top: -60px;"></div>
        <h2>项目缺陷率报告</h2>
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
        <div class="chart-item">
          <div style="margin-top: -60px;"></div>
          <h3>缺陷统计：</h3>
          <table class="time-table">
            <thead>
              <tr>
                <th>缺陷率</th>
                <th>指标得分</th>
                <th>缺陷关闭率</th>
                <th>考核得分</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>{{  defectRate     }}</td>
                <td>{{  indicatorScore }}</td>
                <td>{{  defectCloseRate }}</td>
                <td>{{  assessmentScore}}</td>
              </tr>
            </tbody>
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
  line-height: 125px;  /* 减小行高 */
}
.time-table th,
.time-table td {
  border: 1px solid #ccc;
  padding: 4px; 
  text-align: center; 
}
</style>


<script>
import { Card } from 'element-ui';
import * as echarts from 'echarts';
import instance from '@/api';

export default {
  name: 'ProjectDefectRate',
  components: {
    [Card.name]: Card
  },
  data() {
    return {
      score: 0, //考核得分
      ratio: 0, // 指标比例
      //用于可视化
      defectRate: 0, // 缺陷率
      indicatorScore:  0,// 缺陷率指标得分
      defectCloseRate: 0,// 缺陷关闭率
      indicatorScoreC: 0,//缺陷关闭率指标得分
      assessmentScore: 0,//考核得分
  
    };
  },
  computed: {
    projectId() {
      return this.$store.getters['projectId'];
    },
  },
  mounted() {
    this.fetchDefectData();
  },
  methods: {
    fetchDefectData() {
      instance.get('http://127.0.0.1:8081/server/defect/getDefectById', {
        params: {
          id: this.projectId
        }
      }).then(response => {
        const data = response.data;
        this.indicatorScore = data.indicatorScore 
        this.defectCloseRate = data.defectCloseRate
        this.indicatorScoreC = data.indicatorScoreC
        this.assessmentScore = data.assessmentScore
        this.score = data.assessmentScore
        const weightId = data.weightId;
        const defect = this.$store.state.data.find(item => item.id === weightId)?.defect;
        // 计算 ratio
        this.ratio = defect ? defect * 100 : 0;

        this.$nextTick(() => {
          this.drawBarChart();
          this.drawPieChart();
        });
      }).catch(error => {
        console.error('Error fetching defect data:', error);
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

