[![](https://jitpack.io/v/d1snin/yandex-predictor-kt.svg)](https://jitpack.io/#d1snin/yandex-predictor-kt)

# yandex-predictor-kt

Asynchronous Yandex.Predictor API wrapper for Kotlin/JVM.

### Installation

```kotlin
repositories {
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("dev.d1s:yandex-predictor-kt:$yandexPredictorVersion")
}
```

### Example usage

```kotlin
const val API_KEY = "api key"

val predictor = yandexPredictor(API_KEY)

fun main() {
    println(
        runBlocking {
            predictor.complete("Kotlin programming language is a great ")
                .firstCompletion()
        }
    )

    // "deal"
}
```