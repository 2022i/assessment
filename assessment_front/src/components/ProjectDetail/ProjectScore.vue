<template>
    <div>
        <header class="header">
            <div class="header-item">项目名称：{{ projectName }}</div>
            <div class="header-item">项目种类：{{ projectType }}</div>
            <div class="header-item">项目得分：{{ projectScore }}</div>
        </header>
        <main class="main-content">
            <section class="metric-card" v-for="(metric, key) in metrics" :key="key" :class="{ 'expanded': expanded[key] }">
                <div class="metric-header" @click="toggleExpand(key)">
                    {{ metric.title }}
                    <span :class="['expand-icon', { 'expanded': expanded[key] }]">▶</span>
                </div>
                <transition name="slide">
                    <div class="metric-data" v-if="expanded[key]">
                        <div>指标权重: {{ metric.weight }}</div>
                        <div>指标得分: {{ metric.score }}</div>
                        <div>考核得分: {{ metric.assessment }}</div>
                        <div>{{ metric.customLabel }}: {{ metric.customValue }}</div>
                        <div class="chart-container">
                            <!-- 这里可以插入图表组件，例如使用Chart.js或D3.js -->
                            <!-- <chart-component :data="metric.score"></chart-component> -->
                        </div>
                    </div>
                </transition>
            </section>
        </main>
    </div>
</template>

<script>
export default {
    name: 'ProjectScore',
    data() {
        return {
            projectName: '项目A',
            projectType: '研究型项目',
            projectScore: '85',
            metrics: [
                { title: '项目及时率指标', weight: '80%', score: '90', assessment: '85', customLabel: '及时率', customValue: '95%' },
                { title: '项目缺陷率指标', weight: '75%', score: '88', assessment: '82', customLabel: '缺陷关闭率', customValue: '92%' },
                { title: '人员成本使用率指标', weight: '85%', score: '92', assessment: '87', customLabel: '成本使用率', customValue: '90%' }
            ],
            expanded: {}
        };
    },
    methods: {
        toggleExpand(key) {
            this.$set(this.expanded, key, !this.expanded[key]);
        }
    }
};
</script>

<style scoped>
.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px;
    background-color: #f5f5f5;
    border-bottom: 1px solid #ccc;
}

.header-item {
    flex: 1;
    text-align: center;
    font-size: 0.9em;
}

.main-content {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: 15px;
    margin: 15px 0;
}

.metric-card {
    padding: 10px;
    background-color: #e0e0e0;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    transition: background-color 0.3s ease-in-out;
}

.metric-card.expanded {
    background-color: #d0d0d0;
}

.metric-header {
    text-align: center;
    font-size: 1em;
    margin-bottom: 8px;
    cursor: pointer;
    display: flex;
    justify-content: center;
    align-items: center;
}

.expand-icon {
    margin-left: 5px;
    transition: transform 0.3s ease-in-out;
}

.expand-icon.expanded {
    transform: rotate(90deg);
}

.metric-data {
    display: flex;
    flex-direction: column;
    gap: 5px;
}

.metric-data div {
    padding: 5px;
    background-color: #fff;
    border: 1px solid #ddd;
    border-radius: 5px;
    font-size: 0.9em;
}

.chart-container {
    margin-top: 10px;
}

.slide-enter-active, .slide-leave-active {
    transition: height 0.3s ease-in-out;
}

.slide-enter, .slide-leave-to {
    height: 0;
    overflow: hidden;
}

@media (max-width: 600px) {
    .main-content {
        grid-template-columns: 1fr;
    }
}
</style>
