package com.mylibrary.bookapp.presentation.ui.view.fragments

import android.app.AlertDialog
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
import mylibrary.bookapp.R
import mylibrary.bookapp.databinding.FragmentEventDescriptionBinding

@AndroidEntryPoint
class EventDescriptionFragment : Fragment() {
    private lateinit var binding: FragmentEventDescriptionBinding
    private val args: EventDescriptionFragmentArgs by navArgs()
    private val eventViewModel: EventViewModel by viewModels()
    private var eventId: Int = 0
    private var currentEvent: EventDescriptionResponse? = null

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

        binding.fabAddEvent.setOnClickListener {
            showDialogConfirmation()
        }
    }

    private fun initObservers() {
        eventViewModel.getEventDescription(eventId)
            .observe(viewLifecycleOwner) { eventDescription ->
                when (eventDescription.status) {
                    Status.SUCCESS -> {
                        showEventDescription(eventDescription.data)
                        currentEvent = eventDescription.data
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
            tvDescription.text = "${eventDescription?.support?.text}"
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

    private fun showDialogConfirmation() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage(R.string.add_event)
            .setPositiveButton(
                R.string.accept
            ) { dialog, id ->
                currentEvent?.let { event ->
                    eventViewModel.saveEvent(event)
                }
            }
            .setNegativeButton(
                R.string.cancel
            ) { dialog, id ->
                dialog.dismiss()
            }
        builder.create().show()
    }
}