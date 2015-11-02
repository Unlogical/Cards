function addCardOnclick() {
  var addBlock = document.getElementById("add_block").style  
  addBlock.visibility = "visible" 
  addBlock.opacity = "1"
  return false
}  
                                   
function hideAdd(){
    var addBlock = document.getElementById("add_block").style
    addBlock.visibility = "hidden" 
    addBlock.opacity = "0"
    return false
}

window.addEventListener('load',init,false)

function init() {
  document.getElementById("add").onclick = addCardOnclick
  document.getElementById("hide").onclick = hideAdd
}
