import countriesApi from '../../api/country'
import getIndex from './utils'

export const moduleCountry = {
	namespaced: true,
	state: {
		countries: [],
	},
	actions: {
		async getCountriesAction({ commit, dispatch }) {
			const result = await countriesApi.allCountries()
			const data = await result.json()
			commit('getCountriesMutation', data)
		},
		async addCountryAction({ commit, dispatch }, country) {
			const result = await countriesApi.addCountry(country)
			const data = await result.json()
			if (data.okey) {
				commit('addCountryMutation', country)
			} else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Ошибка создания страны'
      }, { root: true })
		},
		async saveCountryAction({ commit, dispatch }, country) {
			const result = await countriesApi.saveCountry(country)
			const data = await result.json()
			if (data.okey) commit('saveCountryMutation', country)
			else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Ошибка сохранения страны'
      }, { root: true })
		},
		async removeCountryAction({ commit, dispatch }, country) {
			const result = await countriesApi.removeCountry(country)
			const data = await result.json()
			if (data.okey) commit('removeCountryMutation', country)
			else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Ошибка удаления страны, возможно есть поставщик из этой страны'
      }, { root: true })
		}
	},
	mutations: {
		getCountriesMutation(state, countries) {
			state.countries = countries
		},
		addCountryMutation(state, country) {},
		saveCountryMutation(state, country) {
			let index = getIndex(state.countries, country.id)
			state.countries.splice(index, 1, country)
		},
		removeCountryMutation(state, country) {
			state.countries.splice(getIndex(state.countries, country.id), 1)
		},
	},
	getters: {
		getAllCountries: state => state.countries,
		getCountryById: state => id => state.countries.find(elem => {
			return elem.id == id
		})
  }
}