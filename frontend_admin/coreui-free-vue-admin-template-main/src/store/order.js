import axiosIns from 'axios'

export const state = () => ({
  ordersAllList: [],
})
const getters = {
  getAllOrdersList: (state) => state.ordersAllList,
}
const mutations = {
  setAllOrdersList: (state, payload) => {
    state.ordersAllList = payload
  },
}
const actions = {
  async actionAllOrdersList({ commit }, objectSearch) {
    // let header = { headers: { Authorization: "Bearer " + useJwt.getToken() } }
    const data = await axiosIns.get(`
    http://localhost:8089/api/v1/orders-all?fromDate=${objectSearch.fromDate}&toDate=${objectSearch.toDate}`)
    if (data.status === 200) {
      console.log('ress===', data.data)
      commit('setAllOrdersList', data.data)
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
