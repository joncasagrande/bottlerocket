package com.joncasagrande.bottlerocket.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "store")
@Parcelize
data class Store(
    @PrimaryKey
    @SerializedName("storeID")
    val id: Int,
    val address: String, val city: String, val name: String, val zipcode: String,
    @SerializedName("storeLogoURL")
    val logo: String,
    val phone: String, val state: String,
    val latitude: Double, val longitude: Double
) : Parcelable

