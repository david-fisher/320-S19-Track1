import Vue from 'vue'
import Router from 'vue-router'
import Welcome from '@/components/Welcome'
import LoginPage from '@/components/LoginPage'
import Irv from '@/components/IR&V'
import PasswordReset from '@/components/PasswordReset'
import Settings from '@/components/Settings'
import Feed from '@/components/Feed'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Welcome',
      component: Welcome
    },
    {
      path: '/login',
      name: 'LoginPage',
      component: LoginPage
    },
    {
      path: '/irv',
      name: 'IR&V',
      component: Irv
    },
    {
      path: '/passwordreset',
      name: 'PasswordReset',
      component: PasswordReset
    },
    {
      path: '/user/settings',
      name: 'Settings',
      component: Settings
    },
    {
      path: '/feed',
      name: 'Feed',
      component: Feed
    }
  ]
})
