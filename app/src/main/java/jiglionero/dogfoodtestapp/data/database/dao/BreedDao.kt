package jiglionero.dogfoodtestapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jiglionero.dogfoodtestapp.data.entity.Breed

@Dao
interface BreedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg breeds: Breed)

    @Query("SELECT * FROM breeds")
    suspend fun getAllBreeds(): List<Breed>

    @Query("SELECT * FROM breeds WHERE parentName = :parentName")
    suspend fun getSubBreeds(parentName: String): List<Breed>

    @Query("SELECT * FROM breeds WHERE name = :name")
    suspend fun getBreed(name: String): Breed

    @Query("SELECT * FROM breeds WHERE parentName IS NULL")
    suspend fun getParentBreeds(): List<Breed>
}