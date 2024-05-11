package com.chunmaru.quizland.data.storage

import com.chunmaru.quizland.data.models.UserModel
import kotlinx.coroutines.flow.Flow

interface IDataStorageManager {
    fun getUser(): Flow<UserModel>
    suspend fun addPoints(points: Int)
    suspend fun resetPoint()
    suspend fun setUserIcon(icon: String)
    suspend fun setUserName(name: String)
}
