import axios from "axios"
const state = {
    places: []
}

// getters
const getters = {
    getPlaces: (state) => {
        // let result = []
        // state.resOperationManuals.filter((item) => {
        //     result.push(item)
        // })
        return state.places
    },

}

// mutations
const mutations = {
    setPlaces: (state, payload) => {
        state.places = payload
    },

}
// actions
const actions = {
    async getAll({ commit }) {
        const data = await axios.get("http://localhost:8089/api/v1/places")
        if(data.status == 200){
            commit('setPlaces', data.data)
        }
        
        
    }
    // async exportData({ commit }, filter) {
    //     return axios.get(`${apiURL._API_URL}res-operation-manual/export/excel?fromDate=${filter.fromDate}&toDate=${filter.toDate}?riverBasin=${filter.riverBasin}&river=${filter.river}&reservoir=${filter.reservoir}&fileType=${filter.fileType}`, { responseType: 'blob' })

    // },
    // async getFileTemplate({ commit }) {
    //     return axios.get(`${apiURL._API_URL}res-operation-manual/file-template`, { responseType: 'blob' })

    // },
    // async importFile({ commit }, item) {
    //     return axios.post(`${apiURL._API_URL}res-operation-manual/import`, item, { responseType: 'blob' })
    // },

}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}