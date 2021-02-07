import Vue from 'vue'
import Vuesax from 'vuesax'
import axios from 'axios'

Vue.prototype.$axios = axios; // add
Vue.use(Vuesax)
