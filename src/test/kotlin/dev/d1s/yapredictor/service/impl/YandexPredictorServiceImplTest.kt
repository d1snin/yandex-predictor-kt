package dev.d1s.yapredictor.service.impl

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.google.gson.Gson
import dev.d1s.teabag.testing.assertThrowsBlocking
import dev.d1s.teabag.testing.constant.VALID_STUB
import dev.d1s.yapredictor.constant.BASE_URL
import dev.d1s.yapredictor.domain.Completion
import dev.d1s.yapredictor.domain.Language
import dev.d1s.yapredictor.exception.CompletionFailedException
import dev.d1s.yapredictor.factory.gson
import dev.d1s.yapredictor.testUtil.mockCompletion
import io.mockk.*
import kotlinx.coroutines.runBlocking
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class YandexPredictorServiceImplTest {

    private val mockResult =
        mockk<com.github.kittinunf.result.Result<ByteArray, FuelError>>()

    private val mockError = mockk<FuelError> {
        every {
            message
        } returns VALID_STUB
    }

    private val completion = mockCompletion()

    private val mockGson = mockk<Gson>()

    @Test
    fun `should complete the sentence`() {
        this.withStaticMocks {
            expectThat(
                it.complete(VALID_STUB, Language.ENGLISH, 1, VALID_STUB)
            ) isEqualTo completion

            verifyAll {
                mockResult.component1() // bytes
                mockResult.component2() // error
                Fuel.get(
                    BASE_URL,
                    listOf(
                        "key" to VALID_STUB,
                        "lang" to "en",
                        "q" to VALID_STUB,
                        "limit" to 1
                    )
                )
                mockGson.fromJson(any<String>(), Completion::class.java)
            }
        }
    }

    @Test
    fun `should throw CompletionFailedException`() {
        this.withStaticMocks {
            every {
                mockResult.component2() // error
            } returns mockError

            assertThrowsBlocking<CompletionFailedException> {
                it.complete(VALID_STUB, Language.ENGLISH, 1, VALID_STUB)
            }

            verifyAll {
                mockResult.component1() // bytes
                mockResult.component2() // error
                Fuel.get(
                    BASE_URL,
                    listOf(
                        "key" to VALID_STUB,
                        "lang" to "en",
                        "q" to VALID_STUB,
                        "limit" to 1
                    )
                )
            }
        }
    }

    private inline fun withStaticMocks(crossinline block: suspend (YandexPredictorServiceImpl) -> Unit) {
        mockkObject(Fuel) {
            every {
                Fuel.get(
                    BASE_URL,
                    listOf(
                        "key" to VALID_STUB,
                        "lang" to "en",
                        "q" to VALID_STUB,
                        "limit" to 1
                    )
                ).response().component3() // result
            } returns mockResult

            every {
                mockResult.component1() // bytes
            } returns byteArrayOf()

            every {
                mockResult.component2() // error
            } returns null

            mockkStatic("dev.d1s.yapredictor.factory.InternalComponentsKt") {
                every {
                    gson()
                } returns mockGson

                every {
                    mockGson.fromJson(any<String>(), Completion::class.java)
                } returns completion

                runBlocking {
                    block(YandexPredictorServiceImpl())
                }
            }
        }
    }
}