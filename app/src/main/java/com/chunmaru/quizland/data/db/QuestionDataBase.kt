package com.chunmaru.quizland.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chunmaru.quizland.data.db.dao.QuestionDao
import com.chunmaru.quizland.data.db.model.QuestionModelDb

@Database(
    version = 2,
    entities = [
        QuestionModelDb::class,
    ],
)
abstract class QuestionDataBase : RoomDatabase() {

    abstract fun getQuestionDao(): QuestionDao

}