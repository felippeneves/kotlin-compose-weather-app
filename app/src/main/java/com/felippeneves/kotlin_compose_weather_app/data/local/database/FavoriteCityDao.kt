package com.felippeneves.kotlin_compose_weather_app.data.local.database

import androidx.room.Dao
import androidx.room.Query
import com.felippeneves.kotlin_compose_weather_app.data.model.entity.FavoriteCityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCityDao: BaseDao<FavoriteCityEntity> {
    @Query(
        """   
            SELECT *   
            FROM TB_FAVORITE_CITY
        """
    )
    fun selectAll(): Flow<List<FavoriteCityEntity>>

    @Query(
        """   
            SELECT *   
            FROM TB_FAVORITE_CITY
            WHERE CITY = :city
        """
    )
    fun selectById(city: String): Flow<FavoriteCityEntity>

    @Query(
        """   
            DELETE 
            FROM TB_FAVORITE_CITY
        """
    )
    suspend fun deleteAll()

    @Query(
        """   
            DELETE 
            FROM TB_FAVORITE_CITY
            WHERE CITY = :city
        """
    )
    suspend fun deleteById(city: String)

    @Query(
        """   
            SELECT COUNT(1)  
            FROM TB_FAVORITE_CITY
            WHERE CITY = :city
        """
    )
    fun countById(city: String): Flow<Int>
}
