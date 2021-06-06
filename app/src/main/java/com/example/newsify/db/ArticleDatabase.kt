package com.example.newsify.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsify.model.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Convertors::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDAO(): ArticleDAO

    companion object {

        @Volatile
        private var instance: ArticleDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                ArticleDatabase::class.java,
                "Articles.db"
            ).build()
    }
}