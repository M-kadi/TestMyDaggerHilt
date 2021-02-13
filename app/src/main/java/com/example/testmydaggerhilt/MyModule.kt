package com.example.testmydaggerhilt

import android.content.Context
import android.content.SharedPreferences
import com.example.testmydaggerhilt.room.UserDao
import com.example.testmydaggerhilt.room.UserRepository
import com.example.testmydaggerhilt.room.UserRoomDatabase
import com.example.testmydaggerhilt.sqlite.DbHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MyModule {

    @Provides
    @Singleton
    fun provideCar3(): Car3 = Car3()

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context : Context): SharedPreferences =
            context.getSharedPreferences("PrefName", Context.MODE_PRIVATE)

    // Inject Sqlite
    @Provides
    @Singleton
    fun provideDbHelper(@ApplicationContext context : Context): DbHelper = DbHelper(context, "demo-dagger.db", 2)

    // Inject room
    @Provides
    @Singleton
    fun provideUserDao(@ApplicationContext appContext: Context): UserDao =
        UserRoomDatabase.getDatabase(appContext).userDao()

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository = UserRepository(userDao)
}