package com.example.opsc_poe

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.opsc_poe.databinding.ActivityGlobalLogsCategoryFragmentBinding
import com.example.opsc_poe.databinding.ActivityGlobalLogsListFragmentBinding

class global_logs_category_fragment : Fragment(R.layout.activity_global_logs_category_fragment) {


    private var _binding: ActivityGlobalLogsCategoryFragmentBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!


    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityGlobalLogsCategoryFragmentBinding.inflate(inflater, container, false)
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