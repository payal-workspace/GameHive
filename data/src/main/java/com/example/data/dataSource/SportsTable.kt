package com.example.data.dataSource

import com.example.data.R
import com.example.data.model.SportsCategoryData
import com.example.data.model.SportsCategoryEntity
import com.example.data.model.SportsCategoryLists


class SportsTable {

    private val sportsListsData = SportsCategoryEntity(
        data = listOf(
            SportsCategoryData(
                "Adventure Sports", R.drawable.adventure_sports,

                sports_type_items = listOf(
                    SportsCategoryLists("Mountaineering"),
                    SportsCategoryLists("Trekking"),
                    SportsCategoryLists("Rock Climbing"),
                    SportsCategoryLists("Hot Air Ballooning"),
                    SportsCategoryLists("Bunjee Jumping"),
                    SportsCategoryLists("Paraglinding"),
                    SportsCategoryLists("Sky Diving"),
                ),
            ),
            SportsCategoryData(
                "Water Sports", R.drawable.adventure_sports,

                sports_type_items = listOf(
                    SportsCategoryLists("Diving"),
                    SportsCategoryLists("Swimming"),
                    SportsCategoryLists("Surfing"),
                    SportsCategoryLists("Jet Skiing"),
                    SportsCategoryLists("Kayaking"),
                    SportsCategoryLists("Kayaking"),
                    SportsCategoryLists("Rafting"),
                    SportsCategoryLists("Yatching"),
                    SportsCategoryLists("Canoe Polo"),
                    SportsCategoryLists("Fishing"),
                ),
            ),
            SportsCategoryData(
                "Team Sports", R.drawable.adventure_sports,

                sports_type_items = listOf(
                    SportsCategoryLists("Diving"),
                    SportsCategoryLists("Swimming"),
                    SportsCategoryLists("Surfing"),
                    SportsCategoryLists("Jet Skiing"),
                    SportsCategoryLists("Kayaking"),
                    SportsCategoryLists("Kayaking"),
                    SportsCategoryLists("Rafting"),
                    SportsCategoryLists("Yatching"),
                    SportsCategoryLists("Canoe Polo"),
                    SportsCategoryLists("Fishing"),
                ),
            ),
            SportsCategoryData(
                "Individual Sports", R.drawable.adventure_sports,

                sports_type_items = listOf(
                    SportsCategoryLists("Alpine & Cross Country Skiing"),
                    SportsCategoryLists("Archery"),
                    SportsCategoryLists("Arm Wrestling"),
                    SportsCategoryLists("Badminton"),
                    SportsCategoryLists("Boxing"),
                    SportsCategoryLists("Body Building"),
                    SportsCategoryLists("Bowling"),
                    SportsCategoryLists("Bowls"),
                    SportsCategoryLists("Chess"),
                    SportsCategoryLists("Darts"),
                    SportsCategoryLists("Golf"),
                    SportsCategoryLists("Judo"),
                    SportsCategoryLists("Skating"),
                    SportsCategoryLists("Wrestling"),
                    SportsCategoryLists("Weight Lifting"),
                ),
            ),
            SportsCategoryData(
                "Action", R.drawable.adventure_sports,

                sports_type_items = listOf(
                    SportsCategoryLists("Description 3"),
                    SportsCategoryLists("Description 4")
                ),
            )
        )
    )

    suspend fun getGameListsData(): SportsCategoryEntity {
        return sportsListsData
    }
}