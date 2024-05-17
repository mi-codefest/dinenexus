package com.example.codefest20

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.codefest20.databinding.FragmentBookRoomBinding

class BookARoomFragment : Fragment() {
    private var _binding: FragmentBookRoomBinding? = null

    private val binding: FragmentBookRoomBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookRoomBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.setOnClickListener {
            findNavController().navigate(R.id.action_bookARoomFragment_to_hotelSearchResultFragment)
        }
    }

}