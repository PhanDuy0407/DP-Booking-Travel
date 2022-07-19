import axios from "axios"

const state = {
    authentication: false,
    user: {}
}

// getters
const getters = {
    getAuthentication: (state) => {
        return state.authentication
    },
    getUser: (state) => {
        return state.user
    }
}

// mutations
const mutations = {
    setAuthentication: (state, payload) => {
        state.authentication = payload
    },
    setUser: (state, payload) => {
        state.user = {
            // fullname: payload.fullname,
            // email: payload.email,
            // address: payload.address,
            // dob: payload.dob,
            ...payload
        }
    }
}
// actions
const actions = {
    setAuthentication({commit}, val){
        commit('setAuthentication', val)
    },
    async getUser({commit}, item){
        const res = await axios.get(`http://localhost:8089/api/v1/user/${item}`)
        if(res.status == 200){
            commit('setUser', res.data)
            console.log(res.data)
        }
        
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}