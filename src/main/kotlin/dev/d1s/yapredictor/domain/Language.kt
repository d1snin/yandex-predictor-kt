package dev.d1s.yapredictor.domain

public enum class Language(internal val raw: String) {
    RUSSIAN("ru"),
    ENGLISH("en"),
    POLISH("pl"),
    UKRAINIAN("uk"),
    GERMAN("de"),
    FRENCH("fr"),
    SPANISH("es"),
    ITALIAN("it"),
    TURKISH("tr");

    public companion object {

        private val values = values()
        public fun fromString(value: String): Language? = values.firstOrNull {
            it.raw == value
        }
    }
}