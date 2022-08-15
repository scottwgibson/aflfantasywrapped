package com.scottwgibson.aflfantasywrapped.templates

import com.scottwgibson.aflfantasywrapped.services.WrappedData
import com.scottwgibson.aflfantasywrapped.templates.wrapped.WelcomeCarouselItem
import com.scottwgibson.aflfantasywrapped.templates.wrapped.captains.HighestCaptainsScoreTemplate
import com.scottwgibson.aflfantasywrapped.templates.wrapped.captains.MostPopularCaptainCarouselItem
import com.scottwgibson.aflfantasywrapped.templates.wrapped.captains.MostUsedLoopholeTemplate
import com.scottwgibson.aflfantasywrapped.templates.wrapped.captains.TopCaptainSelectionTemplate
import com.scottwgibson.aflfantasywrapped.templates.wrapped.misc.CarouselItem
import com.scottwgibson.aflfantasywrapped.templates.wrapped.rank.RankCarouselSection
import com.scottwgibson.aflfantasywrapped.templates.wrapped.squad.SquadCarouselSection
import com.scottwgibson.aflfantasywrapped.templates.wrapped.squad.StartingSquadTemplate
import io.ktor.server.html.Template
import io.ktor.server.html.insert
import kotlinx.html.ButtonType
import kotlinx.html.HTML
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.id
import kotlinx.html.span

class FantasyWrappedTemplate(
    val wrappedData: WrappedData
) : Template<HTML> {
    override fun HTML.apply() {
        insert(MainTemplate()) {
            body {
                div(classes = "container-fluid") {
                    attributes["max-width"] = "600px"
                    div(classes = "row") {
                        div(classes = "col min-vh-100") {
                            div(classes = "carousel slide") {
                                id = "wrappedCarousel"
                                attributes["data-bs-ride"] = "wrappedCarousel"
                                attributes["data-bs-interval"] = "false"
                                attributes["data-bs-wrap"] = "false"

                                div(classes = "carousel-inner") {
                                    attributes["style"] = "padding-left: 8%; padding-right: 8%;"
                                    insert(CarouselItem(WelcomeCarouselItem(wrappedData), true)) {}
                                    insert(RankCarouselSection(wrappedData)) {}
                                    insert(SquadCarouselSection(wrappedData)) {}
                                    insert(CarouselItem(MostPopularCaptainCarouselItem(wrappedData))) {}
                                    insert(CarouselItem(TopCaptainSelectionTemplate(wrappedData.captainData))) {}
                                    insert(CarouselItem(HighestCaptainsScoreTemplate(wrappedData.captainData))) {}
                                    insert(CarouselItem(StartingSquadTemplate(wrappedData))) {}
                                    insert(CarouselItem(MostUsedLoopholeTemplate(wrappedData.captainData))) {}
                                }
                                button(classes = "carousel-control-prev") {
                                    type = ButtonType.button
                                    attributes["data-bs-target"] = "#wrappedCarousel"
                                    attributes["data-bs-slide"] = "prev"
                                    span("carousel-control-prev-icon") {
                                        attributes["aria-hidden"] = "true"
                                    }
                                    span("visually-hidden") { +"Previous" }
                                }
                                button(classes = "carousel-control-next") {
                                    type = ButtonType.button
                                    attributes["data-bs-target"] = "#wrappedCarousel"
                                    attributes["data-bs-slide"] = "next"
                                    span("carousel-control-next-icon") {
                                        attributes["aria-hidden"] = "true"
                                    }
                                    span("visually-hidden") { +"Next" }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
