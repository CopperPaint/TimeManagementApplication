package com.example.opsc_poe

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentManager

class FragmentHandler {
//class to handle to use of fragments and navigating between them


    //method to switch between fragments (uses the desired fragment, the ID of the fragment container and the supportFragmentManager)
    public fun replaceFragment(fragment : Fragment, fragmentContainerID : Int, fragmentManager : FragmentManager) {
        //begin transition to desired fragment
        val fragmentTransaction = fragmentManager.beginTransaction()

        //set the desired fragment to be swapped
        fragmentTransaction.replace(fragmentContainerID, fragment)

        //commit fragment change
        fragmentTransaction.commit()
    }



}