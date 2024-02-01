package jiglionero.dogfoodtestapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import jiglionero.dogfoodtestapp.data.database.dao.BreedDao
import jiglionero.dogfoodtestapp.data.entity.Breed

@Database(entities = [Breed::class], version = 1)
abstract class DogFoodDatabase: RoomDatabase() {
    abstract fun breedDao(): BreedDao
}