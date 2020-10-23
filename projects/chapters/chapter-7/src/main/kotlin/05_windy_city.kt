import java.io.File
import java.time.LocalDateTime

class Crime(data: List<String>) {
    val id: String = data[0]
    val caseNumber: String = data[1]
    val date: LocalDateTime = data[2].toLocalDateTime()
    val block: String = data[3]
    val type: String = data[5]
    val description: String = data[6]
    val locationDescription: String = data[7]
    val arrest: Boolean = data[8].toBoolean()
    val domestic: Boolean = data[9].toBoolean()
    val latitude: Double? = data[16].toDoubleOrNull()
    val longitude: Double? = data[17].toDoubleOrNull()
}

//private fun String.toLocalDateTime(): LocalDateTime {
//    val (year, month, day) = this.substringBefore('T').split('-').map(String::toInt)
//    val (hour, minute, second) = this.substringAfter('T').split(':').map(String::toInt)
//
//    return LocalDateTime.of(year, month, day, hour, minute, second)
//}

private inline fun String.toLocalDateTime() = LocalDateTime.parse(this)

fun main() {
    // The files in this repository are just the first few rows of each year's stats
    // Go to the following URL for the full data files:
    // https://data.world/publicsafety/chicago-crime

    val crimes = listOf("2014", "2015", "2016")
        .map { year -> "data/chicago_crime_$year.csv" }
        .map { name -> File(name) }
        .flatMap { file ->
            file.readLines().drop(1)
        }
        .map { Crime(it.split(",")) }

    println("# of crimes: ${crimes.size}") // # of crimes: 754541

    println()

    val ratio: Double = crimes.count { it.arrest }.toDouble() / crimes.size
    println("Arrest percentage: ${ratio * 100}%") // Arrest percentage: 24.438565962618334%

    println()

    val result = crimes
        .filter { it.arrest }
        .filter { it.date.hour in 1..5 }
        .filter { it.locationDescription.contains("cta", ignoreCase = true) }
        .filter { it.description.contains("cannabis", ignoreCase = true) }
        .count()
    println("CTA cannabis arrests at night $result")

    println()

    println("Top 3 types of crimes")
    crimes.groupingBy { it.type }
        .eachCount()
        .mapKeys { it.key.toLowerCase().capitalize() } // 1
        .toList() // 2
        .sortedByDescending { (type, count) -> count } // 3
        .take(3) // 4
        .forEach { (type, count) ->
            // 5
            println("$type: $count")
        }

    println()

    crimes.groupingBy { it.date.hour }
        .eachCount()
        .toSortedMap()
        .forEach { (hour, count) ->
            val hourString = String.format("%2dh", hour)
            val percentage = (count.toDouble() / crimes.size * 100).toInt()
            println("$hourString ${"X".repeat(percentage)}")
        }
}
