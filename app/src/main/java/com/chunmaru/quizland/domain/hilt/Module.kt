package com.chunmaru.quizland.domain.hilt

import android.content.Context
import androidx.room.Room
import com.chunmaru.quizland.data.converters.QuestionListConverter
import com.chunmaru.quizland.data.db.QuestionDataBase
import com.chunmaru.quizland.data.db.dao.QuestionDao
import com.chunmaru.quizland.data.db.repositories.DefaultQuestionRepositoryFactory
import com.chunmaru.quizland.data.db.repositories.QuestionRepository
import com.chunmaru.quizland.data.db.repositories.QuestionRepositoryFactory
import com.chunmaru.quizland.data.storage.DataStorageManager
import com.chunmaru.quizland.data.storage.IDataStorageManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideQuestionDatabase(@ApplicationContext context: Context): QuestionDataBase {
        return Room.databaseBuilder(
            context,
            QuestionDataBase::class.java,
            "question_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideQuestionDao(database: QuestionDataBase): QuestionDao {
        return database.getQuestionDao()
    }

    @Singleton
    @Provides
    fun provideQuestionListConverter(): QuestionListConverter = QuestionListConverter()


    @Singleton
    @Provides
    fun provideQuestionRepositoryFactory(
        questionDao: QuestionDao,
        questionListConverter: QuestionListConverter
    ): QuestionRepositoryFactory {
        return DefaultQuestionRepositoryFactory(questionDao, questionListConverter)
    }

    @Singleton
    @Provides
    fun provideQuestionRepository(factory: QuestionRepositoryFactory): QuestionRepository {
        return factory.createQuestionRepository()
    }

    @Singleton
    @Provides
    fun provideStorageManager(@ApplicationContext context: Context): IDataStorageManager {
        return DataStorageManager(context)
    }


}