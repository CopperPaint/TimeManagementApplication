package com.example.opsc_poe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.marginLeft
import androidx.fragment.app.Fragment
import com.example.opsc_poe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        //Hide the action bar
        supportActionBar?.hide()

        //Set view binding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //create local fragment controller
        val fragmentControl = FragmentHandler()

        //set the sign in fragment to be the initial view
        fragmentControl.replaceFragment(sign_in_fragment(), R.id.fcFragmentContainer, supportFragmentManager)


        //Sign in view activation
        binding.tvSignIn.setOnClickListener{
            //replaceFragment(sign_in_fragment())
            fragmentControl.replaceFragment(sign_in_fragment(), R.id.fcFragmentContainer, supportFragmentManager)
        }

        //Sign up view activation
        binding.tvSignUp.setOnClickListener{
            //replaceFragment(sign_up_fragment())
            fragmentControl.replaceFragment(sign_up_fragment(), R.id.fcFragmentContainer, supportFragmentManager)
        }




    }

  /*  private fun replaceFragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fcFragmentContainer, fragment)
        fragmentTransaction.commit()
    }*/
}