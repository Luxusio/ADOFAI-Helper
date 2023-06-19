package io.luxus.adofai.lib.action

@EventType(jsonValue = "SetText", builderClass = SetText.Builder::class, single = false)
data class SetText(
    override val active: Boolean?,
    val decText: String,
    val tag: String,
    val angleOffset: Double,
    val eventTag: String,
) : Action(SetText::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .decText(decText)
        .tag(tag)
        .angleOffset(angleOffset)
        .eventTag(eventTag)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var decText: String = "Text"
            private set
        var tag: String = ""
            private set
        var angleOffset: Double = 0.0
            private set
        var eventTag: String = ""
            private set

        fun decText(decText: String) = apply { this.decText = decText }
        fun tag(tag: String) = apply { this.tag = tag }
        fun angleOffset(angleOffset: Double) = apply { this.angleOffset = angleOffset }
        fun eventTag(eventTag: String) = apply { this.eventTag = eventTag }

        override fun build() = SetText(
            active,
            decText,
            tag,
            angleOffset,
            eventTag,
        )
    }
}
