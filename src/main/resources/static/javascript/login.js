function hideForm(id){
  return function(){
    var formStyle = document.getElementById(id).style
    formStyle.visibility = "hidden" 
    formStyle.opacity = "0"
    return false
  }
}

function linkOnclick(id, input) {
  var formStyle = document.getElementById(id).style  
  var focused = document.getElementById(input)  
  formStyle.visibility = "visible" 
  formStyle.opacity = "1"
  focused.focus()
  focused.select()
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
    linkOnclick("enterform", "in_focused")
    dark()
  }
  document.getElementById("hide").onclick = function(){
  	hideForm("enterform")()
  	light()
  }
  document.getElementById("allin").onclick = function(){
  	hideForm("enterform")()
  	hideForm("regform")()
  	light()
  }

  document.getElementById("reg").onclick = function(){
    hideForm("enterform")
    linkOnclick("regform", "up_focused")
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
    url: "http://localhost:8080/signup",
    type: "POST",
    dataType : "json",
    data: $(document.signup).serialize(),
    success: function(data) {
      console.log("User registered successfully");
      console.log(data);
      window.location.replace("/");
    },
    error: function(data){
      console.log("Impossible to register user");
      console.log(data)
    }
  })
}

function submitLogin(){
  console.log("sending register");
  $.ajax({
    url: "http://localhost:8080/signin",
    type: "POST",
    dataType : "json",
    data: $(document.signin).serialize(),
    success: function(data) {
      console.log("User logged in successfully");
      console.log(data);
      window.location.replace("/");
    },
    error: function(data){
      console.log("Impossible to login user");
      console.log(data)
    }
  })
}
