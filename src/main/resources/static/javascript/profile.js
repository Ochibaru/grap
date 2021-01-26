var database = firebase.database();

function writeUserProfile(userId, name, email, imageUrl) {
    firebase.database().ref('users/' + userId).set({
        username: name,
        email: email,
        profile_picture : imageUrl
    });
}

var retrieveData = firebase.database().ref('posts/' + postId + '/starCount');
retrieveData.on('value', function(snapshot) {
    updateStarCount(postElement, snapshot.val());
});

function writeNewPost(uid, first, last, picture) {
    // A post entry.
    var postData = {
        uid: uid,
        firstName: first,
        lastName: last,
        authorPic: picture
    };

    // Get a key for a new Post.
    var newPostKey = firebase.database().ref().child('posts').push().key;

    // Write the new post's data simultaneously in the posts list and the user's post list.
    var updates = {};
    updates['/posts/' + newPostKey] = postData;
    updates['/user-posts/' + uid + '/' + newPostKey] = postData;

    return firebase.database().ref().update(updates);
}


// Profile: Get data from server and show in the page
firebase.auth().onAuthStateChanged((user)=>{
    if (user) {
        //   User is signed in.
        let user = firebase.auth().currentUser;
        let uid
        if(user != null){
            uid = user.uid;
        }
        let firebaseRefKey = firebase.database().ref().child(uid);
        firebaseRefKey.on('value', (dataSnapShot)=>{
            document.getElementById("userProfileFullName").innerHTML = dataSnapShot.val().userFullName;
            document.getElementById("userProfileLastName").innerHTML = dataSnapShot.val().userSurname;
            // userEmail = dataSnapShot.val().userEmail;
            // userPassword = dataSnapShot.val().userPassword;
            // document.getElementById("userPfBio").innerHTML = dataSnapShot.val().userBio;
        })
    } else {
        //   No user is signed in.
    }
});
// Show edit profile form with detail
function showEditProfileForm(){
    document.getElementById("profileSection").style.display = "none"
    document.getElementById("editProfileForm").style.display = "block"
    let userPfFullName = document.getElementById("userPfFullName").innerHTML;
    let userPfSurname = document.getElementById("userPfSurname").innerHTML;
    let userPfBio = document.getElementById("userPfBio").innerHTML;
    document.getElementById("userFullName").value = userPfFullName;
    document.getElementById("userSurname").value = userPfSurname;
    document.getElementById("userBio").value = userPfBio;
}
// Hide edit profile form
function hideEditProfileForm(){
    document.getElementById("profileSection").style.display = "block";
    document.getElementById("editProfileForm").style.display = "none";
}
// Save profile and update database
function saveProfile(){
    let userFullName = document.getElementById("userFullName").value
    let userSurname = document.getElementById("userSurname").value
    let userBio = document.getElementById("userBio").value
    let userFullNameFormat = /^([A-Za-z.\s_-])/;
    let checkUserFullNameValid = userFullName.match(userFullNameFormat);
    if(checkUserFullNameValid == null){
        return checkUserFirstName();
    }else if(userSurname === ""){
        return checkUserLastName();
    }else{
        let user = firebase.auth().currentUser;
        let uid;
        if(user != null){
            uid = user.uid;
        }
        let firebaseRef = firebase.database().ref();
        let userData = {
            userFullName: userFullName,
            userSurname: userSurname,
            userBio: userBio,
        }
        firebaseRef.child(uid).set(userData);
        return new Swal({
            type: 'successful',
            title: 'Update successful',
            text: 'Profile updated.',
        }).then((value) => {
            setTimeout(function(){
                document.getElementById("profileSection").style.display = "block";

                document.getElementById("editProfileForm").style.display = "none";
            }, 1000)
        });
    }
}
// Working For Sign Out
function signOut(){
    firebase.auth().signOut().then(function() {
        // Sign-out successful.
        return new Swal({
            type: 'successful',
            title: 'Signed Out',
        }).then((value) => {
            setTimeout(function(){
                window.location.replace("../login.html");
            }, 1000)
        });
    }).catch(function(error) {
        // An error happened.
        let errorMessage = error.message;
        swal({
            type: 'error',
            title: 'Error',
            text: "Error",
        })
    });
}