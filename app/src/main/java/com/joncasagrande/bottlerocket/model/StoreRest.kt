package com.joncasagrande.bottlerocket.model

import com.google.gson.annotations.SerializedName

class StoreRest(
    @SerializedName("stores")
    var stores : List<Store>
)