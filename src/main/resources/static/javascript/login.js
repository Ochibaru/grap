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
 * @return {string} The URL of the FirebaseUI standalone widget.
 */
function getWidgetUrl() {
    return '/widget#recaptcha=' + getRecaptchaMode() + '&emailSignInMethod=' +
        getEmailSignInMethod();
}


/**
 * Redirects to the FirebaseUI widget.
 */
var signInWithRedirect = function() {
    window.location.assign(getWidgetUrl());
};


/**
 * Open a popup with the FirebaseUI widget.
 */
var signInWithPopup = function() {
    window.open(getWidgetUrl(), 'Sign In', 'width=985,height=735');
};


/**
 * Displays the UI for a signed in user.
 * @param {!firebase.User} user
 */
var handleSignedInUser = function(user) {


    //var docRef = db.collection("Users").doc(user.uid);

    document.getElementById('user-signed-in').style.display = 'block';
    document.getElementById('user-signed-out').style.display = 'none';
    //document.getElementById('fullName').textContent = user.name;
    document.getElementById('name').textContent = user.displayName;
    document.getElementById('email').textContent = user.email;
    document.getElementById('phone').textContent = user.phoneNumber;
    if (user.photoURL) {
        var photoURL = user.photoURL;
        // Append size to the photo URL for Google hosted images to avoid requesting
        // the image with its original resolution (using more bandwidth than needed)
        // when it is going to be presented in smaller size.
        if ((photoURL.indexOf('googleusercontent.com') !== -1) ||
            (photoURL.indexOf('ggpht.com') !== -1)) {
            photoURL = photoURL + '?sz=' +
                document.getElementById('photo').clientHeight;
        }
        document.getElementById('photo').src = photoURL;
        document.getElementById('photo').style.display = 'block';
    } else {
        document.getElementById('photo').style.display = 'none';
    }
};

/**
 * Displays the UI for a signed out user.
 */
var handleSignedOutUser = function() {
    document.getElementById('user-signed-in').style.display = 'none';
    document.getElementById('user-signed-out').style.display = 'block';

    ui.start('#firebaseui-container', getUiConfig());
};

// Listen to change in auth state so it displays the correct UI for when
// the user is signed in or not.
firebase.auth().onAuthStateChanged(function(user) {
    document.getElementById('loading').style.display = 'none';
    document.getElementById('loaded').style.display = 'block';
    document.getElementById('profile').style.display = 'none';
    document.getElementById('pantry').style.display = 'none';
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


/**
 * Handles when the user changes the reCAPTCHA or email signInMethod initFirebase.
 */
function handleConfigChange() {
    const newRecaptchaValue = document.querySelector(
        'input[name="recaptcha"]:checked').value;
    const newEmailSignInMethodValue = document.querySelector(
        'input[name="emailSignInMethod"]:checked').value;
    location.replace(
        location.pathname + '#recaptcha=' + newRecaptchaValue +
        '&emailSignInMethod=' + newEmailSignInMethodValue);

    // Reset the inline widget so the initFirebase changes are reflected.
    ui.reset();
    ui.start('#firebaseui-container', getUiConfig());
}


/**
 * Initializes the app.
 */
var initApp = function() {

    $('.readmore h3').click(function (){
        firebase.auth().signOut();
    })
    $('.readmore h4').click(function (){
        deleteAccount();
    })
    $('.btnProfileNav').click(function (){
        userProfilePage();
    })
    document.getElementById("btnEdit").addEventListener("click", updateProfileInfo, true);

    $('.btnPantryNav').click(function (){
        userPantryPage();
    })

    $('body')
        .on('click', 'div.three button.btn-search', function(event) {
            event.preventDefault();
            var $input = $('div.three input');
            $input.focus();
            if ($input.val().length() > 0) {
                // submit form
            }
        });

    connect();
};

window.addEventListener('load', initApp);

function userPantryPage(){
    document.getElementById('user-signed-in').style.display = 'none';
    document.getElementById('user-signed-out').style.display = 'none';
    document.getElementById('profile').style.display = 'none';
    document.getElementById('pantry').style.display = 'block';
}

/**
 * Edit Profile
 */
function userProfilePage(){
    document.getElementById('user-signed-in').style.display = 'none';
    document.getElementById('user-signed-out').style.display = 'none';
    document.getElementById('pantry').style.display = 'none';
    document.getElementById('profile').style.display = 'block';
}

function updateProfileInfo(){
    var user = firebase.auth().currentUser;
    if (user != null) {
        var displayName = document.getElementById('username').value;
        user.updateProfile({displayName: displayName, photoURL: user.photoURL}).then(function () {
            sendProfileInformation();
            // Update Successful
            console.log(user.displayName);
        }).catch(function (error) {
            // An error happened
            console.log('Profile Nav error');
        })
    }
}


/*
    Web Socket
 */
var stompClient = null;

function connect() {
    var socket = new SockJS('/profile-update');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function (updateProfile) {
            // Use if sending message back to client side
        });
    });
}

function sendProfileInformation() {
    var email = firebase.auth().currentUser.email;
    var uid = firebase.auth().currentUser.uid;
    console.log("login email shows: " + email);
    stompClient.send("/profile-update", {}, JSON.stringify({'email': email, 'firstName': $("#firstName").val(), 'lastName': $("#lastName").val()}));
}
