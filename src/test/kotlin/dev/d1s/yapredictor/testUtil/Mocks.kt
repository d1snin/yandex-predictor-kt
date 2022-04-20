package dev.d1s.yapredictor.testUtil

import dev.d1s.teabag.testing.constant.VALID_STUB
import dev.d1s.yapredictor.domain.Completion

internal fun mockCompletion() = Completion(
    false,
    0,
    setOf(VALID_STUB)
)