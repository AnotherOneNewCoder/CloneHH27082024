package ru.zhogin.app.search.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

import ru.zhogin.app.search.data.database.models.vacancy.VacancyDbo

/*
   Done like this in order o make app work when api will be dead
 */
@Dao
interface VacancyDao {
    @Query("SELECT * FROM vacancies")
    suspend fun getAllVacancies() : List<VacancyDbo>

    @Query("SELECT * FROM vacancies WHERE is_favorite = 0")
    fun observeAllVacanciesInSearchScreen() : Flow<List<VacancyDbo>>

    @Query("SELECT * FROM vacancies WHERE is_favorite = 1")
    fun observeAllVacanciesInFavouriteScreen() : Flow<List<VacancyDbo>>

    @Query("SELECT * FROM vacancies")
    fun observeAllVacancies() : Flow<List<VacancyDbo>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVacancies(offers: List<VacancyDbo>)

    @Query("DELETE FROM vacancies")
    suspend fun clearVacancies()

    @Query("UPDATE vacancies SET is_favorite = 1 WHERE id = :id AND is_favorite = 0")
    suspend fun showInFavourite(id: String)

    @Query("UPDATE vacancies SET is_favorite = 0 WHERE id = :id AND is_favorite = 1")
    suspend fun hideInFavourite(id: String)

}