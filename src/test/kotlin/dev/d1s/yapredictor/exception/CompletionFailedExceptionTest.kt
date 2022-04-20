package dev.d1s.yapredictor.exception

import dev.d1s.teabag.testing.constant.VALID_STUB
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class CompletionFailedExceptionTest {

    @Test
    fun `should return valid exception message`() {
        expectThat(
            CompletionFailedException(VALID_STUB).message!!
        ) isEqualTo "Completion failed: $VALID_STUB"
    }
}