import SettingsStoke from 'pages/stock/Settings.vue'
import Stoke from 'pages/stock/Stoke.vue'
import ContentStoke from 'pages/stock/Content.vue'
import SparePart from 'pages/stock/spare_part/SparePart.vue'

export default [
  {
		path: '/stokes',
    component: Stoke,
    redirect: '/stokes/settings',
    children: [
      {
        path: 'content',
        component: ContentStoke,
      },
      {
        path: 'settings',
        component: SettingsStoke
      },
      {
        path: 'sparepart',
        component: SparePart
      }
    ]
	}
]