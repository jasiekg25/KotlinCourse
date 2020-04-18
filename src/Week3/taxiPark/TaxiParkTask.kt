package Week3.taxiPark

import kotlin.math.floor
import kotlin.math.roundToInt

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> {
    val tripsDrivers = trips.map { it -> it.driver }.distinct().toSet()
    return allDrivers - tripsDrivers.toSet()
}

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
    if (minTrips == 0) allPassengers
    else
        trips
            .map(Trip::passengers)
            .flatten()
            .groupBy { it }
            .filter { it.value.size >= minTrips }
            .keys


/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
    trips
        .filter { it.driver == driver }
        .map { it.passengers }
        .flatten()
        .groupBy { it }
        .mapValues { it.value.size }
        .filter { it.value > 1 }.keys


/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> {
    val (withoutD, withD) = trips.partition { (it.discount ?: 0.0) <= 0.0 }
    val withoutDiscount = withoutD.map { it.passengers }.flatten().groupBy { it }.mapValues { it.value.size }
    return withD
        .map { it.passengers }
        .flatten()
        .groupBy { it }
        .mapValues { it.value.size }
        .filter { (withoutDiscount[it.key] ?: 0) < it.value }.keys
}

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    return trips
        .map { it.duration }
        .groupBy {
            val start = it / 10 * 10
            val end = start + 9
            start..end
        }
        .mapValues { it.value.size }
        .maxBy { it.value }?.key

}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if (this.trips.isEmpty()) return false

    val totalIncome = trips.sumByDouble(Trip::cost)
    val expectedIncome = (totalIncome * 0.8)
    val driversNumberToCheck = (allDrivers.size * 0.2).toInt()

    val resultSum =
        trips
            .groupBy { it.driver }
            .mapValues { it.value.sumByDouble { it.cost } }
            .values
            .sortedDescending()
            .take(driversNumberToCheck)
            .sum()
    return resultSum >= expectedIncome
}
