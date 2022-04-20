package dev.d1s.yapredictor.constant

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class YandexPredictorConstantsTest {

    @Test
    fun `should return valid base url`() {
        expectThat(BASE_URL) isEqualTo
                "https://predictor.yandex.net/api/v1/predict.json/complete"
    }
}