<template>
    <div>
        <h1>人员成本</h1>
        <!-- 这里可以放置更多的HTML和绑定的数据展示 -->
    </div>
</template>

<script>
export default {
    name: 'ProjectCostUsage',
    data() {
        return {
            // 在这里定义组件的初始数据
            projectCosts: []
        };
    },
    methods: {
        // 在这里定义组件的方法
        fetchProjectCosts() {
            // 方法实现可以是通过API获取项目成本数据
            console.log('Fetching project costs...');
        }
    },
    mounted() {
        // 组件挂载后调用的方法
        this.fetchProjectCosts();
    }
}
</script>

<style scoped>
/* 在这里定义组件的样式 */
h1 {
    color: blue;
}
</style>
