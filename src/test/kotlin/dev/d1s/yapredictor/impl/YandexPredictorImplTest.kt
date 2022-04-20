package dev.d1s.yapredictor.impl

import dev.d1s.teabag.testing.constant.VALID_STUB
import dev.d1s.yapredictor.domain.Language
import dev.d1s.yapredictor.factory.yandexPredictorService
import dev.d1s.yapredictor.service.YandexPredictorService
import dev.d1s.yapredictor.testUtil.mockCompletion
import io.mockk.*
import kotlinx.coroutines.runBlocking
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class YandexPredictorImplTest {

    private val yandexPredictorService = mockk<YandexPredictorService> {
        coEvery {
            complete(VALID_STUB, Language.ENGLISH, 1, VALID_STUB)
        } returns mockCompletion()
    }

    private val completion = mockCompletion()

    @Test
    fun `should complete the sentence`() {
        this.withStaticMocks {
            expectThat(
                runBlocking {
                    it.complete(VALID_STUB)
                }
            ) isEqualTo completion

            coVerify {
                yandexPredictorService.complete(
                    VALID_STUB,
                    Language.ENGLISH,
                    1,
                    VALID_STUB
                )
            }
        }
    }

    private inline fun withStaticMocks(block: (YandexPredictorImpl) -> Unit) {
        mockkStatic("dev.d1s.yapredictor.factory.InternalComponentsKt") {
            every {
                yandexPredictorService()
            } returns yandexPredictorService

            block(YandexPredictorImpl(VALID_STUB))
        }
    }
}