import Vue from 'vue';
import Router from 'vue-router';
import InputText from '@/components/InputText.vue';
import Dashboard from '@/components/Dashboard.vue';
import ProjectDetail from '@/components/ProjectDetail.vue';
import ProjectScore from '@/components/ProjectDetail/ProjectScore.vue';
import ProjectTimelines from '@/components/ProjectDetail/ProjectTimelines.vue';
import ProjectDefectRate from '@/components/ProjectDetail/ProjectDefectRate.vue';
import ProjectCostUsage from '@/components/ProjectDetail/ProjectCostUsage.vue';
import Register from '@/components/Register.vue'


Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: InputText
    },
    {
      path: '/dashboard',
      name: 'Dashboard',
      component: Dashboard,
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/project-detail',
      name: 'ProjectDetail',
      component: ProjectDetail,
      children: [
        {
          path: 'score',
          name: 'ProjectScore',  
          component: ProjectScore
        },
        {
          path: 'timeliness',
          name: 'ProjectTimelines',  
          component: ProjectTimelines
        },
        {
          path: 'defect-rate',
          name: 'ProjectDefectRate',  
          component: ProjectDefectRate
        },
        {
          path: 'cost-usage',
          name: 'ProjectCostUsage',  
          component: ProjectCostUsage
        }
      ]
    }
  ]
});
