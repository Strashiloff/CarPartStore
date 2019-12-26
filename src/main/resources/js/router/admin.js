import AddUser from "pages/admin/AddUser.vue"
import Admin from 'pages/admin/Admin.vue'
import Users from "pages/admin/Users.vue"
import Post from "pages/admin/Post.vue"

export default [
  {
		path: '/admin',
    component: Admin,
    redirect: '/admin/users',
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
	}
]