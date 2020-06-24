package com.example.restareareview

import com.google.gson.annotations.SerializedName

class PlaceModel {
    @SerializedName("휴게소명")
    var name: String = ""

    @SerializedName("위도")
    var x: String = ""

    @SerializedName("경도")
    var y: String = ""
}