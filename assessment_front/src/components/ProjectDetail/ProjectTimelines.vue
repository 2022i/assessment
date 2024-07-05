<template>
    <div class="project-summary-container">
      <el-card class="project-summary-card">
        <div slot="header" class="clearfix">
          <h2>项目及时率报告</h2>
        </div>
        <div class="chart-section">
          <!-- 指标得分 -->
          <div class="chart-item">
            <h3>指标得分：</h3>
            <div id="bar-chart" class="chart"></div>
          </div>
          <!-- 指标占比 -->
          <div class="chart-item">
            <h3>指标占比：</h3>
            <div id="pie-chart" class="chart"></div>
          </div>
          <!-- 项目计划和实际时间 -->
          <div class="chart-item">
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
  
  <script>
  import { Card } from 'element-ui';
  import * as echarts from 'echarts';
  
  export default {
    name: 'ProjectTimelines',
    components: {
      [Card.name]: Card
    },
    data() {
      return {
        /* 假数据，未来需要通过后端接口获取 */
        score: 80, // 指标得分
        ratio: 40, // 指标比例
        
        /* 项目时间假数据 */
        plannedStartTime: '2024年4月18日',
        plannedEndTime: '2024年6月18日',
        actualStartTime: '2024年4月30日',
        actualEndTime: '2024年5月30日'
      };
    },
    mounted() {
      this.$nextTick(() => {
        this.drawBarChart();
        this.drawPieChart();
      });
    },
    methods: {
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
  
  <style scoped>
  .project-summary-container {
    padding: 20px;
  }
  .project-summary-card {
    border-radius: 10px; /* 添加圆角 */
  }
  .project-summary-card .el-card__header {
    padding: 10px 20px;
    border-bottom: none; /* 移除底部边框 */
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
    border-radius: 10px; /* 添加圆角 */
    overflow: hidden; /* 圆角修剪 */
    margin-bottom: 20px;
    font-size: 12px; /* 调整字体大小 */
  }
  .time-table th,
  .time-table td {
    border: 1px solid #ccc;
    padding: 4px; /* 调整单元格内边距 */
  }
  </style>
  