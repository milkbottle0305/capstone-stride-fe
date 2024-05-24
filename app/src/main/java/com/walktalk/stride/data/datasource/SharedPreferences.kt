package com.walktalk.stride.data.datasource

import android.content.Context

class SharedPreferences(context: Context) {
    private val prefsFilename = "Prefs"
    private val prefs = context.getSharedPreferences(prefsFilename, Context.MODE_PRIVATE)

    fun setStringPref(key: String, value: String) {
        try {
            prefs.edit().putString(key, value).apply()
        } catch (e: Exception) {
            throw Exception("Error setting string preference")
        }
    }

    fun getStringPref(key: String): String? {
        try {
            return prefs.getString(key, null)
        } catch (e: Exception) {
            throw Exception("Error getting string preference")
        }
    }
}