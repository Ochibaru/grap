const ui = new firebaseui.auth.AuthUI(firebase.auth());
import { getEmailSignInMethod, getRecaptchaMode } from './common.js';

const CLIENT_ID =
    '278286482562-8r4t3bfmdeftpubeast0e4j1974d9e28.apps.googleusercontent.com';

function getUiConfig() {
    return {
        'callbacks': {
            'signInSuccessWithAuthResult': function(authResult, redirectUrl) {
                if (authResult.user) {
                    handleSignedInUser(authResult.user);
                    window.location.replace("/set-uid/?uid=" + authResult.user.uid);
                }
                if (authResult.additionalUserInfo) {
                    document.getElementById('is-new-user').textContent =
                        authResult.additionalUserInfo.isNewUser ?
                            'New User' : 'Existing User';
                }
                // Do not redirect.
                return false;
            }
        },
        // Opens IDP Providers sign-in flow in a popup.
        'signInFlow': 'popup',
        'signInOptions': [
            // TODO(developer): Remove the providers you don't need for your app.
            {
                provider: firebase.auth.GoogleAuthProvider.PROVIDER_ID,
                // Required to enable ID token credentials for this provider.
                clientId: '278286482562-8r4t3bfmdeftpubeast0e4j1974d9e28.apps.googleusercontent.com'
            },
            {
                provider: firebase.auth.EmailAuthProvider.PROVIDER_ID,
                // Whether the display name should be displayed in Sign Up page.
                requireDisplayName: true,
                signInMethod: getEmailSignInMethod()
            },
            firebaseui.auth.AnonymousAuthProvider.PROVIDER_ID
        ],
        // Terms of service url.
        'tosUrl': 'https://www.google.com',
        // Privacy policy url.
        'privacyPolicyUrl': 'https://www.google.com',
        'credentialHelper': CLIENT_ID && CLIENT_ID !== '278286482562-8r4t3bfmdeftpubeast0e4j1974d9e28.apps.googleusercontent.com' ?
            firebaseui.auth.CredentialHelper.GOOGLE_YOLO :
            firebaseui.auth.CredentialHelper.NONE
    };
}

/**
 * Redirects to the FirebaseUI widget.
 */
var signInWithRedirect = function() {
    window.location.assign(getWidgetUrl());
};

/**
 * Displays the UI for a signed in user.
 * @param {!firebase.User} user
 */
var handleSignedInUser = function(user) {
};

/**
 * Displays the UI for a signed out user.
 */
var handleSignedOutUser = function() {
    //document.getElementById('user-signed-in').style.display = 'none';
    //document.getElementById('user-signed-out').style.display = 'block';

    ui.start('#firebaseui-container', getUiConfig());
};

// Listen to change in auth state so it displays the correct UI for when
// the user is signed in or not.
firebase.auth().onAuthStateChanged(function(user) {
    user ? handleSignedInUser(user) : handleSignedOutUser();
});

/**
 * Deletes the user's account.
 */
var deleteAccount = function() {
    firebase.auth().currentUser.delete().catch(function(error) {
        if (error.code === 'auth/requires-recent-login') {
            // The user's credential is too old. She needs to sign in again.
            firebase.auth().signOut().then(function() {
                // The timeout allows the message to be displayed after the UI has
                // changed to the signed out state.
                setTimeout(function() {
                    alert('Please sign in again to delete your account.');
                }, 1);
            });
        }
    });
};

function loginUser(){
    var email = document.getElementById("emailExistingUser").value;
    var password = document.getElementById("passwordExistingUser").value;
    firebase.auth().signInWithEmailAndPassword(email, password)
        .then((userCredential) => {
            // Signed in
            var user = userCredential.user;
            console.log('user signed in : ', user.uid)
            window.location.replace("/set-uid/?uid=" + user.uid);
            // ...
        })
        .catch((error) => {
            var errorCode = error.code;
            var errorMessage = error.message;
            console.log('login error code: ' + errorCode + ' login error message: ' + errorMessage);
        });
}

// This does nothing with the username field
function signUpNewUser(){
    var email = document.getElementById("emailNewUser").value;
    var password = document.getElementById("passwordNewUser").value;
    var username = document.getElementById("usernameNewUser").value;
    // Create user with email and pass.
    firebase.auth().createUserWithEmailAndPassword(email, password).catch(function(error) {
        // Handle Errors here.
        var errorCode = error.code;
        var errorMessage = error.message;
        if (errorCode === 'auth/weak-password') {
            alert('The password is too weak.');
        } else {
            alert(errorMessage);
        }
        console.log(error);
    });
}
/**
 * Initializes the app.
 */
var initApp = function() {

    $('.readmore h3').click(function (){
        firebase.auth().signOut();
    })
    document.getElementById('btnLogin').addEventListener('click', loginUser, true);
    document.getElementById('btnSignUp').addEventListener('click', signUpNewUser, true);
};

window.addEventListener('load', initApp);

