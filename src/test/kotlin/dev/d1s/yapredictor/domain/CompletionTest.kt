package dev.d1s.yapredictor.domain

import dev.d1s.teabag.testing.constant.VALID_STUB
import dev.d1s.yapredictor.testUtil.mockCompletion
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class CompletionTest {

    private val completion = mockCompletion()

    @Test
    fun `should return first completion`() {
        expectThat(completion.firstCompletion()) isEqualTo VALID_STUB
    }
}