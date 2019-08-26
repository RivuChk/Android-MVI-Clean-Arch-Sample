package dev.rivu.nasaapodarchive.cache.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.rivu.nasaapodarchive.cache.db.ApodDatabase
import dev.rivu.nasaapodarchive.cache.androidtest.factory.APODFactory
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ApodDaoTest {
    private lateinit var apodDao: ApodDao
    private lateinit var db: ApodDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, ApodDatabase::class.java
        ).build()
        apodDao = db.apodDao()
    }

    @After
    fun closeDB() {
        db.close()
    }

    @Test
    fun saveApods() {
        val apodEntity = APODFactory.makeApodEntity()
        val insertObserver = apodDao.saveApods(listOf(apodEntity))
            .test()

        insertObserver.assertComplete()

        val testObserver = apodDao.getApod(apodEntity.date)
            .test()

        testObserver.assertValue(apodEntity)
        testObserver.assertComplete()
    }
}