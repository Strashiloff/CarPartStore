import Vue from 'vue'

const sections = Vue.resource('/section')
const allSection = Vue.resource('/section/all')
const deleteSection = Vue.resource('/section/delete')

export default {
  getAllSections: () => allSection.get(),
  getSectionByStoke: stoke => sections.get(stoke),
	addSection: section => sections.save(section),
	saveSection: section => sections.update(section),
	delSection: section => deleteSection.save(section)
}