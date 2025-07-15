package com.example.premierleagueapp.data.model

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class Match(
    val id: Int,
    val matchNumber: Int,
    val roundNumber: Int,
    val dateUtc: String,
    val location: String,
    val homeTeam: String,
    val awayTeam: String,
    val homeTeamScore: Int?,
    val awayTeamScore: Int?
) {
    fun getFormattedDate(): String {
        return try {
            val utcDateTime = LocalDateTime.parse(dateUtc, DateTimeFormatter.ISO_DATE_TIME)
            val zonedUtcDateTime = ZonedDateTime.of(utcDateTime, ZoneId.of("UTC"))
            val localDateTime = zonedUtcDateTime.withZoneSameInstant(ZoneId.systemDefault())

            val dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

            "${localDateTime.format(dateFormatter)} at ${localDateTime.format(timeFormatter)}"
        } catch (e: Exception) {
            dateUtc
        }
    }
}