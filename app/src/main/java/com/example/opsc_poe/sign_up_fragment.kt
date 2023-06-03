package com.example.opsc_poe

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.opsc_poe.databinding.SignUpFragmentBinding

class sign_up_fragment : Fragment(R.layout.sign_up_fragment){


    private var _binding: SignUpFragmentBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SignUpFragmentBinding.inflate(inflater, container, false)
        val view = binding.root


        //-------------------------------------------------
        //code here

        //sign in button
        binding.tvSignUpButton.setOnClickListener {



            val trySignUp  =  Temp_UserDataClass()
            trySignUp.RegisterUser(binding.etEmail.text.toString(), binding.etUsername.text.toString(), binding.etPassword.text.toString(), requireContext())



            //-------------------------------------------------
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}