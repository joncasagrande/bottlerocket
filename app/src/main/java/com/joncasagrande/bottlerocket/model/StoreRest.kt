package com.joncasagrande.bottlerocket.model

import com.google.gson.annotations.SerializedName

data class StoreRest(
    @SerializedName("stores")
    var stores : List<Store>
)