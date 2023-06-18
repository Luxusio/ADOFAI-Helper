package io.luxus.adofai.lib.action

@EventType(jsonValue = "EditorComment", builderClass = EditorComment.Builder::class, single = false)
class EditorComment private constructor(
    active: Boolean?,
    val comment: String,
) : Action(EditorComment::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .comment(comment)

    class Builder : Action.Builder<Builder>() {
        override val self = this

        var comment: String = ""
            private set

        fun comment(comment: String) = apply { this.comment = comment }

        override fun build() = EditorComment(
            active,
            comment,
        )
    }
}
