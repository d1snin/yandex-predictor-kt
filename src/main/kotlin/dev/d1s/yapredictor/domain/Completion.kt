package dev.d1s.yapredictor.domain

import com.google.gson.annotations.SerializedName

public data class Completion(

    @field:SerializedName("endOfWord")
    val endOfText: Boolean,

    @field:SerializedName("pos")
    val position: Int,

    @field:SerializedName("text")
    val completions: Set<String>
) {
    public fun firstCompletion(): String = completions.first()
}