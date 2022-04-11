package com.mylibrary.bookapp.presentation.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mylibrary.bookapp.presentation.ui.adapter.EventAdapter
import com.mylibrary.bookapp.presentation.ui.viewmodel.BookViewModel
import com.mylibrary.core.data.Status
import dagger.hilt.android.AndroidEntryPoint
import mylibrary.bookapp.databinding.FragmentMainBinding


@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val bookViewModel: BookViewModel by viewModels()
    private lateinit var eventAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        //initAdapter()

        //bookViewModel.getBooks()
    }

    private fun initObservers() {
        bookViewModel.getBooks().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    //eventAdapter.submitList(it.data.orEmpty())
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

    /* private fun initAdapter() {
         eventAdapter = EventAdapter { event ->
             val action =
                 com.mylibrary.bookapp.ui.view.fragments.MainFragmentDirections.actionMainFragmentToEventDescriptionFragment(
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
     }*/

    private fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressBar.visibility = View.INVISIBLE
    }
}