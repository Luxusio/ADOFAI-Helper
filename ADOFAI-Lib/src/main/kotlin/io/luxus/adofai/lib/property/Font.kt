package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class Font(override val jsonValue: String) : JsonParseable {
    DEFAULT("Default"),
    ARIAL("Arial"),
    COMIC_SANS_MS("ComicSansMS"),
    COURIER_NEW("CourierNew"),
    GEORGIA("Georgia"),
    IMPACT("Impact"),
    TIMES_NEW_ROMAN("TimesNewRoman"),
}
