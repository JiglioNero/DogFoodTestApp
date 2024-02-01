package jiglionero.dogfoodtestapp.ui.screen.breed

import android.content.res.Resources
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import jiglionero.dogfoodtestapp.R

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
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}