package com.mysteriouscoder.brainbooster

import android.content.Context

class PreferenceManager(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("PhysicalAndMentalHealth", Context.MODE_PRIVATE)


    fun save(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun get(key: String): String? {
        return sharedPreferences.getString(key, "0")
    }

//    fun clear() {
//        sharedPreferences.edit().clear().apply()
//    }
//
//    fun clear(key: String) {
//        sharedPreferences.edit().remove(key).apply()
//    }
}