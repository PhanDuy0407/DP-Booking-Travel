import axiosIns from 'axios'

export const state = () => ({
  guideList: [],
  maxId: Number,
  dataAdd: Number,
  dataUpdate: Number,
  guideSelectBox: [],
})
const getters = {
  getGuideList: (state) => state.guideList,
  getMaxId: (state) => state.maxId,
  getDataAdd: (state) => state.dataAdd,
  getDataUpdate: (state) => state.dataUpdate,
  getGuideSelectBoxList: (state) => state.guideSelectBox,
}
const mutations = {
  setGuideList: (state, payload) => {
    state.guideList = payload
  },
  setMaxId: (state, payload) => {
    state.maxId = payload
  },
  setdataAdd: (state, payload) => {
    state.dataAdd = payload
  },
  setdataUpdate: (state, payload) => {
    state.dataUpdate = payload
  },
  setGuideSelectBoxList: (state, payload) => {
    state.guideSelectBox = payload
  },
}
const actions = {
  async actionGuideList({ commit }, objectSearch) {
    // let header = { headers: { Authorization: "Bearer " + useJwt.getToken() } }
    const data = await axiosIns.get(`
    http://localhost:8089/api/v1/guides?nameGuide=${objectSearch.nameGuide}&phoneGuide=${objectSearch.phoneGuide}`)
    if (data.status === 200) {
      console.log('ress===', data.data)
      commit('setGuideList', data.data)
    }
  },
  async actionGetMaxId({ commit }) {
    // let header = { headers: { Authorization: "Bearer " + useJwt.getToken() } }
    const data = await axiosIns.get(`
    http://localhost:8089/api/v1/guides/maxid`)
    if (data.status === 200) {
      console.log('ress===', data.data)
      commit('setMaxId', data.data)
    }
  },
  async actionGetGuideSelectBoxList({ commit }) {
    // let header = { headers: { Authorization: "Bearer " + useJwt.getToken() } }
    const data = await axiosIns.get(`
    http://localhost:8089/api/v1/guide-select-box`)
    if (data.status === 200) {
      console.log('ress===', data.data)
      commit('setGuideSelectBoxList', data.data)
    }
  },
  async actionPostNewGuide({ commit }, dataGuide) {
    // let header = { headers: { Authorization: "Bearer " + useJwt.getToken() } }
    const data = await axiosIns.post(
      `http://localhost:8089/api/v1/guides`,
      dataGuide,
    )
    if (data.status === 200) {
      console.log('ress===', data.data)
      commit('setdataAdd', data.data)
    }
  },
  async actionPostUpdateGuide({ commit }, dataGuide) {
    // let header = { headers: { Authorization: "Bearer " + useJwt.getToken() } }
    const data = await axiosIns.post(
      `http://localhost:8089/api/v1/guides/update`,
      dataGuide,
    )
    if (data.status === 200) {
      console.log('ress===', data.data)
      commit('setdataUpdate', data.data)
    }
  },
}
export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
}
