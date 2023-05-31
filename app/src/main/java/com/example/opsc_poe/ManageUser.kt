package com.example.opsc_poe

import android.content.ContentValues
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ManageUser {

    private val appDatabase = Firebase.firestore
    private val newPasswordManager = ManagePassword()
    var userEmail : String = ""

    fun RegisterUser (email: String, username: String, password: String){

        val userPasswordSalt = newPasswordManager.generateRandomSalt()
        val userPasswordHash = newPasswordManager.generateHash(password,userPasswordSalt)


        var userHashMap = hashMapOf(
            "Email" to email,
            "PasswordHash" to userPasswordHash,
            "PasswordSalt" to userPasswordSalt,
            "Username" to username
        )

        //add new document with a generated ID
        appDatabase.collection("Users")
            .add(userHashMap)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "Document Snapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }


    }



    fun logInUser (emailAttempt: String, passwordAttempt: String){

        var userPasswordHash = ""
        var userPasswordSalt = ""
        var userUsername = ""

        appDatabase.collection("Users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")

                }

            }
            .addOnFailureListener{ exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }


    }




    /*
    fun logInUser (emailAttempt: String, passwordAttempt: String): String{

        var userPasswordHash = ""
        var userPasswordSalt = ""
        var userUsername = ""

        appDatabase.collection("Activity")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")

                    //userData.put(document.data.getValue("userEmail").toString(), document.data.getValue("userPassword").toString())

                   // userEmail = document.data.getValue("Email").toString()
                    userEmail = document.data.getValue("CategoryID").toString()
                   // userPasswordHash = document.data.getValue("PasswordHash").toString()
                    //userPasswordSalt = document.data.getValue("PasswordSalt").toString()
                    //userUsername = document.data.getValue("Username").toString()

                    //userList.add(document.data.getValue("Email").toString())

                   // if (emailAttempt == userEmail) {

                       // userPasswordHash = document.data.getValue("PasswordHash").toString()
                      //  userPasswordSalt = document.data.getValue("PasswordSalt").toString()

                    //}

                }

            }
            .addOnFailureListener{ exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }

        return userEmail

    }

     */


}