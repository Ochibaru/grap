const txtSIUserEmail = document.getElementById('userSIEmail');
const txtSIUserPwd = document.getElementById('userSIPassword');
const btnSignIn = document.getElementById('userSignIn');
const txtUserFirstName = document.getElementById('userFirstName');
const txtUserLastName = document.getElementById('userLastName');
const txtUserEmail = document.getElementById('userEmail');
const txtUserPwd = document.getElementById('userPassword');
const btnSignUp = document.getElementById('btnSignup');
//txtSIUserEmail
// Working For Sign Up Form
// Full Name Validation
function checkUserFirstName(){
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
// Check user bio characters. It'll use later
function checkUserBio(){
    let userBio = document.getElementById("userBio").value;
    let flag = false;
    if(flag){
        document.getElementById("userBioError").style.display = "block";
    }else{
        document.getElementById("userBioError").style.display = "none";
    }
}

//  Sign In Email Validation
function checkUserSIEmail(){
    let userSIEmail = document.getElementById("userSIEmail");
    let userSIEmailFormat = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    let flag;
    flag = !userSIEmail.value.match(userSIEmailFormat);
    if(flag){
        document.getElementById("userEmailError").hidden = false;
    }else{
        document.getElementById("userEmailError").hidden = true;
    }
}
// Sign In Password Validation
function checkUserSIPassword(){
    let userSIPassword = document.getElementById("userSIPassword");
    let userSIPasswordFormat = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{10,}/;
    let flag;
    flag = !userSIPassword.value.match(userSIPasswordFormat);
    document.getElementById("userPasswordError").hidden = !flag;
}
// Check email/password exist in the firebase authentication
export function signIn(){
    let userSIEmail = document.getElementById("userSIEmail").value;
    let userSIPassword = document.getElementById("userSIPassword").value;
    let userSIEmailFormat = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    let userSIPasswordFormat = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{10,}/;

    let checkUserEmailValid = userSIEmail.match(userSIEmailFormat);
    let checkUserPasswordValid = userSIPassword.match(userSIPasswordFormat);

    if(checkUserEmailValid == null){
        return checkUserSIEmail();
    }else if(checkUserPasswordValid == null){
        return checkUserSIPassword();
    }else{
        firebase.auth().signInWithEmailAndPassword(userSIEmail, userSIPassword).then((success) => {
            return new Swal({
                type: 'successful',
                title: 'Successfully signed in',
            }).then((value) => {
                setTimeout(function(){
                    window.location.replace("../profile");
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

/*
txtSIUserEmail.addEventListener('onchange', function (event) {
    checkUserSIEmail();
});
txtSIUserPwd.addEventListener('onchange', function (event) {
    checkUserSIPassword();
});
 */

btnSignIn.addEventListener('click', function (event) {
    console.log("hitting sign in");
    checkUserFirstName();
    checkUserLastName();
    checkUserPassword();
    signIn();
});