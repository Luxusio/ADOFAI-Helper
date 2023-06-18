package io.luxus.adofai.lib.action

@EventType(jsonValue = "SetConditionalEvents", builderClass = SetConditionalEvents.Builder::class, single = true)
class SetConditionalEvents private constructor(
    active: Boolean?,
    val perfectTag: String,
    val hitTag: String,
    val earlyPerfectTag: String,
    val latePerfectTag: String,
    val barelyTag: String,
    val veryEarlyTag: String,
    val veryLateTag: String,
    val missTag: String,
    val tooEarlyTag: String,
    val tooLateTag: String,
    val lossTag: String,
) : Action(SetConditionalEvents::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .perfectTag(perfectTag)
        .hitTag(hitTag)
        .earlyPerfectTag(earlyPerfectTag)
        .latePerfectTag(latePerfectTag)
        .barelyTag(barelyTag)
        .veryEarlyTag(veryEarlyTag)
        .veryLateTag(veryLateTag)
        .missTag(missTag)
        .tooEarlyTag(tooEarlyTag)
        .tooLateTag(tooLateTag)
        .lossTag(lossTag)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var perfectTag: String = "NONE"
            private set
        var hitTag: String = "NONE"
            private set
        var earlyPerfectTag: String = "NONE"
            private set
        var latePerfectTag: String = "NONE"
            private set
        var barelyTag: String = "NONE"
            private set
        var veryEarlyTag: String = "NONE"
            private set
        var veryLateTag: String = "NONE"
            private set
        var missTag: String = "NONE"
            private set
        var tooEarlyTag: String = "NONE"
            private set
        var tooLateTag: String = "NONE"
            private set
        var lossTag: String = "NONE"
            private set

        fun perfectTag(perfectTag: String) = apply { this.perfectTag = perfectTag }
        fun hitTag(hitTag: String) = apply { this.hitTag = hitTag }
        fun earlyPerfectTag(earlyPerfectTag: String) = apply { this.earlyPerfectTag = earlyPerfectTag }
        fun latePerfectTag(latePerfectTag: String) = apply { this.latePerfectTag = latePerfectTag }
        fun barelyTag(barelyTag: String) = apply { this.barelyTag = barelyTag }
        fun veryEarlyTag(veryEarlyTag: String) = apply { this.veryEarlyTag = veryEarlyTag }
        fun veryLateTag(veryLateTag: String) = apply { this.veryLateTag = veryLateTag }
        fun missTag(missTag: String) = apply { this.missTag = missTag }
        fun tooEarlyTag(tooEarlyTag: String) = apply { this.tooEarlyTag = tooEarlyTag }
        fun tooLateTag(tooLateTag: String) = apply { this.tooLateTag = tooLateTag }
        fun lossTag(lossTag: String) = apply { this.lossTag = lossTag }

        override fun build() = SetConditionalEvents(
            active,
            perfectTag,
            hitTag,
            earlyPerfectTag,
            latePerfectTag,
            barelyTag,
            veryEarlyTag,
            veryLateTag,
            missTag,
            tooEarlyTag,
            tooLateTag,
            lossTag,
        )
    }
}
