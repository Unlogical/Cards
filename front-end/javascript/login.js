function hideForm(id){
  return function(){
    var formStyle = document.getElementById(id).style
    formStyle.visibility = "hidden" 
    formStyle.opacity = "0"
    return false
  }
}

function linkOnclick(id) {
  var formStyle = document.getElementById(id).style  
  formStyle.visibility = "visible" 
  formStyle.opacity = "1"
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
                               
function init() {
  document.getElementById("enter").onclick = function(){
    hideForm("regform")
    linkOnclick("enterform")
    dark()
  }
  document.getElementById("hide").onclick = function(){
  	hideForm("enterform")()
  	light()
  }

  document.getElementById("reg").onclick = function(){
    hideForm("enterform")
    linkOnclick("regform")
    dark()
  }
  document.getElementById("hidereg").onclick = function () {
  	 hideForm("regform")()
    light()
 }
}

window.addEventListener('load',init,false)

function submitRegister(){
  console.log("sending register");
  $.ajax({
    url: "http://localhost:8080/fakesignup",
    type: "POST",
    dataType : "json",
    data: {login: "lol", passwd: "123", email: "q@q.com", gender: false},
    success: function(data) {
      console.log("SUCCESS");     
      console.log(data);
    },
    error: function(a,b,c){
      console.log("AAA"); 
      console.log(a)
      console.log(b)
      console.log(c)
    }
  })
}
