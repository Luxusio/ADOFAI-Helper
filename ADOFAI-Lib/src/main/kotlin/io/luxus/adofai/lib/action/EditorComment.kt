package io.luxus.adofai.lib.action

@EventType(jsonValue = "EditorComment", builderClass = EditorComment.Builder::class, single = false)
data class EditorComment(
    override val active: Boolean?,
    val comment: String,
) : Action(EditorComment::class.java, active) {

    override fun toBuilder() = Builder()
        .active(active)
        .comment(comment)

    class Builder : Action.Builder<Builder>() {
        override val self = this

        var comment: String = """
            Write your own comment here!

            Multi-lines and <color=#00FF00>colored</color> texts are also supported.
        """.trimIndent()
            private set

        fun comment(comment: String) = apply { this.comment = comment }

        override fun build() = EditorComment(
            active,
            comment,
        )
    }
}
