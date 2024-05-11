package com.chunmaru.quizland.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.chunmaru.quizland.data.models.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

private val Context.dataStorage: DataStore<Preferences> by preferencesDataStore("data_storage")

class DataStorageManager(
    private val context: Context
) {

    fun getUser(): Flow<UserModel> {
        return context.dataStorage.data.map { pref ->
            UserModel(
                id = pref[StorageConst.USER_ID_KEY] ?: -1,
                name = pref[StorageConst.USER_NAME_KEY] ?: "person",
                pic = pref[StorageConst.USER_ICON_KEY] ?: "person1",
                score = pref[StorageConst.USER_SCORE_KEY] ?: 0
            )
        }
    }

    suspend fun addPoints(points: Int) {
        val score = getUserScore() + points
        setUserScore(score)
    }

    suspend fun resetPoint() {
        context.dataStorage.edit { pref ->
            pref[StorageConst.USER_SCORE_KEY] = 0
        }
    }

    private suspend fun getUserScore(): Int {
        return context.dataStorage.data.map { pref ->
            pref[StorageConst.USER_SCORE_KEY]
        }.firstOrNull() ?: 0
    }

    private suspend fun setUserScore(score: Int) {
        context.dataStorage.edit { pref ->
            pref[StorageConst.USER_SCORE_KEY] = score
        }
    }


    suspend fun setUserIcon(icon: String) {
        context.dataStorage.edit { pref ->
            pref[StorageConst.USER_ICON_KEY] = icon
        }
    }

    suspend fun setUserName(name: String) {
        context.dataStorage.edit { pref ->
            pref[StorageConst.USER_NAME_KEY] = name
        }
    }


}