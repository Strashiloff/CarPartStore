import Buys from 'pages/order/Buys.vue'
import Requests from 'pages/order/Requests.vue'
import Customers from 'pages/order/Customers.vue'
import Defects from 'pages/order/Defects.vue'
import Orders from 'pages/order/Orders.vue'


export default [
  {
		path: '/orders',
    component: Orders,
    redirect: '/orders/requests',
    children: [
      {
        path: 'requests',
        component: Requests,
      },
      {
        path: 'buys',
        component: Buys,
      },
      {
        path: 'customers',
        component: Customers
      },
      {
        path: 'defects',
        component: Defects
      }
    ]
	}
]