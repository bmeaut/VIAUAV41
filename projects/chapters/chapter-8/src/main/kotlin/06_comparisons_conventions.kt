class Time(hours: Int, minutes: Int) : Comparable<Time> {
    private val totalMinutes = hours * 60 + minutes

    val hours: Int
        get() = totalMinutes / 60
    val minutes: Int
        get() = totalMinutes % 60

    override fun equals(other: Any?): Boolean {
        if (other !is Time) return false
        return totalMinutes == other.totalMinutes
    }

    override fun hashCode(): Int {
        return totalMinutes
    }

    override operator fun compareTo(other: Time): Int {
        return totalMinutes - other.totalMinutes
    }
}

class TimeRange(private val start: Time, private val end: Time) : Iterable<Time> {
    operator fun contains(time: Time): Boolean {
        return start <= time && time <= end
    }

    override operator fun iterator(): Iterator<Time> {
        return object : Iterator<Time> {
            val startMinutes = start.hours * 60 + start.minutes
            val endMinutes = end.hours * 60 + end.minutes

            var currentMinutes = startMinutes

            override fun hasNext(): Boolean {
                return currentMinutes <= endMinutes
            }

            override fun next(): Time {
                return Time(hours = currentMinutes / 60, minutes = currentMinutes % 60).also {
                    currentMinutes++
                }
            }
        }
    }
}

operator fun Time.rangeTo(other: Time): TimeRange {
    return TimeRange(this, other)
}

operator fun Time.component1(): Int = hours
operator fun Time.component2(): Int = minutes

fun main() {
    val sixAm = Time(6, 0)
    val sixOClock = Time(6, 0)
    println(sixAm == sixOClock) // true
    println(sixAm != sixOClock) // false

    println(Time(5, 0) < Time(6, 0)) // true
    println(Time(5, 0) >= Time(10, 25)) // false

    val morning = Time(8, 0)
    val evening = Time(17, 0)

    val range: TimeRange = morning..evening

    val lunch = Time(12, 30)
    println(lunch in range) // true
    println(lunch !in range) // false

    for ((hours, minutes) in morning..lunch) {
        println(String.format("%02d:%02d", hours, minutes))
    }

    val quarters = (morning..lunch).filter { it.minutes % 15 == 0 }
    for (time in quarters) {
        println(String.format("%02d:%02d", time.hours, time.minutes))
    }
}
