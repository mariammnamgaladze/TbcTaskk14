package com.example.tbctaskk14.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbctaskk14.ResponseCatcher
import com.example.tbctaskk14.databinding.FragmentMainBinding
import com.example.tbctaskk14.model.Data
import com.example.tbctaskk14.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val myAdapter by lazy { MyAdapter() }

    private val viewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding  = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecycler()
        onClick()
        getInfo()


    }


    private fun setUpRecycler() {
        binding.recyclerView.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(context)
        }
        viewModel.info()
    }

    private fun onClick() {
        myAdapter.onItemClickListener = {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToSecondFragment(it))
        }
    }

    private fun getInfo() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.myFlow.collect { item ->
                    when(item) {
                        is ResponseCatcher.Success -> successCatcher(item.resultList)
                        is ResponseCatcher.Error -> errorCatcher(item.errorBody)
                        else -> {}
                    }
                    binding.progressBar.isVisible = item.isLoading
                }
            }
        }
    }

    private fun errorCatcher(errorBody: String) {
        Snackbar.make(binding.root, errorBody, Snackbar.LENGTH_SHORT).show()
    }

    private fun successCatcher(resultList: List<Data.Content>) {
        myAdapter.submitList(resultList)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}