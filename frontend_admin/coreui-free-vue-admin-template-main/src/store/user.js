import axiosIns from 'axios'

export const state = () => ({
  userList: [],
  userStatus: {},
  authentication: false,
})
const getters = {
  getUserList: (state) => state.userList,
  getUserStatus: (state) => state.userStatus,
  getAuthentication: (state) => {
    return state.authentication
  },
}
const mutations = {
  setUserList: (state, payload) => {
    state.userList = payload
  },
  setUserStatus: (state, payload) => {
    state.userStatus = payload
  },
  setAuthentication: (state, payload) => {
    state.authentication = payload
  },
}
const actions = {
  setAuthentication({ commit }, val) {
    commit('setAuthentication', val)
  },
  async actionUserList({ commit }, objectSearch) {
    // let header = { headers: { Authorization: "Bearer " + useJwt.getToken() } }
    const data = await axiosIns.get(
      `http://localhost:8089/api/v1/users?id=${objectSearch.id}&username=${objectSearch.username}&rank=${objectSearch.rank}&status=${objectSearch.status}`,
    )
    if (data.status === 200) {
      console.log('ress===', data.data)
      commit('setUserList', data.data)
    }
  },
  async actionUserStatus({ commit }, userStatus) {
    // let header = { headers: { Authorization: "Bearer " + useJwt.getToken() } }
    const data = await axiosIns.post(
      `http://localhost:8089/api/v1/user/status`,
      userStatus,
    )
    if (data.status === 200) {
      console.log('ress===', data.data)
      commit('setUserStatus', data.data)
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
