package com.example.data.dataSource

import com.example.data.R
import com.example.data.model.SportsCategoryData
import com.example.data.model.SportsCategoryEntity
import com.example.data.model.SportsCategoryLists


sealed class SportsCategory(val name: String, val iconResId: Int) {
    object Adventure : SportsCategory("Adventure Sports", R.drawable.adventure_sport)
    object Water : SportsCategory("Water Sports", R.drawable.water_sport)
    object Team : SportsCategory("Team Sports", R.drawable.team_sport)
    object Individual : SportsCategory("Individual Sports", R.drawable.individual_sport)
    object Winter : SportsCategory("Winter Sports", R.drawable.winter_sport)
    object Racing : SportsCategory("Racing Sports", R.drawable.racing_sport)
}

class SportsTable {
    private val sportsListsData = SportsCategoryEntity(
        data = listOf(
            createCategory(
                SportsCategory.Adventure,
                listOf(
                    "Mountaineering", "Trekking", "Rock Climbing", "Hot Air Ballooning",
                    "Bungee Jumping", "Paragliding", "Sky Diving", "Cave Exploration",
                    "Zip Lining", "Hang Gliding", "Snowboarding", "Mountain Biking",
                    "Kite Surfing", "Sky Surfing", "Canoeing", "Wingsuit", "Cliff Diving",
                    "Trail Running", "Flyboarding"
                )
            ),
            createCategory(
                SportsCategory.Water,
                listOf(
                    "Diving", "Swimming", "Surfing", "Scuba Diving", "Jet Skiing",
                    "Kayaking", "Rafting", "Yachting", "Canoe Polo", "Fishing",
                    "Water Polo", "Rowing", "Skim Boarding", "Free Diving",
                    "Wake Boarding", "Kneeboarding", "Sailing", "Dragon Boat Racing"
                )
            ),
            createCategory(
                SportsCategory.Team,
                listOf(
                    "Basketball", "Volleyball", "Cricket", "Hockey", "Lacrosse",
                    "Football", "Baseball", "Badminton", "Water Polo", "Snow Volleyball",
                    "Tug of War", "Handball"
                )
            ),
            createCategory(
                SportsCategory.Individual,
                listOf(
                    "Chess", "Darts", "Golf", "Judo", "Skating", "Jet Ski",
                    "Kayaking", "Taekwondo", "Tennis", "Swimming", "Snooker",
                    "Bodybuilding", "Motorcycling", "Weightlifting", "Disc Golf", "Archery"
                )
            ),
            createCategory(
                SportsCategory.Winter,
                listOf(
                    "Ice Skating", "Skiing", "Snowbiking", "Ice Racing", "Snow Golf",
                    "Snow Polo", "Ice Fishing", "Monoskiing", "Speed Skiing",
                    "Sneak Boat Hunting", "Ice Yachting", "Winter Swimming"
                )
            ),
            createCategory(
                SportsCategory.Racing,
                listOf(
                    "Speed Climbing", "Paralympic Swimming", "Marathon Swimming", "Sandboarding",
                    "Track Cycling", "Skiing", "Skating", "Camel Racing", "Drone Racing",
                    "Open Wheel Racing", "Motorcycle Racing", "Slot Car Racing", "Rowing",
                    "Yacht Racing", "Mountain Biking"
                )
            )
        )
    )

    suspend fun getGameListsData(): SportsCategoryEntity {
        return sportsListsData
    }

    private fun createCategory(category: SportsCategory, items: List<String>): SportsCategoryData {
        return SportsCategoryData(
            sportsCategoryTitle = category.name,
            sportsCategoryImageUrl = category.iconResId,
            sportsCategoryItem = items.map { title ->
                SportsCategoryLists(
                    sportsImageUrl = category.iconResId,
                    sportsTitle = title,
                    sportsDescription = title
                )
            }
        )
    }
}