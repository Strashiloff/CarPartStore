import Vue from "vue"
import VueRouter from "vue-router"
import AddUser from "pages/admin/AddUser.vue"
import Admin from 'pages/admin/Admin.vue'
import Users from "pages/admin/Users.vue"
import Test from "pages/Test.vue"
import Post from "pages/admin/Post.vue"
import Home from "pages/Home.vue"
import Stoke from 'pages/stock/Settings.vue'

Vue.use(VueRouter)

const routes = [
	{
		path: '*',
		name: 'home',
		component: Home,
	},
	{
		path: '/admin',
    component: Admin,
		meta: {
      permissions: [
        {
          role: 'USER',
          access: false,
          redirect: 'home'
        }
      ]
    },
		children: [
			{
				path: 'registration',
				component: AddUser,
				meta: {
          permissions: [
            {
              role: 'USER',
              access: false,
              redirect: 'home'
            }
          ]
        },
			},
			{
				path: 'posts',
				component: Post,
				meta: {
          permissions: [
            {
              role: 'USER',
              access: false,
              redirect: 'home'
            }
          ]
        },
			},
			{
				path: 'users',
				component: Users,
				meta: {
          permissions: [
            {
              role: 'USER',
              access: false,
              redirect: 'home'
            }
          ]
        },
			}
		]
	},
	{
		path: '/stokes',
		component: Stoke,
	},
]

export default new VueRouter({
	mode: 'history',
	routes
})
