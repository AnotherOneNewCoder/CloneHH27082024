package ru.zhogin.app.search.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.zhogin.app.search.data.database.models.offer.OfferDbo

@Dao
interface OfferDao {
    @Query("SELECT * FROM offers")
    suspend fun getAllOffers() : List<OfferDbo>

    @Query("SELECT * FROM offers")
    fun observeAllOffers() : Flow<List<OfferDbo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOffers(offers: List<OfferDbo>)

    @Query("DELETE FROM offers")
    suspend fun clearOffers()
}