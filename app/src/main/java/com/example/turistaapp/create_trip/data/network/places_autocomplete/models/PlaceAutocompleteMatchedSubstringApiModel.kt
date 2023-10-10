package com.example.turistaapp.create_trip.data.network.places_autocomplete.models

import com.google.gson.annotations.SerializedName

data class PlaceAutocompleteMatchedSubstringApiModel (
    @SerializedName("length") val lengthApi: Int?,
    @SerializedName("offset") val offsetApi: Int?
)