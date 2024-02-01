package jiglionero.dogfoodtestapp.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import jiglionero.dogfoodtestapp.R
import jiglionero.dogfoodtestapp.data.api.dogfood.DogFoodApiService
import jiglionero.dogfoodtestapp.data.api.dogfood.dto.MessageStatus
import jiglionero.dogfoodtestapp.data.database.dao.BreedDao
import jiglionero.dogfoodtestapp.data.entity.Breed
import retrofit2.HttpException
import java.io.IOException


class BreedRepository (
    private val context: Context,
    private val connectivityManager: ConnectivityManager,
    private val breedDao: BreedDao,
    private val dogFoodApi: DogFoodApiService
) {

    suspend fun getAllBreeds(): List<Breed> {
        if (connectivityManager.activeNetwork == null) {
            Toast.makeText(context, context.resources.getString(R.string.no_internet_toast), Toast.LENGTH_SHORT).show()
            return breedDao.getAllBreeds()
        }

        val breedsMessage = dogFoodApi.getAllBreeds()
        if (breedsMessage.status == MessageStatus.FAILURE) {
            Toast.makeText(context, context.resources.getString(R.string.fail_toast), Toast.LENGTH_SHORT).show()
            return breedDao.getAllBreeds()
        }
        val breeds = breedsMessage.getBreedList()
        if (breeds.isEmpty()) {
            return breedDao.getAllBreeds()
        }

        breedDao.insertAll(*breeds.toTypedArray())
        return breeds
    }

    suspend fun getSubBreeds(parentName: String): List<Breed> {
        if (connectivityManager.activeNetwork == null) {
            Toast.makeText(context, context.resources.getString(R.string.no_internet_toast), Toast.LENGTH_SHORT).show()
            return breedDao.getSubBreeds(parentName)
        }

        val breedsMessage = dogFoodApi.getAllSubBreeds(parentName)
        if (breedsMessage.status == MessageStatus.FAILURE) {
            Toast.makeText(context, context.resources.getString(R.string.fail_toast), Toast.LENGTH_SHORT).show()
            return breedDao.getSubBreeds(parentName)
        }
        val breeds = breedsMessage.getBreedList(parentName)
        if (breeds.isEmpty()) {
            return breedDao.getSubBreeds(parentName)
        }
        breedDao.insertAll(*breeds.toTypedArray())
        return breeds
    }

    suspend fun getAllBreedImages(breedName: String): List<String> {
        return try {
            dogFoodApi.getAllBreedImages(breedName).message
        } catch (e: HttpException) {
            Toast.makeText(context, context.resources.getString(R.string.no_internet_toast), Toast.LENGTH_SHORT).show()
            emptyList()
        }
    }

    suspend fun getAllSubBreedImages(breedName: String, subBreedName: String): List<String> {
        return try {
            dogFoodApi.getAllSubBreedImages(breedName, subBreedName).message
        } catch (e: HttpException) {
            Toast.makeText(context, context.resources.getString(R.string.no_internet_toast), Toast.LENGTH_SHORT).show()
            emptyList()
        }
    }

    suspend fun getRandomBreedImage(breedName: String, count: Int = 1): List<String> {
        return try {
            dogFoodApi.getRandomBreedImage(breedName, count).message
        } catch (e: HttpException) {
            Toast.makeText(context, context.resources.getString(R.string.no_internet_toast), Toast.LENGTH_SHORT).show()
            emptyList()
        }
    }

    suspend fun getRandomSubBreedImages(breedName: String, subBreedName: String, count: Int = 1): List<String> {
        return try {
            dogFoodApi.getRandomSubBreedImages(breedName, subBreedName, count).message
        } catch (e: HttpException) {
            Toast.makeText(context, context.resources.getString(R.string.no_internet_toast), Toast.LENGTH_SHORT).show()
            emptyList()
        }
    }

    suspend fun getRandomImages(count: Int = 1): List<String> {
        return try {
            dogFoodApi.getRandomImage(count).message
        } catch (e: HttpException) {
            Toast.makeText(context, context.resources.getString(R.string.no_internet_toast), Toast.LENGTH_SHORT).show()
            emptyList()
        }
    }

}