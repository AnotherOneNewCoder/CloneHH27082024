package ru.zhogin.app.search.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.zhogin.app.search.data.database.dao.OfferDao
import ru.zhogin.app.search.data.database.dao.VacancyDao
import ru.zhogin.app.search.data.database.models.offer.OfferDbo
import ru.zhogin.app.search.data.database.models.vacancy.VacancyDbo
import ru.zhogin.app.search.data.database.utils.GsonConverter

class VacanciesAndOffersDatabase internal constructor(private val database: VacanciesAndOffersRoomDatabase) {
    val offerDao: OfferDao
        get() = database.offerDao()
    val vacancyDbo: VacancyDao
        get() = database.vacancyDao()
}

@Database(
    entities = [
        OfferDbo::class,
        VacancyDbo::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(GsonConverter::class)
internal abstract class VacanciesAndOffersRoomDatabase : RoomDatabase() {
    abstract fun offerDao() : OfferDao
    abstract fun vacancyDao() : VacancyDao
}

fun VacanciesAndOffersDatabase(applicationContext: Context) : VacanciesAndOffersDatabase {
    val vacanciesAndOffersRoomDatabase = Room.databaseBuilder(
        checkNotNull(applicationContext.applicationContext),
        VacanciesAndOffersRoomDatabase::class.java,
        "vacancies_offers"
    ).build()
    return VacanciesAndOffersDatabase(vacanciesAndOffersRoomDatabase)
}