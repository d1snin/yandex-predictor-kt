package dev.d1s.yapredictor.impl

import dev.d1s.yapredictor.api.YandexPredictor
import dev.d1s.yapredictor.domain.Completion
import dev.d1s.yapredictor.domain.Language
import dev.d1s.yapredictor.factory.yandexPredictorService

internal class YandexPredictorImpl(private val apiKey: String) : YandexPredictor {

    private val yandexPredictorService = yandexPredictorService()

    override suspend fun complete(text: String, language: Language, limit: Int): Completion =
        yandexPredictorService.complete(text, language, limit, apiKey)
}