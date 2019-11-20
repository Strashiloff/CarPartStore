export default function() {
  return store => {
    store.subscribe((mutation, state) => {
      let date = new Date()
      console.log(date.getHours()+':'+date.getMinutes()+':'+date.getSeconds()+':'+date.getMilliseconds(), mutation)
    })
  }
}