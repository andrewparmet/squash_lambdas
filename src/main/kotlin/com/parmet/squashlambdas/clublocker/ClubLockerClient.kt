package com.parmet.squashlambdas.clublocker

import com.google.common.util.concurrent.Service
import com.parmet.squashlambdas.activity.Match
import java.time.LocalDate

internal interface ClubLockerClient : Service {
    fun user(): UserResp

    fun courts(): List<CourtResp>

    fun slotsTaken(from: LocalDate, to: LocalDate): List<Slot>

    fun makeReservation(match: Match): ReservationResp

    fun directory(): List<User>
}

internal data class UserResp(
    val id: Int,
    val affiliations: List<Affiliation>,
    val email: String
)

internal data class Affiliation(
    val id: Int,
    val name: String
)

internal data class CourtResp(
    val id: Int,
    val name: String,
    val slotLengthMinutes: Int
)

internal data class Slot(
    val id: Int,
    val reservationId: Int,
    val court: Int,
    val startTime: Int,
    val endTime: Int,
    val startUtc: Int
)

internal sealed class ReservationResp {
    internal data class Success(
        val id: Int,
        val match: Match
    ) : ReservationResp()

    internal abstract class NonSuccess : ReservationResp()

    internal data class Error(
        val statusCode: Int,
        val message: String,
        val match: Match
    ) : NonSuccess()

    internal data class Failure(
        val t: Throwable,
        val match: Match
    ) : NonSuccess()
}

internal data class User(
    val id: Int,
    val name: String,
    val email: String
)