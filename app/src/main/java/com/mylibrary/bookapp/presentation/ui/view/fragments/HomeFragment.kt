package com.mylibrary.bookapp.presentation.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mylibrary.bookapp.presentation.ui.adapter.EventAdapter
import com.mylibrary.bookapp.presentation.ui.viewmodel.BookViewModel
import com.mylibrary.bookapp.presentation.ui.viewmodel.EventViewModel
import com.mylibrary.core.data.Status
import dagger.hilt.android.AndroidEntryPoint
import mylibrary.bookapp.databinding.FragmentHomeBinding
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val eventViewModel: EventViewModel by viewModels()
    private lateinit var eventAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initObservers()
    }

    private fun initObservers() {
        eventViewModel.getEvents().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    eventAdapter.submitList(it.data?.data.orEmpty())
                    hideProgress()
                }
                Status.LOADING -> {
                    showProgress()
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    hideProgress()
                }
            }
        }
    }

    private fun initAdapter() {
        eventAdapter = EventAdapter(false) { event ->
            val action = HomeFragmentDirections.actionNavHomeToEventDescriptionFragment(
                event.id
            )
            findNavController().navigate(action)
        }

        binding.apply {
            rvEvents.apply {
                adapter = eventAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
    }

    private fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}