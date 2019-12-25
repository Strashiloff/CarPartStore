import Vue from 'vue'

const countries = Vue.resource('/country')
const deleteCountry = Vue.resource('/country/delete')

export default {
	allCountries: () => countries.get(),
	addCountry: country => countries.save(country),
	saveCountry: country => countries.update(country),
	removeCountry: country => deleteCountry.save(country)
}