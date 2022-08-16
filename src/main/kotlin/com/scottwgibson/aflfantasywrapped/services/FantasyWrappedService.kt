package com.scottwgibson.aflfantasywrapped.services

import com.scottwgibson.aflfantasywrapped.aflfantasy.models.ClassicTeamRound
import com.scottwgibson.aflfantasywrapped.aflfantasy.models.ClassicTeamSnapshot
import com.scottwgibson.aflfantasywrapped.aflfantasy.models.Player
import com.scottwgibson.aflfantasywrapped.aflfantasy.models.PlayerId
import com.scottwgibson.aflfantasywrapped.aflfantasy.models.insights.SeasonCaptainData
import com.scottwgibson.aflfantasywrapped.aflfantasy.models.insights.SeasonTradeData

data class WrappedData(
    val playerStats: Map<PlayerId, Player>,
    val rounds: Map<Int, ClassicTeamRound>,
    val snapshot: ClassicTeamSnapshot,
    val captainData: SeasonCaptainData,
    val seasonTradeData: SeasonTradeData
) {
    val userId = rounds.values.first().userId
}

object FantasyWrappedService {
    fun createWrapped(
        playerData: Map<PlayerId, Player>,
        rounds: Map<Int, ClassicTeamRound>,
        snapshot: ClassicTeamSnapshot
    ): WrappedData {
        return WrappedData(
            playerData,
            rounds,
            snapshot,
            SeasonCaptainData(playerData, rounds),
            SeasonTradeData(playerData, rounds)
        )
    }
}
