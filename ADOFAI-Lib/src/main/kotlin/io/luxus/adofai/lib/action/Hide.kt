package io.luxus.adofai.lib.action

import io.luxus.adofai.lib.property.Toggle

class Hide private constructor(
    active: Boolean?,
    val hideJudgment: Toggle,
    val hideTileIcon: Toggle,
) : Action(Hide::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .hideJudgment(hideJudgment)
        .hideTileIcon(hideTileIcon)

    class Builder : Action.Builder<Builder>() {
        override val self = this
        var hideJudgment: Toggle = Toggle.DISABLED
            private set
        var hideTileIcon: Toggle = Toggle.DISABLED
            private set

        fun hideJudgment(hideJudgment: Toggle) = apply { this.hideJudgment = hideJudgment }
        fun hideTileIcon(hideTileIcon: Toggle) = apply { this.hideTileIcon = hideTileIcon }

        override fun build() = Hide(
            active,
            hideJudgment,
            hideTileIcon,
        )
    }
}
