package jiglionero.dogfoodtestapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breeds", primaryKeys = ["name", "parentName"])
data class Breed (
    val name: String,
    var parentName: String = "",
) {
    fun getBreedFullName(): String {
        if (parentName.isEmpty()) {
            return name
        }
        return "$name $parentName"
    }
}