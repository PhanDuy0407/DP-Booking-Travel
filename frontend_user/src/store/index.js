import Vue from 'vue'
import Vuex from 'vuex'

import tourList from './modules/tour_list'
import place from './modules/place'
import order from './modules/order'
import user from './modules/user'

Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        tourList,
        place,
        order,
        user
    },
    // strict: process.env.DEV,
})
