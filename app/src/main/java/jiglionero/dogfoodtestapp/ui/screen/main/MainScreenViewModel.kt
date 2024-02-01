package jiglionero.dogfoodtestapp.ui.screen.main

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import jiglionero.dogfoodtestapp.data.repository.BreedPagedRepository
import jiglionero.dogfoodtestapp.data.repository.BreedRepository
import kotlinx.coroutines.async
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
    lateinit var listState: LazyListState
    fun getBreeds() = breedPagedRepository.getAllBreeds().cachedIn(viewModelScope)
}