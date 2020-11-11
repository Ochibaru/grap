// Working For Sign Up Form
// Full Name Validation
function checkUserFullName(){
    let userSurname = document.getElementById("userFullName").value;
    let flag = false;
    if(userSurname === ""){
        flag = true;
    }
    if(flag){
        document.getElementById("userFullNameError").style.display = "block";
    }else{
        document.getElementById("userFullNameError").style.display = "none";
    }
}
// User Surname Validation
function checkUserLastName(){
    let userSurname = document.getElementById("userSurname").value;
    let flag = false;
    if(userSurname === ""){
        flag = true;
    }
    if(flag){
        document.getElementById("userSurnameError").style.display = "block";
    }else{
        document.getElementById("userSurnameError").style.display = "none";
    }
}
// Email Validation
function checkUserEmail(){
    let userEmail = document.getElementById("userEmail");
    let userEmailFormat = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    let flag;
    flag = !userEmail.value.match(userEmailFormat);
    if(flag){
        document.getElementById("userEmailError").style.display = "block";
    }else{
        document.getElementById("userEmailError").style.display = "none";
    }
}
// Password Validation
function checkUserPassword(){
    let userPassword = document.getElementById("userPassword");
    let userPasswordFormat = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{10,}/;
    let flag;
    flag = !userPassword.value.match(userPasswordFormat);
    if(flag){
        document.getElementById("userPasswordError").style.display = "block";
    }else{
        document.getElementById("userPasswordError").style.display = "none";
    }
}
// Submitting and Creating new user in firebase authentication
function signUp(){
    let userFullName = document.getElementById("userFullName").value;
    let userSurname = document.getElementById("userSurname").value;
    let userEmail = document.getElementById("userEmail").value;
    let userPassword = document.getElementById("userPassword").value;
    let userFullNameFormat = /^([A-Za-z.\s_-])/;
    let userEmailFormat = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    let userPasswordFormat = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{10,}/;

    let checkUserFullNameValid = userFullName.match(userFullNameFormat);
    let checkUserEmailValid = userEmail.match(userEmailFormat);
    let checkUserPasswordValid = userPassword.match(userPasswordFormat);

    if(checkUserFullNameValid == null){
        return checkUserFullName();
    }else if(userSurname === ""){
        return checkUserLastName();
    }else if(checkUserEmailValid == null){
        return checkUserEmail();
    }else if(checkUserPasswordValid == null){
        return checkUserPassword();
    }else{
        firebase.auth().createUserWithEmailAndPassword(userEmail, userPassword).then((success) => {
            let user = firebase.auth().currentUser;
            let uid;
            if (user != null) {
                uid = user.uid;
            }
            let firebaseRef = firebase.database().ref();
            let userData = {
                userFullName: userFullName,
                userSurname: userSurname,
                userEmail: userEmail,
                userPassword: userPassword
            }
            firebaseRef.child(uid).set(userData);
            return new Swal('Your Account Created','Your account was created successfully, you can log in now.',
            ).then((value) => {
                setTimeout(function(){
                    window.location.replace("../index.html");
                }, 1000)
            });
        }).catch((error) => {
            // Handle Errors here.
            let errorCode = error.code;
            let errorMessage = error.message;
            return new Swal({
                type: 'error',
                title: 'Error',
                text: "Error",
            })
        });
    }
}