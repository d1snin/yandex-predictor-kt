package dev.d1s.yapredictor.api

import dev.d1s.yapredictor.domain.Completion
import dev.d1s.yapredictor.domain.Language

public interface YandexPredictor {

    public suspend fun complete(text: String, language: Language = Language.ENGLISH, limit: Int = 1): Completion
}