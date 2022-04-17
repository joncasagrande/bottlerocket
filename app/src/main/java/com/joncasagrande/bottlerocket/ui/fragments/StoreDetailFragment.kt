package com.joncasagrande.bottlerocket.ui.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.joncasagrande.bottlerocket.EXTRA_STORE
import com.joncasagrande.bottlerocket.R
import com.joncasagrande.bottlerocket.databinding.FragmentStoreDetailBinding
import com.joncasagrande.bottlerocket.model.Store

class StoreDetailFragment : Fragment() {

    private lateinit var binding: FragmentStoreDetailBinding

    protected val storeExtra by lazy {
        requireNotNull(requireArguments().getSerializable(EXTRA_STORE) as Store)
        { "Store must be specified!" }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoreDetailBinding.inflate(layoutInflater, container, false)
        setUi()
        return binding.root
    }

    private fun setUi() {
        with(binding) {
            addressTv.text =
                getString(R.string.address, storeExtra.address)
            cityTv.text = getString(R.string.city, storeExtra.city)
            zipcodeTv.text =
                getString(R.string.zipcode, storeExtra.zipcode)
            nameTV.text = storeExtra.name
            Glide.with(requireContext()).load(storeExtra.logo).into(logoIV)

            directionBT.setOnClickListener {
                getDirections()
            }
            callBT.setOnClickListener {
                phoneCall()
            }

        }
    }


    private fun phoneCall() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${storeExtra.phone}"))
            startActivity(intent)
        } else {
            requestPermissions(arrayOf(android.Manifest.permission.CALL_PHONE), 1010)
        }
    }

    private fun getDirections() {
        val gmmIntentUri = Uri.parse("geo:${storeExtra.latitude},${storeExtra.longitude}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    companion object {
        @JvmStatic
        fun newInstance(storeExtra: Store) =
            StoreDetailFragment().apply {
                bundleOf(
                    EXTRA_STORE to storeExtra
                )
            }
    }
}