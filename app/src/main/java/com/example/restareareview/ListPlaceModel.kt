package com.example.restareareview

import com.google.gson.annotations.SerializedName

class ListPlaceModel {
    @SerializedName("records")
    var records: ArrayList<PlaceModel> = ArrayList()
}