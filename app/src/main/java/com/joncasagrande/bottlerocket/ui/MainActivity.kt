package com.joncasagrande.bottlerocket.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.joncasagrande.bottlerocket.R
import com.joncasagrande.bottlerocket.ui.fragments.ListStoreFragment
import org.koin.core.KoinComponent


class MainActivity : AppCompatActivity(), KoinComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.your_placeholder, ListStoreFragment.newInstance())
        ft.commit()

    }


}
