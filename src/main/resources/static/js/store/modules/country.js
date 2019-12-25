import countriesApi from '../../api/country'
import getIndex from './utils'

export const moduleCountry = {
	namespaced: true,
	state: {
		countries: [],
	},
	actions: {
		async getCountriesAction({commit}) {
			const result = await countriesApi.allCountries()
			const data = await result.json()
			commit('getCountriesMutation', data)
		},
		async addCountryAction({commit}, country) {
			const result = await countriesApi.addCountry(country)
			const data = await result.json()
			if (data.ok) {
				commit('addCountryMutation', country)
			}
		},
		async saveCountryAction({commit}, country) {
			const result = await countriesApi.saveCountry(country)
			const data = await result.json()
			if (data.ok) commit('saveCountryMutation', country)
		},
		async removeCountryAction({commit}, country) {
			const result = await countriesApi.removeCountry(country)
			const data = await result.json()
			if (data.ok) commit('removeCountryMutation', country)
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