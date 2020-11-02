package com.coolblue.pos.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.coolblue.pos.R
import com.coolblue.pos.adapter.ProductAdapter
import com.coolblue.pos.adapter.ProductLoadStateAdapter
import com.coolblue.pos.databinding.FragmentProductBinding
import com.coolblue.pos.viewmodel.ProductGalleryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductSearchFragment : Fragment(R.layout.fragment_product) {

    private val viewModel by viewModels<ProductGalleryViewModel>()

    private var _binding: FragmentProductBinding? = null
    private val binding: FragmentProductBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentProductBinding.bind(view)

        val adapter = ProductAdapter()

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = null
            recyclerView.adapter =
                adapter.withLoadStateHeaderAndFooter(header = ProductLoadStateAdapter { adapter.retry() },
                    footer = ProductLoadStateAdapter { adapter.retry() })

            btnRetry.setOnClickListener {
                adapter.retry()
            }
        }

        viewModel.products.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                btnRetry.isVisible = loadState.source.refresh is LoadState.Error
                txtError.isVisible = loadState.source.refresh is LoadState.Error

                setupEmptyState(loadState, adapter)
            }

        }

        setHasOptionsMenu(true)
    }

    private fun FragmentProductBinding.setupEmptyState(
        loadState: CombinedLoadStates,
        adapter: ProductAdapter
    ) {
        if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached &&
            adapter.itemCount < 1
        ) {
            recyclerView.isVisible = false
            txtEmpty.isVisible = true
        } else {
            txtEmpty.isVisible = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_product, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding.recyclerView.scrollToPosition(0)
                    viewModel.searchProducts(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }


        })

    }


    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}