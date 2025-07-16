package com.example.premierleagueapp.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

data class Match(
    val id: Int,
    @SerializedName("MatchNumber")
    val matchNumber: Int,
    @SerializedName("RoundNumber")
    val roundNumber: Int,
    @SerializedName("DateUtc")
    val dateUtc: String,
    @SerializedName("Location")
    val location: String,
    @SerializedName("HomeTeam")
    val homeTeam: String,
    @SerializedName("AwayTeam")
    val awayTeam: String,
    @SerializedName("HomeTeamScore")
    val homeTeamScore: Int?,
    @SerializedName("AwayTeamScore")
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
    fun getLocalTime(): String {
        return try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss'Z'")
            val utcDateTime = LocalDateTime.parse(dateUtc, formatter)
            val zonedUtc = ZonedDateTime.of(utcDateTime, ZoneId.of("UTC"))
            val localTime = zonedUtc.withZoneSameInstant(ZoneId.systemDefault())
            DateTimeFormatter
                .ofLocalizedTime(FormatStyle.SHORT)
                .format(localTime)
        } catch (e: Exception) {
            "Time error"
        }
    }
}