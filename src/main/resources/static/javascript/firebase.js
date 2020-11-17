// Reference: https://firebase.google.com/docs/auth/web/firebaseui

document.addEventListener("DOMContentLoaded", function() {
    // Your web app's Firebase configuration
    var firebaseConfig = {
          apiKey: "AIzaSyCIV8r3jmqIj4SLbXaPvMRxIKmACny5-5A",
          authDomain: "grap-c2990.firebaseapp.com",
          databaseURL: "https://grap-c2990.firebaseio.com",
          projectId: "grap-c2990",
          storageBucket: "grap-c2990.appspot.com",
          messagingSenderId: "278286482562",
          appId: "1:278286482562:web:252683e15afb979cd6089b",
          measurementId: "G-VENZ60BKVW"
        };

    // Initialize Firebase
    firebase.initializeApp(firebaseConfig);

    // Initialize the FirebaseUI Widget using Firebase.
    var ui = new firebaseui.auth.AuthUI(firebase.auth());

    var uiConfig = {
        callbacks: {
            signInSuccessWithAuthResult: function(authResult, redirectUrl) {
                // User successfully signed in.
                // Redirect to page with user's favorites by setting UID cookie first
                window.location.replace("/set-uid/?uid=" + authResult.user.uid);
                // Return type determines whether we continue the redirect automatically
                // or whether we leave that to developer to handle.
                return false;
            },
            uiShown: function() {
                // The widget is rendered.
                // Hide the loader.
                document.getElementById('loader').style.display = 'none';
            }
        },
        // Prevent redirection to https://www.accountchooser.com
        credentialHelper: 'none',
        // Will use popup for IDP Providers sign-in flow instead of the default, redirect.
        signInFlow: 'popup',
        signInOptions: [
            firebase.auth.EmailAuthProvider.PROVIDER_ID
        ]
    };

    // The start method will wait until the DOM is loaded.
    ui.start('#firebaseui-auth-container', uiConfig);
})