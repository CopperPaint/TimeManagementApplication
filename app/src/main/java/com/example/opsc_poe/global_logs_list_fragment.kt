package com.example.opsc_poe

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.opsc_poe.databinding.ActivityGlobalLogsListFragmentBinding
import com.example.opsc_poe.databinding.ActivityViewDetailsFragmentBinding


class global_logs_list_fragment : Fragment(R.layout.activity_global_logs_list_fragment) {

        private var _binding: ActivityGlobalLogsListFragmentBinding? = null
        // This property is only valid between onCreateView and
// onDestroyView.
        private val binding get() = _binding!!


        @SuppressLint("Range")
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = ActivityGlobalLogsListFragmentBinding.inflate(inflater, container, false)
            val view = binding.root




            //-------------------------------------------------
            //code here





            //------------------------------------------------------




            return view
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }
