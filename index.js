firebase.auth().onAuthStateChanged(function(user) {
  if (user) {
    // User is signed in.
    window.location.href = "main.html";
    
      
     
  } 
  else {
    // No user is signed in.
    //window.location.href = "signin.html";
    
   
  }
});


function login(){
  var userEmail = document.getElementById("SIemail").value;
  var userpass = document.getElementById("SIpassword").value;
  

  //window.alert(userEmail + " " + userpass);
  firebase.auth().signInWithEmailAndPassword(userEmail, userpass)
  .then((user) => {

    // Signed in 
    // ...
   user = firebase.auth().currentUser;
   //location.href = "main.html";
   window.alert("Login successfully.....");
  })
  .catch((error) => {
    var error = error.code;
    var errorMessage = error.message;

    window.alert("Error : " + errorMessage);
  });


  
}
