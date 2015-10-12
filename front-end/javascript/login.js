function hideForm(id){
  return function(){
    var formStyle = document.getElementById(id).style
    formStyle.visibility = "hidden" 
    formStyle.opacity = "0"
    return false
  }
}

function enterLinkOnclick() {
  //hideRegOnclick
  var formStyle = document.getElementById("enterform").style  
  formStyle.visibility = "visible" 
  formStyle.opacity = "1"
  return false
}  
function reglinkOnclick() {
	//hideOnclick
  var formStyle = document.getElementById("regform").style  
  formStyle.visibility = "visible" 
  formStyle.opacity = "1"
  return false
}
                                     
function init() {
  document.getElementById("enter").onclick = function(){
    hideForm("regform")()
    enterLinkOnclick()
  }
  document.getElementById("hide").onclick = hideForm("enterform")

  document.getElementById("reg").onclick = function(){
    hideForm("enterform")()
    reglinkOnclick()
  }
  document.getElementById("hidereg").onclick = hideForm("regform")

}
window.addEventListener('load',init,false)
