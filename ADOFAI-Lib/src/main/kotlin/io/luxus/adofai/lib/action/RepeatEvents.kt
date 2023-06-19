package io.luxus.adofai.lib.action

@EventType(jsonValue = "RepeatEvents", builderClass = RepeatEvents.Builder::class, single = false)
data class RepeatEvents(
    override val active: Boolean?,
    val repetitions: Long,
    val interval: Double,
    val tag: String,
) : Action(RepeatEvents::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .repetitions(repetitions)
        .interval(interval)
        .tag(tag)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var repetitions: Long = 1L
            private set
        var interval: Double = 1.0
            private set
        var tag: String = ""
            private set

        fun repetitions(repetitions: Long) = apply { this.repetitions = repetitions }
        fun interval(interval: Double) = apply { this.interval = interval }
        fun tag(tag: String) = apply { this.tag = tag }

        override fun build() = RepeatEvents(
            active,
            repetitions,
            interval,
            tag,
        )
    }
}
