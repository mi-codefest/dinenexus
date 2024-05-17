package com.example.codefest20

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.codefest20.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    private val binding: FragmentSearchBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.letsGoButton.setOnClickListener {
            when(binding.searchOptionsGroup.checkedRadioButtonId){
                R.id.bookSpa -> {

                }
                R.id.eatAroundTown ->{
                    findNavController().navigate(R.id.action_searchFragment_to_searchEatAroundTownFragment)
                }
                R.id.bookGolf ->{

                }
                R.id.bookARoom ->{
                    findNavController().navigate(R.id.action_searchFragment_to_bookARoomFragment)
                }
                R.id.findStarbucks ->{
                    val gmmIntentUri = Uri.parse("geo:0,0?q=starbucks")
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    startActivity(mapIntent)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}