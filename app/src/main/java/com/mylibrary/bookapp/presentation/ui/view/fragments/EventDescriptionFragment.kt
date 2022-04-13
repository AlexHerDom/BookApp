package com.mylibrary.bookapp.presentation.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mylibrary.bookapp.presentation.ui.viewmodel.EventViewModel
import com.mylibrary.core.data.Status
import com.mylibrary.core.domain.EventDescriptionResponse
import dagger.hilt.android.AndroidEntryPoint
import mylibrary.bookapp.databinding.FragmentEventDescriptionBinding

@AndroidEntryPoint
class EventDescriptionFragment : Fragment() {
    private lateinit var binding: FragmentEventDescriptionBinding
    private val args: EventDescriptionFragmentArgs by navArgs()
    private val eventViewModel: EventViewModel by viewModels()
    private var eventId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventId = args.eventId
        initObservers()
    }

    private fun initObservers() {
        eventViewModel.getEventDescription(eventId)
            .observe(viewLifecycleOwner) { eventDescription ->
                when (eventDescription.status) {
                    Status.SUCCESS -> {
                        showEventDescription(eventDescription.data)
                        hideProgress()
                    }
                    Status.LOADING -> {
                        showProgress()
                    }
                    Status.ERROR -> {
                        Toast.makeText(
                            requireContext(),
                            eventDescription.message,
                            Toast.LENGTH_LONG
                        ).show()
                        hideProgress()
                    }
                }
            }
    }

    private fun showEventDescription(eventDescription: EventDescriptionResponse?) {
        binding.apply {
            Glide.with(requireContext()).load(eventDescription?.data?.avatar).into(ivEvent)
            tvName.text =
                "${eventDescription?.data?.first_name} ${eventDescription?.data?.last_name}"
            tvDescription.text = "${eventDescription?.data?.last_name}"
            tvContact.text = "${eventDescription?.data?.email}"

            Glide.with(requireContext()).load(
                "https://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=13&size=600x300&maptype=roadmap" +
                        "&markers=color:red%7Clabel:C%7C40.718217,-73.998284" +
                        "&key=AIzaSyBe7c0cOj66R8dLw9WKutl5mDu2wIov7Bg"
            ).centerCrop().into(ivMap)
        }
    }

    private fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressBar.visibility = View.INVISIBLE
    }
}