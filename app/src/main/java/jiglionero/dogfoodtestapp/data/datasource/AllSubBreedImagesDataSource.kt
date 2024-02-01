package jiglionero.dogfoodtestapp.data.datasource

import jiglionero.dogfoodtestapp.data.repository.BreedRepository

class AllSubBreedImagesDataSource(
    private val parentBreedName: String,
    private val subBreedName: String,
    private val breedRepository: BreedRepository
): NotPagedDataSource<String>() {
    override suspend fun getItems(): List<String> {
        return breedRepository.getAllSubBreedImages(parentBreedName, subBreedName)
    }
}