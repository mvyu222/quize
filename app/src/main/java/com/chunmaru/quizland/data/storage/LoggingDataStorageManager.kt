package com.chunmaru.quizland.data.storage

import android.util.Log
import com.chunmaru.quizland.data.models.UserModel
import kotlinx.coroutines.flow.Flow

class LoggingDataStorageManager(
    private val dataStorageManager: IDataStorageManager
) : IDataStorageManager by dataStorageManager {

    override fun getUser(): Flow<UserModel> {
        val userFlow = dataStorageManager.getUser()
        Log.d("MyTag", "getUser: $userFlow ")
        return userFlow
    }

    override suspend fun addPoints(points: Int) {
        dataStorageManager.addPoints(points)
        Log.d("MyTag", "addPoints: $points ")
    }

    override suspend fun resetPoint() {
        dataStorageManager.resetPoint()
        Log.d("MyTag", "resetPoint: success ")
    }

    override suspend fun setUserIcon(icon: String) {
        dataStorageManager.setUserIcon(icon)
        Log.d("MyTag", "setUserIcon: $icon ")
    }

    override suspend fun setUserName(name: String) {
        dataStorageManager.setUserName(name)
        Log.d("MyTag", "setUserIcon: $name ")
    }
}