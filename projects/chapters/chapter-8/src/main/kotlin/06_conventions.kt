class Time(hours: Int, minutes: Int) {
    private val totalMinutes = hours * 60 + minutes

    val hours: Int
        get() = totalMinutes / 60
    val minutes: Int
        get() = totalMinutes % 60


}

class TimeRange(private val start: Time, private val end: Time) : Iterable<Time> {
    operator fun contains(time: Time): Boolean {
        val timeMinutes = time.hours * 60 + time.minutes
        return start.hours * 60 + start.minutes <= timeMinutes &&
                timeMinutes <= end.hours * 60 + end.minutes
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
