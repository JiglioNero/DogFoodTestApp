package jiglionero.dogfoodtestapp.data.datasource

import jiglionero.dogfoodtestapp.data.repository.BreedRepository

class AllBreedImagesDataSource(
    private val breedName: String,
    private val breedRepository: BreedRepository
): NotPagedDataSource<String>() {
    override suspend fun getItems(): List<String> {
        return breedRepository.getAllBreedImages(breedName)
    }
}