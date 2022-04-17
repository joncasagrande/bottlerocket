package com.joncasagrande.bottlerocket.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.joncasagrande.bottlerocket.EXTRA_STORE
import com.joncasagrande.bottlerocket.R
import com.joncasagrande.bottlerocket.databinding.FragmentListStoreBinding
import com.joncasagrande.bottlerocket.ui.StoreActivity
import com.joncasagrande.bottlerocket.ui.adapter.StoreRecyclerView
import com.joncasagrande.bottlerocket.ui.model.UiState
import com.joncasagrande.bottlerocket.ui.viewModel.ListStoreViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ListStoreFragment : Fragment() {


    lateinit var storeAdapter: StoreRecyclerView
    private val viewModel by viewModel<ListStoreViewModel>()

    private lateinit var binding : FragmentListStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListStoreBinding.inflate(layoutInflater,container,false)
        setUi()
        setObservers()
        viewModel.loadStore()

        return binding.root
    }

    private fun setUi() {
        val layoutManager = LinearLayoutManager(binding.storeRV.context)
        storeAdapter = StoreRecyclerView(
            onClick = {
                val intent = Intent(requireContext(), StoreActivity::class.java).apply {
                    putExtra(EXTRA_STORE, it)
                }
                ContextCompat.startActivity(requireContext(), intent, null)
            }
        )
        with(binding.storeRV) {
            adapter = storeAdapter
            this.layoutManager = layoutManager

            this.addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    layoutManager.orientation
                )
            )
        }
    }

    private fun setObservers() {
        viewModel.listStore.observe(
            this,
            Observer {
                when (val uiState = it) {
                    is UiState.Display -> {
                        storeAdapter.swapData(uiState.data)
                        binding.progressBar.visibility = View.GONE
                    }
                    is UiState.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is UiState.Error -> {
                        binding.storeRV.visibility = View.GONE
                        binding.noResultsImageView.visibility = View.VISIBLE
                        binding.noResultsTextView.text = getString(R.string.no_conection)
                        binding.progressBar.visibility = View.GONE
                    }
                    is UiState.Empty -> {
                        binding.storeRV.visibility = View.GONE
                        binding.noResultsImageView.visibility = View.VISIBLE
                        binding.noResultsTextView.text = getString(R.string.no_results)
                        binding.progressBar.visibility = View.GONE
                    }
                }
            })

        viewModel.errorMessage.observe(this,
            Observer<Boolean> {
                Toast.makeText(requireContext(), R.string.no_conection, Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            })
    }


    companion object {
        @JvmStatic
        fun newInstance() = ListStoreFragment()
    }
}