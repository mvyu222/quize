package com.chunmaru.quizland.data.storage

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object StorageConst {
    val USER_ID_KEY = intPreferencesKey("user_id")
    val USER_NAME_KEY = stringPreferencesKey("user_name")
    val USER_ICON_KEY = stringPreferencesKey("user_icon")
    val USER_SCORE_KEY = intPreferencesKey("user_score")
}