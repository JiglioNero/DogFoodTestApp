package jiglionero.dogfoodtestapp.ui.screen.breed

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BreedImagesScreen(name : String, parentName : String?) {
    val viewModel = hiltViewModel<BreedImagesViewModel>()
    val images = viewModel.getImagesByBreed(name, parentName).collectAsLazyPagingItems()

    LazyColumn {
        items(images.itemCount) { index ->
            val image = images[index]
            image?.let {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    GlideImage(
                        model = it,
                        contentDescription = "$name $parentName",
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}