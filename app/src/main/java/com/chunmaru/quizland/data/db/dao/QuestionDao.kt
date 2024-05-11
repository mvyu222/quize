package com.chunmaru.quizland.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.chunmaru.quizland.data.db.model.QuestionModelDb

@Dao
interface QuestionDao {

    @Insert
    suspend fun insert(questionModelDb: QuestionModelDb)

    @Delete
    suspend fun delete(questionModelDb: QuestionModelDb)

    @Query("SELECT * FROM question_table WHERE category = :category")
    fun getQuestionsByCategory(category: String): List<QuestionModelDb>

    @Query("SELECT * FROM question_table WHERE category = :category AND isPass = 0 LIMIT :limit")
    fun getQuestionsByCategoryLimit(
        category: String,
        limit: Int = 10
    ): List<QuestionModelDb>

    @Query("SELECT * FROM question_table WHERE isPass = 0 LIMIT :limit")
    fun getQuestionWithOutCategory(limit: Int = 10): List<QuestionModelDb>

    @Query("SELECT * FROM question_table")
    fun getAllQuestions(): List<QuestionModelDb>

    @Query("UPDATE question_table SET isPass = 1 WHERE id IN (:questionsId)")
    suspend fun setQuestionsPass(questionsId: List<Int>)

    @Query("UPDATE question_table SET isPass = 0 ")
    suspend fun resetQuestionPass()

}