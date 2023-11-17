
package pro.tokamak.mobile.android.tokamakai.model

import androidx.compose.runtime.*

/*
 * The type of values the tracker will track over time.
 */
enum class TrackerType {
    BOOLEAN,
    RANGE,
    COUNT
}

/*
 * The settings for a particular tracker.
 *
 * Note that all properties, including type, are user-configurable.
 */
@Stable
class Tracker(
    emoji: String,
    name: String,
    type: TrackerType,
    units: String = "",
    minValue: Int = 0,
    maxValue: Int = 5,
    step: Int = 1,
) {
    var emoji by mutableStateOf(emoji)
    var name by mutableStateOf(name)
    var type by mutableStateOf(type)
    var units by mutableStateOf(units)
    var minValue by mutableStateOf(minValue)
    var maxValue by mutableStateOf(maxValue)
    var step by mutableStateOf(step)

    fun copy(): Tracker = Tracker(
        emoji,
        name,
        type,
        units,
        minValue,
        maxValue,
        step
    )

    fun configureAs(other: Tracker) {
        emoji = other.emoji
        name = other.name
        type = other.type
        units = other.units
        minValue = other.minValue
        maxValue = other.maxValue
        step = other.step
    }

    override fun toString(): String {
        return "{ type: $type, name: $name, emoji: $emoji }"
    }
}
