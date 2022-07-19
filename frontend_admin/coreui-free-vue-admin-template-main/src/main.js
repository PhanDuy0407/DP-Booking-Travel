import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import CoreuiVue from '@coreui/vue'
import ElementPlus from 'element-plus'
import CIcon from '@coreui/icons-vue'
import { iconsSet as icons } from '@/assets/icons'
import DocsCallout from '@/components/DocsCallout'
import DocsExample from '@/components/DocsExample'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import { brandSet } from '@coreui/icons'

const app = createApp(App)
app.use(store)
app.use(router)
app.use(CoreuiVue)
app.use(ElementPlus)
app.use(brandSet)
app.provide('icons', icons)
app.component('CIcon', CIcon)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
app.component('DocsCallout', DocsCallout)
app.component('DocsExample', DocsExample)

app.mount('#app')
