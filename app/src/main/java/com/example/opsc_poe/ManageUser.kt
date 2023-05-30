package com.example.opsc_poe

import android.content.ContentValues
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ManageUser {

    private val appDatabase = Firebase.firestore
    private val newPasswordManager = ManagePassword()

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

}