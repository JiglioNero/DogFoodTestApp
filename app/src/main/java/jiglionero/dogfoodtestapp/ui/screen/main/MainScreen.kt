package jiglionero.dogfoodtestapp.ui.screen.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onNavigateToBreedImages: (String, String?) -> Unit) {
    val viewModel = hiltViewModel<MainScreenViewModel>()
    val breeds = viewModel.breedsState.collectAsLazyPagingItems()
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        items(breeds.itemCount) { index ->
            val breed = breeds[index]
            breed?.let {
                Card(
                    onClick = {
                        onNavigateToBreedImages(it.name, it.parentName.ifEmpty { null })
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = it.getBreedFullName(), modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally))
                }
            }
        }
    }

}