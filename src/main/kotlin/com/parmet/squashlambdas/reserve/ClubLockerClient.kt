package com.parmet.squashlambdas.reserve

import com.google.common.util.concurrent.Service
import com.parmet.squashlambdas.activity.Match
import java.time.LocalDate

internal interface ClubLockerClient : Service {
    fun user(): UserResp

    fun courts(): List<CourtResp>

    fun slotsTaken(from: LocalDate, to: LocalDate): List<TakenSlot>

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

internal data class TakenSlot(
    val id: Int,
    val reservationId: Int,
    val court: Int,
    val startTime: Int,
    val endTime: Int,
    val startUtc: Int
)

internal sealed class ReservationResp {
    internal data class Success(
        val id: Int
    ) : ReservationResp()

    internal data class Error(
        val statusCode: Int,
        val message: String,
        val match: Match
    ) : ReservationResp()
}

internal data class User(
    val id: Int,
    val name: String,
    val email: String
)