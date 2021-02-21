firebase.auth().onAuthStateChanged(function(user) {
    if (user) {
      // User is signed in.
      window.location.href = "AdminMain.html";
      
        
       
    } 
    else {
      // No user is signed in.
      //window.location.href = "signin.html";
      
     
    }
  });
  
  
  function Alogin(){
    var Apass = "Smart@11"
    var userEmail = document.getElementById("ASIemail").value;
    var userpass = document.getElementById("ASIpassword").value;
    var Auserpass = document.getElementById("Apassword").value;
    
   if (Auserpass == Apass){
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
  else{
    window.alert("Something Wrong.....");

  }

   }
    
  