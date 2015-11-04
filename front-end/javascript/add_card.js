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

function dark() {
  var Background = document.getElementById("allin").style
  Background.backgroundColor = "rgba(0,0,0,0.5)"
  Background.display = "block"
  return false
}
function light() {
  var Background = document.getElementById("allin").style
  Background.backgroundColor = "rgba(0,0,0,0)"
  Background.display = "none"
  return false
}
   

window.addEventListener('load',init,false)

function init() {
  document.getElementById("add").onclick = function () {
  	 addCardOnclick()
  	 dark()
  	 }
  document.getElementById("hide").onclick = function () { 
    hideAdd()
    light() 
  }
  
}
