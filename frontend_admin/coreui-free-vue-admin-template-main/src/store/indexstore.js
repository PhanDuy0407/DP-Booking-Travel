import Vue from 'vue'
import Vuex from 'vuex'
import tour from './tour.js'
import guide from './guide.js'
import user from './user.js'
import order from '/order.js'
// Modules

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    tour,
    guide,
    user,
    order,
  },
  strict: process.env.DEV,
})
