package io.luxus.adofai.lib.action

@EventType(jsonValue = "Bookmark", builderClass = Bookmark.Builder::class, single = true)
data class Bookmark(
    override val active: Boolean?
) : Action(Bookmark::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        override fun build() = Bookmark(active)
    }
}
