function deleteset(id) {
  result = confirm("Удалить этот набор?");
  if (result) {
    document.getElementById(id).style.display = "none"  	
  }
}
function init() {
   document.getElementById("delete-set1").onclick = function () {
    deleteset("set1")
  }
    document.getElementById("delete-set2").onclick = function () {
    deleteset("set2")
  }
  
    document.getElementById("delete-set3").onclick = function () {
    deleteset("set3")
  }
  
    document.getElementById("delete-set4").onclick = function () {
    deleteset("set4")
  }
  
    document.getElementById("delete-set5").onclick = function () {
    deleteset("set5")
  }
  
    document.getElementById("delete-set6").onclick = function () {
    deleteset("set6")
  }
  
    document.getElementById("delete-set7").onclick = function () {
    deleteset("set7")
  }
  
    document.getElementById("delete-set8").onclick = function () {
    deleteset("set8")
  }
    document.getElementById("delete-set9").onclick = function () {
    deleteset("set9")
  }
  
}
window.addEventListener('load',init,false)