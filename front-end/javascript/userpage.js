

init() {
  document.getElementsByClass("fa-trash").onClick = showModalDialog("sure?")
}

window.addEventListener('load',init,false)