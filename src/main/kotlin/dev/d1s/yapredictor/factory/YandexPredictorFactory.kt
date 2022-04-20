package dev.d1s.yapredictor.factory

import dev.d1s.yapredictor.api.YandexPredictor
import dev.d1s.yapredictor.impl.YandexPredictorImpl

public fun yandexPredictor(apiKey: String): YandexPredictor = YandexPredictorImpl(apiKey)