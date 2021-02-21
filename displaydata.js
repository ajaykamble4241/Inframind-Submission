function Search(name,email,phone,age,gender,BO,BP,BG,HB,Tem){
    var search = document.getElementById("SIemail").value;
    var ul = document.getElementById('list');
   
    var _name= document.createElement('li');
    var _email= document.createElement('li');
    var _phone= document.createElement('li');
    var _age= document.createElement('li');
    var _gender= document.createElement('li');
    var _BO= document.createElement('li');
    var _BP= document.createElement('li');
    var _BG= document.createElement('li');
    var _HB= document.createElement('li');
    var _Tem= document.createElement('li');
   

    _name.innerHTML='Name: '+name;
    _email.innerHTML='Name: '+email;
    _age.innerHTML='Name: '+age;
    _gender.innerHTML='Name: '+gender;
    _phone.innerHTML='Name: '+phone;
    _BO.innerHTML='Name: '+BO;
    _BG.innerHTML='Name: '+BG;
    _BP.innerHTML='Name: '+BP;
    _HB.innerHTML='Name: '+HB;
    _Tem.innerHTML='Name: '+Tem;
    
    if(search==_name){
        ul.appendChild(_name);
        ul.appendChild(_email);
        ul.appendChild(_phone);
        ul.appendChild(_age);
        ul.appendChild(_gender);
        ul.appendChild(_BO);
        ul.appendChild(_BP);
        ul.appendChild(_BG);
        ul.appendChild(_HB);
        ul.appendChild(_Tem);

    }
    else if(search==_email){
        ul.appendChild(_name);
        ul.appendChild(_email);
        ul.appendChild(_phone);
        ul.appendChild(_age);
        ul.appendChild(_gender);
        ul.appendChild(_BO);
        ul.appendChild(_BP);
        ul.appendChild(_BG);
        ul.appendChild(_HB);
        ul.appendChild(_Tem);

    }
    else if(search==phone){
        ul.appendChild(_name);
        ul.appendChild(_email);
        ul.appendChild(_phone);
        ul.appendChild(_age);
        ul.appendChild(_gender);
        ul.appendChild(_BO);
        ul.appendChild(_BP);
        ul.appendChild(_BG);
        ul.appendChild(_HB);
        ul.appendChild(_Tem);
    }
    else{
        window.alert("Searching element is not found.....");
    }
}

function FetchAllData(){
    firebase.database().ref('User').once('value', function(snapshot){
        snapshot.forEach(
            function(ChildSnapshot){
                let name = ChildSnapshot.val().Name;
                let mail = ChildSnapshot.val().email;
                let phn = ChildSnapshot.val().phone;
                let age = ChildSnapshot.val().age;
                let gen = ChildSnapshot.val().gender;
                let Tem = ChildSnapshot.val().tem;
                let BO = ChildSnapshot.val().bo;
                let BP = ChildSnapshot.val().bp;
                let BG = ChildSnapshot.val().glucose;
                let HB = ChildSnapshot.val().heart;
                Search(name,mail,phn,age,gen,BO,BP,BG,HB,Tem);
                             
                            
                            
            }
        )
    })

}
window.onload(FetchAllData());
   

  