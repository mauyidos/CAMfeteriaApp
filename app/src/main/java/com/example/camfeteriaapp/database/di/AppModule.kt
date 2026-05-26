package com.example.camfeteriaapp.database.di

import android.content.Context
import androidx.room.Room
import com.example.camfeteriaapp.database.room.UserDataBase
import com.example.camfeteriaapp.database.room.UserDataBaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesUserDao(userDataBase: UserDataBase): UserDataBaseDao {
        return userDataBase.userDao()
    }

    @Singleton
    @Provides
    fun providesUserDataBase(@ApplicationContext context: Context): UserDataBase {
        return Room.databaseBuilder(
            context = context,
            UserDataBase::class.java,
            name = "user_db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}