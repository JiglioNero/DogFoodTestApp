package jiglionero.dogfoodtestapp.ui.screen.main

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import jiglionero.dogfoodtestapp.data.entity.Breed
import jiglionero.dogfoodtestapp.data.repository.BreedPagedRepository
import jiglionero.dogfoodtestapp.data.repository.BreedRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.cache
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val breedPagedRepository: BreedPagedRepository
) : ViewModel() {

    val breedsState: MutableStateFlow<PagingData<Breed>> = MutableStateFlow(value = PagingData.empty())

    init {
        getBreeds()
    }

    private fun getBreeds() {
        viewModelScope.launch {
            breedPagedRepository.getAllBreeds()
                .cachedIn(viewModelScope)
                .collect {
                    breedsState.value = it
                }
        }
    }
}