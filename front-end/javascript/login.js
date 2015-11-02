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
