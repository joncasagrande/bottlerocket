package com.joncasagrande.bottlerocket.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.joncasagrande.bottlerocket.EXTRA_STORE
import com.joncasagrande.bottlerocket.model.Store
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.joncasagrande.bottlerocket.R
import kotlinx.android.synthetic.main.activity_store.*

class StoreActivity : AppCompatActivity() {

    private val storeExtra: Store
        get() = intent.getParcelableExtra(EXTRA_STORE) ?: throw IllegalArgumentException()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)
    }

    override fun onStart() {
        super.onStart()

        addressTv.text = getString(R.string.address,storeExtra.address)
        cityTv.text = getString(R.string.city,storeExtra.city)
        zipcodeTv.text = getString(R.string.zipcode,storeExtra.zipcode)
        nameTV.text = storeExtra.name
        Glide.with(this).load(storeExtra.logo).into(logoIV)
    }


    fun phoneCall(view: View) {
        if( ContextCompat.checkSelfPermission(view.context,android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${storeExtra.phone}"))
            startActivity(intent)
        }else{
            requestPermissions(arrayOf(android.Manifest.permission.CALL_PHONE),1010)
        }
    }

    fun getDirections(view: View) {

        val gmmIntentUri = Uri.parse("geo:${storeExtra.latitude},${storeExtra.longitude}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}
