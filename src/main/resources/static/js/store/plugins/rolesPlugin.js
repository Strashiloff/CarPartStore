export default function () {
  return store => {
    store.subscribe((mutation, state) => {
      let payload = mutation.payload

      switch (mutation.type) {
        case 'app/getCurrentUserMutation':
          if (payload.roles.includes('ADMIN')) {
            window.vm.$user.set({ role: 'ADMIN' })
          } else {
            window.vm.$user.set({ role: 'USER' })
          }
          break
        default:
          console.log(mutation.type)
          break
      }
    })
  }
}