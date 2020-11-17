const btnSignUp = document.getElementById('btnSignup');

// Working For Sign Up Form
// Full Name Validation
function checkUserFirstName(){
    let userSurname = document.getElementById("userFirstName").value;
    let flag = false;
    if(userSurname === ""){
        flag = true;
    }
    if(flag){
        document.getElementById("userFirstNameError").style.display = "block";
    }else{
        document.getElementById("userFirstNameError").style.display = "none";
    }
}
// User  Validation
function checkUserLastName(){
    let userSurname = document.getElementById("userLastName").value;
    let flag = false;
    if(userSurname === ""){
        flag = true;
    }
    if(flag){
        document.getElementById("userLastNameError").style.display = "block";
    }else{
        document.getElementById("userLastNameError").style.display = "none";
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
    let userFirstName = document.getElementById("userFirstName").value;
    let userLastName = document.getElementById("userLastName").value;
    let userEmail = document.getElementById("userEmail").value;
    let userPassword = document.getElementById("userPassword").value;
    let userFullNameFormat = /^([A-Za-z.\s_-])/;
    let userEmailFormat = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    let userPasswordFormat = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{10,}/;

    let checkUserFullNameValid = userFirstName.match(userFullNameFormat);
    let checkUserEmailValid = userEmail.match(userEmailFormat);
    let checkUserPasswordValid = userPassword.match(userPasswordFormat);

    if(checkUserFullNameValid == null){
        return checkUserFirstName();
    }else if(userLastName === ""){
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
                userFirstName: userFirstName,
                userLastName: userLastName,
                userEmail: userEmail,
                userPassword: userPassword
            }
            firebaseRef.child(uid).set(userData);
            return new Swal('Your Account Created','Your account was created successfully, you can log in now.',
            ).then((value) => {
                setTimeout(function(){
                    window.location.replace("../index");
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

btnSignUp.addEventListener('click', function (event) {
    signUp();
});