package com.scottwgibson.aflfantasywrapped.templates.wrapped.captains

import com.scottwgibson.aflfantasywrapped.aflfantasy.models.insights.SeasonCaptainData
import com.scottwgibson.aflfantasywrapped.templates.wrapped.misc.PlayerRowTemplate
import io.ktor.server.html.Template
import io.ktor.server.html.insert
import kotlinx.html.HtmlBlockTag
import kotlinx.html.div
import kotlinx.html.h1

class LowestCaptainScoreTemplate(
    private val captainData: SeasonCaptainData
) : Template<HtmlBlockTag> {
    override fun HtmlBlockTag.apply() {
        div(classes = "container text-center") {
            div(classes = "row") {
                div(classes = "col text-center") {
                    h1 { +"Lowest Captain Scores" }
                }
            }

            captainData.orderedByScoreAsc().take(5).forEach {
                val score = it.finalCaptainScore()
                val selectedAs = if (it.loopholeUsed()) "VC" else "C"
                val round = it.round
                val bracketData = "R$round $selectedAs"
                insert(PlayerRowTemplate(it.finalCaptain())) {
                    column1 { +"$score" }
                    column4 { +bracketData }
                }
            }
        }
    }
}
