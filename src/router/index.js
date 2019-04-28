import Vue from 'vue'
import Router from 'vue-router'
import Welcome from '@/components/Welcome'
import LoginPage from '@/components/LoginPage'
import Irv from '@/components/IR&V'
import PasswordReset from '@/components/PasswordReset'
import Settings from '@/components/Settings'
<<<<<<< HEAD
import Feed from '@/components/Feed'
=======
import AccountCreation from '@/components/AccountCreation'
import ModeratorAndAdminCreation from '@/components/ModeratorAndAdminCreation'
>>>>>>> 554ec38f7bbada9eeb5388777b16f4c8d5be69ea

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
<<<<<<< HEAD
      path: '/feed',
      name: 'Feed',
      component: Feed
=======
      path: '/accountcreation',
      name: 'AccountCreation',
      component: AccountCreation
    },
    {
      path: '/moderatorAndAdminCreation',
      name: 'ModeratorAndAdminCreation',
      component: ModeratorAndAdminCreation
>>>>>>> 554ec38f7bbada9eeb5388777b16f4c8d5be69ea
    }
  ]
})
