package io.luxus.adofai.lib.action

@EventType(jsonValue = "Checkpoint", builderClass = Checkpoint.Builder::class, single = true)
class Checkpoint private constructor(
    active: Boolean?,
    val tileOffset: Long,
) : Action(Checkpoint::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .tileOffset(tileOffset)

    class Builder : Action.Builder<Builder>() {
        override val self = this

        var tileOffset: Long = 0L
            private set

        fun tileOffset(tileOffset: Long) = apply { this.tileOffset = tileOffset }

        override fun build() = Checkpoint(
            active,
            tileOffset,
        )
    }
}
