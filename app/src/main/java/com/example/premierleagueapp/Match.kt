package com.example.premierleagueapp

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class Match(
    val id: Int,
    val MatchNumber: Int,
    val RoundNumber: Int,
    val DateUtc: String,
    val Location: String,
    val HomeTeam: String,
    val AwayTeam: String,
    val HomeTeamScore: Int?,
    val AwayTeamScore: Int?
) {
    fun getFormattedDate(): String {
        return try {
            val utcDateTime = LocalDateTime.parse(DateUtc, DateTimeFormatter.ISO_DATE_TIME)
            val zonedUtcDateTime = ZonedDateTime.of(utcDateTime, ZoneId.of("UTC"))
            val localDateTime = zonedUtcDateTime.withZoneSameInstant(ZoneId.systemDefault())

            val dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

            "${localDateTime.format(dateFormatter)} at ${localDateTime.format(timeFormatter)}"
        } catch (e: Exception) {
            DateUtc
        }
    }

    fun getLocalTime(): LocalDateTime {
        return try {
            val utcDateTime = LocalDateTime.parse(DateUtc, DateTimeFormatter.ISO_DATE_TIME)
            val zonedUtcDateTime = ZonedDateTime.of(utcDateTime, ZoneId.of("UTC"))
            zonedUtcDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()
        } catch (e: Exception) {
            LocalDateTime.now()
        }
    }
}