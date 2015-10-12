function hideOnclick() {
  var formStyle = document.getElementById("enterform").style  
  formStyle.visibility = "hidden" 
  formStyle.opacity = "0"
  return false
}      
function hideRegOnclick() {
  var formStyle = document.getElementById("regform").style  
  formStyle.visibility = "hidden" 
  formStyle.opacity = "0"
  return false
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
    hideRegOnclick()
    enterLinkOnclick()
  }
  document.getElementById("hide").onclick = hideOnclick

  document.getElementById("reg").onclick = function(){
    hideOnclick()
    reglinkOnclick()
  }
  document.getElementById("hidereg").onclick = hideRegOnclick

}
window.addEventListener('load',init,false)
