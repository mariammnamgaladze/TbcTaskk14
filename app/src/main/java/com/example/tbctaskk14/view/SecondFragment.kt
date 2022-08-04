package com.example.tbctaskk14.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.tbctaskk14.databinding.FragmentSecondBinding
import com.example.tbctaskk14.model.Data

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val args: SecondFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding  = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

    }

    private fun init() = with(binding) {
        val apartment = args.name as Data.Content
        Glide.with(imageView2)
            .load(apartment.cover)
            .into(imageView2)
        titleTV2.text = apartment.titleKA
        descriptionTV2.text = apartment.descriptionKA
        dateTV2.text = apartment.publishDate
    }






    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

