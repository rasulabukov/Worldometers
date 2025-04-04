package com.example.worldometers

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

class PopulationRepository {
    companion object {
        private const val TAG = "PopulationRepository"
        private const val TEST_API_URL = "https://jsonplaceholder.typicode.com/todos/1"
    }

    suspend fun fetchPopulationData(): PopulationData {
        return try {
            Log.d(TAG, "Fetching test data from $TEST_API_URL")

            val jsonStr = withContext(Dispatchers.IO) {
                URL(TEST_API_URL).readText()
            }

            val json = JSONObject(jsonStr)
            val title = json.optString("title", "No title")
            val id = json.optInt("id", -1)

            Log.d(TAG, "Successfully fetched test data: $title (ID: $id)")
            PopulationData(worldPopulation = "ID: $id, Title: $title")
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching test data", e)
            PopulationData(worldPopulation = "Error: ${e.message}")
        }
    }
}