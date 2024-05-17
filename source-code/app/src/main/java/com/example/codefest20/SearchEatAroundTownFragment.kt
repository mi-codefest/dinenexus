package com.example.codefest20

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.codefest20.databinding.FragmentEatAroundTownSearchBinding
import com.example.codefest20.databinding.FragmentSearchBinding

class SearchEatAroundTownFragment : Fragment() {

    private var _binding: FragmentEatAroundTownSearchBinding? = null

    private val binding: FragmentEatAroundTownSearchBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEatAroundTownSearchBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchRestaurantButton.setOnClickListener {
            val location =binding.enterYourLocation.editText?.text
            val intent = Intent(ACTION_VIEW)
            intent.data = Uri.parse("https://eataroundtown.marriott.com/search?campaignCode=&location=${Uri.encode(location.toString())}")
            activity?.startActivity(intent)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}