package com.example.edressing.ui.home

import com.example.meteoguesser.MeteoAPI.MeteoResponse

/**
 *Created by $(USER) on $(DATE).
 */
sealed class MeteoModel {

}
data class  MeteoSuccess(val meteo : MeteoResponse) : MeteoModel()
object  MeteoFailure: MeteoModel()
object  MeteoLoader: MeteoModel()
