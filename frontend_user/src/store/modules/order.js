import axios from "axios"
const state = {
    
}

// getters
const getters = {

}

// mutations
const mutations = {

}
// actions
const actions = {
    async bookTour({ commit }, item) {
        const res = await axios.post("http://localhost:8089/api/v1/order", item)
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