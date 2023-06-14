package io.luxus.adofai.lib.action

class SetConditionalEvents private constructor(
    active: Boolean?,
    val perfectTag: String,
    val hitTag: String,
    val barelyTag: String,
    val missTag: String,
    val lossTag: String,
) : Action(SetConditionalEvents::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .perfectTag(perfectTag)
        .hitTag(hitTag)
        .barelyTag(barelyTag)
        .missTag(missTag)
        .lossTag(lossTag)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var perfectTag: String = "NONE"
            private set
        var hitTag: String = "NONE"
            private set
        var barelyTag: String = "NONE"
            private set
        var missTag: String = "NONE"
            private set
        var lossTag: String = "NONE"
            private set

        fun perfectTag(perfectTag: String) = apply { this.perfectTag = perfectTag }
        fun hitTag(hitTag: String) = apply { this.hitTag = hitTag }
        fun barelyTag(barelyTag: String) = apply { this.barelyTag = barelyTag }
        fun missTag(missTag: String) = apply { this.missTag = missTag }
        fun lossTag(lossTag: String) = apply { this.lossTag = lossTag }

        override fun build() = SetConditionalEvents(
            active,
            perfectTag,
            hitTag,
            barelyTag,
            missTag,
            lossTag,
        )
    }
}
