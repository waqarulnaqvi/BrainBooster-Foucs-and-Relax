package com.mysteriouscoder.ultimatebrainbooster.ui.screens.faq

class FAQEvent(
    val question:String,
    val answer:String
)
{
    fun doesMatchSearchQuery(query:String):Boolean
    {
        val matchingCombination= listOf(
            "$question",
            "${question.first()}"
        )
        return matchingCombination.any { it.contains(query,true) }
    }
}