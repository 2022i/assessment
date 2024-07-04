<template>
    <div>
        <!-- 这里可以放置更多的HTML和绑定的数据展示 -->
        <h1>{{ message }}</h1>
        <button @click="handleClick">点击我</button>
    </div>
</template>

<script>
export default {
    name: 'ProjectTimelines', // 组件名，可以根据需要修改
    data() {
        return {
            message: '项目及时率'
        };
    },
    methods: {
        handleClick() {
            alert(this.message);
        }
    },
    mounted() {
        console.log('组件已挂载');
    }
};
</script>

<style scoped>
/* 样式代码，这里使用 scoped 确保样式只应用于当前组件 */
div {
    text-align: center;
}

button {
    margin-top: 20px;
}
</style>
