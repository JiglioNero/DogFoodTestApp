package jiglionero.dogfoodtestapp.data.datasource

import jiglionero.dogfoodtestapp.data.entity.Breed
import jiglionero.dogfoodtestapp.data.repository.BreedRepository

class AllBreedsDataSource(
    private val breedRepository: BreedRepository
): NotPagedDataSource<Breed>() {
    override suspend fun getItems(): List<Breed> {
        return breedRepository.getAllBreeds()
    }
}