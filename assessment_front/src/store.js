// src/store/index.js
import Vue from 'vue';
import Vuex from 'vuex';
import instance from './api';

Vue.use(Vuex);

const API_URL = process.env.VUE_APP_API_URL || 'http://127.0.0.1:8081';

export default new Vuex.Store({
  state: {
    selectedProject: localStorage.getItem('selectedProject') ? JSON.parse(localStorage.getItem('selectedProject')) : null,
    projectId: localStorage.getItem('projectId') ? parseInt(localStorage.getItem('projectId')) : null,
    projects: [],
    data: [
      { id: 1, timely_rate: 0.33, defect: 0.33, utilization: 0.33 },
      { id: 2, timely_rate: 0.50, defect: 0.25, utilization: 0.25 },
      { id: 3, timely_rate: 0.25, defect: 0.50, utilization: 0.25 },
      { id: 4, timely_rate: 0.25, defect: 0.25, utilization: 0.50 },
      { id: 5, timely_rate: 0.40, defect: 0.40, utilization: 0.20 },
      { id: 6, timely_rate: 0.40, defect: 0.20, utilization: 0.40 },
      { id: 7, timely_rate: 0.20, defect: 0.40, utilization: 0.40 }
    ],
    projectDetails: {} // 添加项目详情的 state
  },
  getters: {
    selectedProject: state => state.selectedProject,
    projectId: state => state.projectId,
    allProjects: state => state.projects,
    projectDetails: state => state.projectDetails // 添加项目详情的 getter
  },
  mutations: {
    SET_SELECTED_PROJECT(state, project) {
      state.selectedProject = project;
      localStorage.setItem('selectedProject', JSON.stringify(project));
    },
    SET_PROJECT_ID(state, id) {
      state.projectId = id;
      localStorage.setItem('projectId', id);
    },
    SET_PROJECTS(state, projects) {
      state.projects = projects;
    },
    SET_PROJECT_DETAILS(state, projectDetails) { // 添加项目详情的 mutation
      state.projectDetails = projectDetails;
    }
  },
  actions: {
    updateSelectedProject({ commit }, project) {
      commit('SET_SELECTED_PROJECT', project);
    },
    updateProjectId({ commit }, id) {
      commit('SET_PROJECT_ID', id);
    },
    
    async fetchProjects({ commit }) {
      try {
        const response = await instance.get(`${API_URL}/server/search/getAllProjects`);
        const projects = response.data.map(project => ({
          label: project.id,
          name: project.name || 'Unknown',
          category: project.typesSection || 'Uncategorized',
          type: project.projectType || 'Unknown'
        }));
        commit('SET_PROJECTS', projects);
      } catch (error) {
        console.error('获取项目失败:', error);
      }
    },

    async fetchProjectDetails({ commit }, projectId) { // 添加获取项目详情的 action
      try {
        const response = await instance.get(`${API_URL}/server/projectDetails/getProjectDetails`, {
          params: {
            id: parseInt(projectId)
          }
        });
        commit('SET_PROJECT_DETAILS', response.data);
      } catch (error) {
        console.error('获取项目详情失败', error);
      }
    }
  }
});

