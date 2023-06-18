package io.luxus.adofai.lib

import io.luxus.adofai.lib.action.Action
import io.luxus.adofai.lib.action.isSingleOnly

interface ActionMap {
    operator fun <T : Action> get(key: Class<T>): List<T>
    fun <T : Action> getFirstOrNull(key: Class<T>): T?
    val values: List<Action>
}

interface MutableActionMap : ActionMap {
    operator fun <T : Action> set(key: Class<T>, values: List<T>)
    fun <T : Action> add(value: T)
    fun <T : Action> remove(key: Class<T>): MutableList<T>?
    fun <T : Action> mapValues(key: Class<T>, transform: (T) -> T): MutableActionMap
}

/**
 * basically, this is a LinkedMultiValueMap for action.
 */
private class ActionMapImpl(
    val map: Map<Class<out Action>, List<Action>>,
    override val values: List<Action>,
) : ActionMap {
    @Suppress("UNCHECKED_CAST")
    override fun <T : Action> get(key: Class<T>): List<T> {
        return (map[key] ?: listOf()) as List<T>
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Action> getFirstOrNull(key: Class<T>): T? {
        return map[key]?.firstOrNull() as? T
    }
}

fun actionMapOf(actions: List<Action> = listOf()): ActionMap {
    val map = actions.groupBy { it::class.java }

    Action.SINGLE_ACTIONS.forEach { eventType ->
        map[eventType]?.takeIf { it.size > 1 }?.let {
            throwSingleActionException(eventType, it)
        }
    }


    return ActionMapImpl(map, actions)
}

private class MutableActionMapImpl(
    val map: MutableMap<Class<out Action>, Pair<MutableList<Int>, MutableList<Action>>>,
    var _values: MutableList<Action>,
) : MutableActionMap {

    var increment = 0
    var needUpdateValues = false

    override fun <T : Action> set(key: Class<T>, values: List<T>) {
        map[key] = Pair(values.map { increment++ }.toMutableList(), values.toMutableList())
        needUpdateValues = true
    }

    override fun <T : Action> add(value: T) {
        val eventType = value::class.java
        val list = map.computeIfAbsent(eventType) { Pair(mutableListOf(), mutableListOf()) }
        if (eventType.isSingleOnly() && list.first.isNotEmpty()) {
            throwSingleActionException(eventType, list.second)
        }

        list.first.add(increment++)
        list.second.add(value)
        _values.add(value)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Action> remove(key: Class<T>): MutableList<T>? {
        return map.remove(key) as? MutableList<T>
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Action> mapValues(key: Class<T>, transform: (T) -> T) = apply {
        map[key]?.let { (_, actions) ->
            actions.forEachIndexed { index, action ->
                actions[index] = transform(action as T)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Action> get(key: Class<T>): List<T> {
        return (map[key]?.second?.toList() ?: listOf()) as List<T>
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Action> getFirstOrNull(key: Class<T>): T? {
        return map[key]?.second?.firstOrNull() as? T
    }

    override val values: List<Action>
        get() {
            if (needUpdateValues) {
                needUpdateValues = false
                _values = map.values.asSequence()
                    .flatMap { it.second.mapIndexed { index, action -> Pair(it.first[index], action) } }
                    .sortedBy { it.first }
                    .map { it.second }
                    .toMutableList()
            }

            return _values.toList()
        }
}

private fun throwSingleActionException(eventType: Class<out Action>, list: List<Action>): Nothing =
    throw IllegalArgumentException("Single action can't have multiple actions (eventType=$eventType, actions=$list)")

fun mutableActionMapOf(actions: List<Action> = listOf()): MutableActionMap = MutableActionMapImpl(
    mutableMapOf(),
    mutableListOf()
).apply {
    actions.forEach { this.add(it) }
}

fun ActionMap.toActionMap(): ActionMap = actionMapOf(this.values)
fun ActionMap.toMutableActionMap(): MutableActionMap = mutableActionMapOf(this.values)
