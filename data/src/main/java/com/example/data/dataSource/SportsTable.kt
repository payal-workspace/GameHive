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
                    SportsCategoryLists( R.drawable.adventure_sports,"Mountaineering"),
                    SportsCategoryLists( R.drawable.adventure_sports,"Trekking"),
                    SportsCategoryLists( R.drawable.adventure_sports,"Rock Climbing"),
                    SportsCategoryLists( R.drawable.adventure_sports,"Hot Air Ballooning"),
                    SportsCategoryLists( R.drawable.adventure_sports,"Bunjee Jumping"),
                    SportsCategoryLists( R.drawable.adventure_sports,"Paraglinding"),
                    SportsCategoryLists( R.drawable.adventure_sports,"Sky Diving"),
                ),
            ),
            SportsCategoryData(
                "Water Sports", R.drawable.adventure_sports,

                sports_type_items = listOf(
                    SportsCategoryLists( R.drawable.adventure_sports,"Diving"),
                    SportsCategoryLists( R.drawable.adventure_sports,"Swimming"),
                    SportsCategoryLists( R.drawable.adventure_sports,"Surfing"),
                    SportsCategoryLists( R.drawable.adventure_sports,"Jet Skiing"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Kayaking"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Kayaking"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Rafting"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Yatching"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Canoe Polo"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Fishing"),
                ),
            ),
            SportsCategoryData(
                "Team Sports", R.drawable.adventure_sports,

                sports_type_items = listOf(
                    SportsCategoryLists(R.drawable.adventure_sports,"Diving"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Swimming"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Surfing"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Jet Skiing"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Kayaking"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Kayaking"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Rafting"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Yatching"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Canoe Polo"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Fishing"),
                ),
            ),
            SportsCategoryData(
                "Individual Sports", R.drawable.adventure_sports,

                sports_type_items = listOf(
                    SportsCategoryLists(R.drawable.adventure_sports,"Alpine & Cross Country Skiing"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Archery"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Arm Wrestling"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Badminton"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Boxing"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Body Building"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Bowling"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Bowls"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Chess"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Darts"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Golf"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Judo"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Skating"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Wrestling"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Weight Lifting"),
                ),
            ),
            SportsCategoryData(
                "Action", R.drawable.adventure_sports,

                sports_type_items = listOf(
                    SportsCategoryLists(R.drawable.adventure_sports,"Description 3"),
                    SportsCategoryLists(R.drawable.adventure_sports,"Description 4")
                ),
            )
        )
    )

    suspend fun getGameListsData(): SportsCategoryEntity {
        return sportsListsData
    }
}