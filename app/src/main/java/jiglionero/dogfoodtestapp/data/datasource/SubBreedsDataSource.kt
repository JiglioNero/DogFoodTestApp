package jiglionero.dogfoodtestapp.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import jiglionero.dogfoodtestapp.data.entity.Breed
import jiglionero.dogfoodtestapp.data.repository.BreedRepository

class SubBreedsDataSource(
    private val parentBreedName: String,
    private val breedRepository: BreedRepository
): NotPagedDataSource<Breed>() {
    override suspend fun getItems(): List<Breed> {
        return breedRepository.getSubBreeds(parentBreedName)
    }
}