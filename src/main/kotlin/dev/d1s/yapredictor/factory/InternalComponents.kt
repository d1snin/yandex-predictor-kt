package dev.d1s.yapredictor.factory

import com.google.gson.Gson
import dev.d1s.yapredictor.service.YandexPredictorService
import dev.d1s.yapredictor.service.impl.YandexPredictorServiceImpl

internal fun yandexPredictorService(): YandexPredictorService = YandexPredictorServiceImpl()
internal fun gson() = Gson()