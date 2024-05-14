package com.chunmaru.quizland.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_table")
data class QuestionModelDb(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val question: String,
    @ColumnInfo val image: String?,
    @ColumnInfo val category: String,
    @ColumnInfo val answers: String,
    @ColumnInfo val correctAnswer: String,
    @ColumnInfo val score: Int,
    @ColumnInfo val isPass: Boolean = false,
)