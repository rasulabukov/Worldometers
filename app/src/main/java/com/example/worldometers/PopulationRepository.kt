package com.example.worldometers

import org.jsoup.Jsoup
import javax.inject.Inject


class PopulationRepository @Inject constructor() {

    suspend fun fetchPopulationData(): PopulationData {
        return try {
            // Подключаемся к сайту и получаем HTML
            val doc = Jsoup.connect("https://www.worldometers.info/world-population/").get()

            // Парсим данные о населении
            val populationElement = doc.select("div.maincounter-number span").first()
            val population = populationElement?.text() ?: "N/A"

            PopulationData(worldPopulation = population)
        } catch (e: Exception) {
            e.printStackTrace()
            PopulationData(worldPopulation = "Error fetching data")
        }
    }
}