package com.example.codefest20

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.codefest20.databinding.FragmentBookRoomBinding
import com.example.codefest20.databinding.FragmentHotelSearchResultBinding

class HotelSearchResultFragment : Fragment() {


    private var _binding: FragmentHotelSearchResultBinding? = null

    private val binding: FragmentHotelSearchResultBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHotelSearchResultBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.setOnClickListener {

        }
    }
}
