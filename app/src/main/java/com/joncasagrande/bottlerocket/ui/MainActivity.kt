package com.joncasagrande.bottlerocket.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.joncasagrande.bottlerocket.R
import com.joncasagrande.bottlerocket.ui.adapter.StoreRecyclerView
import com.joncasagrande.bottlerocket.ui.model.UiState
import com.joncasagrande.bottlerocket.ui.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent

class MainActivity : AppCompatActivity(), KoinComponent {

    lateinit var storeAdapter: StoreRecyclerView
    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUi()
        setObservers()
        viewModel.loadStore()
    }

    private fun setUi() {
        val layoutManager = LinearLayoutManager(this)
        storeAdapter = StoreRecyclerView()
        with(storeRV) {
            adapter = storeAdapter
            this.layoutManager = layoutManager

            this.addItemDecoration(
                DividerItemDecoration(
                    storeRV.context,
                    layoutManager.orientation
                )
            )
        }
    }

    private fun setObservers() {
        viewModel.listStore.observe(
            this@MainActivity,
            Observer {
                when (val uiState = it) {
                    is UiState.Display -> {
                        storeAdapter.swapData(uiState.data)
                        progressBar.visibility = View.GONE
                    }
                    is UiState.Loading -> progressBar.visibility = View.VISIBLE
                    is UiState.Error -> progressBar.visibility = View.GONE
                    is UiState.Empty -> progressBar.visibility = View.GONE
                }
            })

        viewModel.errorMessage.observe(this@MainActivity,
            Observer<Boolean> {
                Toast.makeText(this@MainActivity, R.string.no_conection, Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
            })
    }

}
