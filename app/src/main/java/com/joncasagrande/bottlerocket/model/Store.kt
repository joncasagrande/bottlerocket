package com.joncasagrande.bottlerocket.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "store")
class Store(@PrimaryKey
                 @SerializedName("storeID")
                 val id: Int,
                 val address : String, val city: String, val name: String, val zipcode: String,
                 @SerializedName("storeLogoURL")
                 val logo: String,
                 val phone: String, val state :String,
                 val latitude : Double, val longitude : Double) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readDouble(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(address)
        parcel.writeString(city)
        parcel.writeString(name)
        parcel.writeString(zipcode)
        parcel.writeString(logo)
        parcel.writeString(state)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Store

        if (id != other.id) return false
        if (address != other.address) return false
        if (city != other.city) return false
        if (name != other.name) return false
        if (zipcode != other.zipcode) return false
        if (logo != other.logo) return false
        if (phone != other.phone) return false
        if (state != other.state) return false
        if (latitude != other.latitude) return false
        if (longitude != other.longitude) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + address.hashCode()
        result = 31 * result + city.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + zipcode.hashCode()
        result = 31 * result + logo.hashCode()
        result = 31 * result + phone.hashCode()
        result = 31 * result + state.hashCode()
        result = 31 * result + latitude.hashCode()
        result = 31 * result + longitude.hashCode()
        return result
    }


    companion object CREATOR : Parcelable.Creator<Store> {
        override fun createFromParcel(parcel: Parcel): Store {
            return Store(parcel)
        }

        override fun newArray(size: Int): Array<Store?> {
            return arrayOfNulls(size)
        }
    }

}


/*
{
      "address": "7801 Citrus Park Town Center Mall",
      "city": "Tampa",
      "name": "Macy's",
      "latitude": "28.068052",
      "zipcode": "33625",
      "storeLogoURL": "http://sandbox.bottlerocketapps.com/BR_Android_CodingExam_2015_Server/images/macys.jpeg",
      "phone": "813-926-7300",
      "longitude": "-82.573301",
      "storeID": "1234",
      "state": "FL"
    }
 */