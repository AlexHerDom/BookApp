package com.mylibrary.bookapp.presentation.ui.view.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mylibrary.bookapp.presentation.ui.adapter.BookAdapter
import com.mylibrary.bookapp.presentation.ui.view.activities.MainActivity
import com.mylibrary.bookapp.presentation.ui.viewmodel.BookViewModel
import com.mylibrary.core.data.Status
import com.mylibrary.core.domain.DataX
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.internal.managers.FragmentComponentManager
import mylibrary.bookapp.R
import mylibrary.bookapp.databinding.FragmentFindBooksBinding

@AndroidEntryPoint
class FindBooksFragment : Fragment() {

    private var _binding: FragmentFindBooksBinding? = null
    private val binding get() = _binding!!
    private val bookViewModel: BookViewModel by viewModels()
    private lateinit var bookAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFindBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        setHasOptionsMenu(true)
        bookViewModel.getBooks().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    bookAdapter.setData(it.data?.data as MutableList<DataX>?)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        menu.clear()
        inflater.inflate(R.menu.find_menu, menu)
        val item = menu.findItem(R.id.action_search)
        val searchView =
            SearchView((FragmentComponentManager.findActivity(view?.context) as MainActivity).supportActionBar!!.themedContext)
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
        item.actionView = searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                bookAdapter.filter.filter(newText)
                return false
            }
        })
        searchView.setOnClickListener {
            print("")
        }
    }

    private fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun initAdapter() {
        bookAdapter = BookAdapter()

        binding.apply {
            rvBooks.apply {
                adapter = bookAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                addItemDecoration(
                    DividerItemDecoration(
                        rvBooks.context,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
        }
    }
}