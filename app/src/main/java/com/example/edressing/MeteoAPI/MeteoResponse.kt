package com.example.edressing.MeteoAPI

/**
 *Created by $(USER) on $(DATE).
 */
data class MeteoResponse(
    var coord : Coordonnees,
    var weather : List<ListWeather>,
    var main : Main,
    var visibility : Int,
    var name : String
)
