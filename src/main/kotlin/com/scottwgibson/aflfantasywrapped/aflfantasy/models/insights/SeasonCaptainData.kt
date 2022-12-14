package com.scottwgibson.aflfantasywrapped.aflfantasy.models.insights

import com.scottwgibson.aflfantasywrapped.aflfantasy.models.ClassicTeamRound
import com.scottwgibson.aflfantasywrapped.aflfantasy.models.Player
import com.scottwgibson.aflfantasywrapped.aflfantasy.models.PlayerId

class SeasonCaptainData(
    playerData: Map<PlayerId, Player>,
    rounds: Map<Int, ClassicTeamRound>,
    private val calvinsCaptainsData: CalvinsCaptainsData
) {
    val roundCaptains = rounds
        .mapValues { it.value.lineup.captain to it.value.lineup.viceCaptain }
        .mapValues { RoundCaptains(it.key, playerData[it.value.first], playerData[it.value.second]) }

    fun timesLoopholeUsed() = roundCaptains.count { it.value.loopholeUsed() }

    fun averageCaptainScore() = roundCaptains
        .map { it.value.finalCaptainScore() }
        .average()

    fun orderedByScoreDesc() = roundCaptains.values.sortedByDescending { it.finalCaptainScore() }

    fun orderedByScoreAsc() = roundCaptains.values.sortedBy { it.finalCaptainScore() }

    fun orderedByUsedDesc() = roundCaptains.values.groupBy { it.finalCaptain() }
        .toList()
        .sortedByDescending { it.second.count() }

    fun orderedByLoopholeDesc() = roundCaptains.values.asSequence().filter { it.loopholeUsed() }
        .map { it.captain }
        .filterNotNull()
        .groupBy { it }
        .toList()
        .sortedByDescending { it.second.count() }

    fun timesUsingCalvinsTop3() = roundCaptains.count { roundCaptains ->
        roundCaptains.value.finalCaptain()?.let { player ->
            calvinsCaptainsData.rounds[roundCaptains.key.toString()]?.contains(player.id)
        } ?: false
    }
}
