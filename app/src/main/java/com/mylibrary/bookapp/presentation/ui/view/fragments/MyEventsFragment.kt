package com.mylibrary.bookapp.presentation.ui.view.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mylibrary.bookapp.presentation.ui.adapter.EventAdapter
import com.mylibrary.bookapp.presentation.ui.viewmodel.EventViewModel
import dagger.hilt.android.AndroidEntryPoint
import mylibrary.bookapp.R
import mylibrary.bookapp.databinding.FragmentMyEventsBinding

@AndroidEntryPoint
class MyEventsFragment : Fragment() {

    private var _binding: FragmentMyEventsBinding? = null
    private val binding get() = _binding!!
    private val eventViewModel: EventViewModel by viewModels()
    private lateinit var eventAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        myEventsObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun myEventsObserver(){
        eventViewModel.getMyEvent().observe(viewLifecycleOwner) {
            eventAdapter.submitList(it)
        }
    }

    private fun initAdapter() {
        eventAdapter = EventAdapter(true) { event ->
            val action = MyEventsFragmentDirections.actionNavMyEventsToEventDescriptionFragment(
                event.id
            )
            findNavController().navigate(action)
        }

        binding.apply {
            rvMyEvents.apply {
                adapter = eventAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        eventAdapter.deleteItem.observe(viewLifecycleOwner) { eventPair ->
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage(R.string.cancel_event_attendance)
                .setPositiveButton(
                    R.string.accept
                ) { _, _ ->
                    eventViewModel.deleteEventById(eventPair.second)
                    myEventsObserver()
                }
                .setNegativeButton(
                    R.string.cancel
                ) { dialog, id ->
                    dialog.dismiss()
                }
            builder.create().show()
        }
    }
}