import axios from 'axios'
const state = {
    objSearch: {
        startPlaceId: "",
        endPlaceId: "",
        startTime: "",
        fromPeriod: "",
        toPeriod: "",
        period: "",
        fromPrice: "",
        toPrice: ""
    },
    tours: [],
    tour: null,
    toursDiscount: []
}

// getters
const getters = {
    getObjSearch: (state) => {
        // let result = []
        // state.resOperationManuals.filter((item) => {
        //     result.push(item)
        // })
        return state.objSearch
    },
    getTours: (state) => {
        return state.tours
    },
    getTour: (state) => {
        return state.tour
    },
    getToursDiscount: (state) => {
        return state.toursDiscount
    },

}

// mutations
const mutations = {
    setObjSearch: (state, payload) => {
        state.objSearch = {
            startPlaceId: payload.startPlaceId,
            endPlaceId: payload.endPlaceId,
            startTime: payload.startTime,
            fromPeriod: payload.fromPeriod,
            toPeriod: payload.toPeriod,
            period: payload.period,
            fromPrice: payload.fromPrice,
            toPrice: payload.toPrice
        }
    },
    setTours: (state, payload) => {
        state.tours = payload
    },
    setTour: (state, payload) => {
        state.tour = payload
    },
    setToursDiscount: (state, payload) => {
        state.toursDiscount = payload
    },

}
// actions
const actions = {
    setObjSearch({ commit }, filter) {
        commit('setObjSearch', filter)
        console.log(filter)
    },

    async getTours({ commit }, item) {
        const data = await axios.get(`http://localhost:8089/api/v1/tour-list?startPlaceId=${item.startPlaceId}&endPlaceId=${item.endPlaceId}&startTime=${item.startTime}&fromPeriod=${item.fromPeriod}&toPeriod=${item.toPeriod}&fromPrice=${item.fromPrice}&toPrice=${item.toPrice}`)
        if (data.status == 200) {
            commit('setTours', data.data)
        }
    },
    async getTour({ commit }, id) {
        const data = await axios.get(`http://localhost:8089/api/v1/tour-detail/${id}`)
        if (data.status == 200) {
            commit('setTour', data.data)
        }
    },
    async getToursDiscount({ commit }) {
        const data = await axios.get(`http://localhost:8089/api/v1/tour-discount`)
        if (data.status == 200) {
            commit('setToursDiscount', data.data)
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