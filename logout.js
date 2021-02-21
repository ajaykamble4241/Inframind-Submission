firebase.auth().onAuthStateChanged(function(user) {
    if (!user) {
      // User is signed in.
      window.location.href = "AdminLogin.html";
      
    } else {
      // No user is signed in.
      
     
    }
});


function logout(){
  firebase.auth().signOut()
      .then(function () {
          // Sign-out successful.
         
          
      }).catch(function (error) {
          // An error happened.

          var error = error.code;
          var errorMessage = error.message;

          window.alert("Error : " + errorMessage);
      });
}
  
