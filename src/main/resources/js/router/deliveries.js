import Deliveries from 'pages/deliveries/Deliveries.vue'
import Countries from 'pages/deliveries/CountriesCategories.vue'
import Purveyors from 'pages/deliveries/Purveyor.vue'
import Supplies from 'pages/deliveries/Supply.vue'


export default [
  {
		path: '/deliveries',
    component: Deliveries,
    redirect: '/deliveries/supplies',
    children: [
      {
        path: 'supplies',
        component: Supplies,
      },
      {
        path: 'countries',
        component: Countries,
      },
      {
        path: 'purveyors',
        component: Purveyors
      },
      // {
      //   path: 'sparepart',
      //   component: SparePart
      // }
    ]
	}
]