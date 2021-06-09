package com.example.edressing.MeteoAPI

import java.io.FileDescriptor

data class ListWeather (
    var id: Int,
    var main: String,
    var description: String,
    var icon: String
)
