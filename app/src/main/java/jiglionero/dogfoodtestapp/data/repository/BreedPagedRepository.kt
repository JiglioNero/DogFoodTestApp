package jiglionero.dogfoodtestapp.data.repository

import android.content.res.Resources
import androidx.compose.foundation.pager.PageSize
import androidx.paging.Pager
import androidx.paging.PagingConfig
import jiglionero.dogfoodtestapp.data.datasource.AllBreedImagesDataSource
import jiglionero.dogfoodtestapp.data.datasource.AllBreedsDataSource
import jiglionero.dogfoodtestapp.data.datasource.AllSubBreedImagesDataSource
import jiglionero.dogfoodtestapp.data.datasource.SubBreedsDataSource
import javax.inject.Inject

class BreedPagedRepository (
    private val pageSize: Int,
    private val breedRepository: BreedRepository
) {
    fun getAllBreeds() = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { AllBreedsDataSource(breedRepository) }
    ).flow

    fun getSubBreeds(parentName: String) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { SubBreedsDataSource(parentName, breedRepository) }
    ).flow

    fun getAllBreedImages(breedName: String) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { AllBreedImagesDataSource(breedName, breedRepository) }
    ).flow

    fun getAllSubBreedImages(parentBreedName: String, subBreedName: String) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { AllSubBreedImagesDataSource(parentBreedName, subBreedName, breedRepository) }
    ).flow
}