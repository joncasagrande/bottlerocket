package com.joncasagrande.bottlerocket.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.joncasagrande.bottlerocket.model.Store
import com.joncasagrande.bottlerocket.ui.adapter.StoreRecyclerView
import com.joncasagrande.bottlerocket.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.KoinComponent
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService





class MainActivity : AppCompatActivity(), KoinComponent{

    lateinit var storeAdapter : StoreRecyclerView
    lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.joncasagrande.bottlerocket.R.layout.activity_main)
        viewModel = getViewModel()

        val layoutManager = LinearLayoutManager(this)
        storeAdapter = StoreRecyclerView()
        storeRV.adapter =  storeAdapter
        storeRV.setLayoutManager(layoutManager)
        val dividerItemDecoration = DividerItemDecoration(
            storeRV.getContext(),
            layoutManager.getOrientation()
        )
        storeRV.addItemDecoration(dividerItemDecoration)

        loadStore()


        viewModel.listStore.observe(this@MainActivity,
             Observer<List<Store>>{
            storeAdapter.swapData(it)
                 progressBar.visibility = View.GONE
        })

        viewModel.errorMessage.observe(this@MainActivity,
            Observer<String>{
               Toast.makeText(this@MainActivity,it,Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
            })

    }

    private fun loadStore(){
        viewModel.loadStore(this)
        progressBar.visibility = View.VISIBLE
    }
}
