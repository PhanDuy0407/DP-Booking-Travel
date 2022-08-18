import axios from "axios"
const state = {
    orderHistoryList : []
}

// getters
const getters = {
    getOrderHistory: (state) => {
        // let result = []
        // state.resOperationManuals.filter((item) => {
        //     result.push(item)
        // })
        return state.orderHistoryList
    },
}

// mutations
const mutations = {
    setOrderHistory: (state, payload) => {
        state.orderHistoryList = payload
    },
}
// actions
const actions = {
    async bookTour({ commit }, item) {
        const res = await axios.post("http://localhost:8089/api/v1/order", item)
        return res;
    },
    async actionOrderListHistory({ commit }, userId) {
        const res = await axios.get(`http://localhost:8089/api/v1/order-history/${userId}`)
        if(res.status == 200){
            commit('setOrderHistory', res.data)
        }
        return res;
    }

}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}