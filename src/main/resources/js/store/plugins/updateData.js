export default function () {
  return store => {
    let dispatch = store.dispatch
    let getter = store.getters

    store.subscribe((mutation, state) => {
      let payload = mutation.payload

      switch (mutation.type) {
        case 'users/updateUserMutation':
          if (payload.username == getter['app/getCurrentUser'].username) {
            dispatch('app/getCurrentUserAction')
          }
          break
        case 'stoke/addStokeMutation':
          dispatch('stoke/getAllStokeAction')
          break
        case 'section/addSectionMutation':
          dispatch('section/setAllSectionsAction')
          break
        case 'type/addTypeMutation':
          dispatch('type/setAllTypesAction')
          break
        case 'sparePart/addSparePartsMutation':
          dispatch('sparePart/getAllSparePartsAction')
          break
        case 'country/addCountryMutation':
          dispatch('country/getCountriesAction')
          break
        case 'category/addCategoryMutation':
          dispatch('category/getCategoriesAction')
          break
        case 'purveyor/addPurveyorMutation':
          dispatch('purveyor/getPurveyorsAction')
          break
        case 'supply/addSupplyMutation':
          dispatch('supply/getSuppliesAction')
          break
        case 'contract/addContractMutation':
          dispatch('supply/saveSupplyAction', payload.supply)
          dispatch('contract/getContractsAction')
          break
        case 'position/addPositionMutation':
          dispatch('position/setAllPositionsAction')
          break
        case 'customer/addCustomerMutation':
          dispatch('customer/getCustomersAction')
          break
        case 'request/addRequestMutation':
          dispatch('request/getRequestsAction')
          break
        case 'list/addListMutation':
          dispatch('list/setAllListsAction')
          break
        case 'buy/addBuyMutation':
          dispatch('buy/getBuysAction')
          break
        case 'defect/addDefectMutation':
          dispatch('defect/getDefectsAction')
          break
      }
    })
  }
}