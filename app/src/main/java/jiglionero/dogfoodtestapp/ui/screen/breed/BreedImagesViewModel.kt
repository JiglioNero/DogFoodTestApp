package jiglionero.dogfoodtestapp.ui.screen.breed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import jiglionero.dogfoodtestapp.data.repository.BreedPagedRepository
import jiglionero.dogfoodtestapp.data.repository.BreedRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.cache
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class BreedImagesViewModel @Inject constructor(
    private val breedPagedRepository: BreedPagedRepository
) : ViewModel() {
    fun getImagesByBreed(breedName: String, parentName: String?): Flow<PagingData<String>> {
        return if (parentName == null) {
            breedPagedRepository.getAllBreedImages(breedName).cachedIn(viewModelScope)
        } else {
            breedPagedRepository.getAllSubBreedImages(parentName, breedName).cachedIn(viewModelScope)
        }
    }
}