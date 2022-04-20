package dev.d1s.yapredictor.service.impl

import com.github.kittinunf.fuel.Fuel
import dev.d1s.yapredictor.constant.BASE_URL
import dev.d1s.yapredictor.domain.Completion
import dev.d1s.yapredictor.domain.Language
import dev.d1s.yapredictor.exception.CompletionFailedException
import dev.d1s.yapredictor.factory.gson
import dev.d1s.yapredictor.service.YandexPredictorService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class YandexPredictorServiceImpl : YandexPredictorService {

    private val gson = gson()

    override suspend fun complete(text: String, language: Language, limit: Int, key: String): Completion =
        withContext(Dispatchers.IO) {
            val (_, _, result) = Fuel.get(
                BASE_URL,
                listOf(
                    "key" to key,
                    "lang" to language.raw,
                    "q" to text,
                    "limit" to limit
                )
            ).response()

            val (bytes, error) = result

            error?.let {
                throw CompletionFailedException(it.message!!)
            }

            // convert the result manually,
            // because Fuel is ABSOLUTELY NOT TESTED in this regard,
            // I have NO IDEA how to test this shit.
            gson.fromJson(
                String(bytes!!),
                Completion::class.java
            )
        }
}