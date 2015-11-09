function deleteset(id) {
  document.getElementById(id).style.display = "none"  	
}
 function show() {
  alert("rwrligjilk")    
}

function init() {
   document.getElementById("delete-set1").onclick = function () {
    deleteset("set1")
  }
}
window.addEventListener('load',init,false)