package dev.d1s.yapredictor.service

import dev.d1s.yapredictor.domain.Completion
import dev.d1s.yapredictor.domain.Language

internal interface YandexPredictorService {

    suspend fun complete(text: String, language: Language, limit: Int, key: String): Completion
}