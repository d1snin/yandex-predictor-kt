package dev.d1s.yapredictor.domain

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class LanguageTest {

    @Test
    fun `should return valid Language`() {
        expectThat(Language.fromString("en")) isEqualTo
                Language.ENGLISH
    }
}