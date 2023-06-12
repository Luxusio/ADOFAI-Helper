package io.luxus.adofai.lib.property

import io.luxus.adofai.lib.json.JsonParseable

enum class SpecialArtistType(override val jsonValue: String) : JsonParseable {
    NONE("None"),
    AUTHOR_IS_ARTIST("AuthorIsArtist"),
    PUBLIC_LICENSE("PublicLicense"),
}
