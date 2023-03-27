package com.example.usermanagementapp.firebase

import android.util.Log
import com.example.usermanagementapp.activities.SignInActivity
import com.example.usermanagementapp.activities.SignUpActivity
import com.example.usermanagementapp.models.User
import com.example.usermanagementapp.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions


class FirestoreClass {

    // Create a instance of Firebase Firestore
    private val mFireStore = FirebaseFirestore.getInstance()


    fun registerUser(activity: SignUpActivity, userInfo: User) {

        mFireStore.collection(Constants.USERS)
            // Document ID for users fields. Here the document it is the User ID.
            .document(getCurrentUserID())
            // Here the userInfo are Field and the SetOption is set to merge. It is for if we wants to merge
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {

                // Here call a function of base activity for transferring the result to it.
                activity.userRegisteredSuccess()
            }
            .addOnFailureListener { e ->
                Log.e(
                    activity.javaClass.simpleName,
                    "Error writing document",
                    e
                )
            }
    }


    fun getCurrentUserID(): String {

        val currentUser = FirebaseAuth.getInstance().currentUser

        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }

        return currentUserID


    }


    fun signInUser(activity: SignInActivity) {

        mFireStore.collection(Constants.USERS)
            // The document id to get the Fields of user.
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->



                val loggedInUser = document.toObject(User::class.java)


                if(loggedInUser!=null){

                activity.signInSuccess(loggedInUser)}

            }
            .addOnFailureListener { e ->
                Log.e(
                    "Sign-In User",
                    "Error while getting loggedIn user details",
                    e
                )
            }
    }




}

