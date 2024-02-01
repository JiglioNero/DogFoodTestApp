package jiglionero.dogfoodtestapp.ui.view

import android.content.res.Resources
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import jiglionero.dogfoodtestapp.R
import jiglionero.dogfoodtestapp.data.entity.Breed


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BreedView(breed: Breed, url: String, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier.padding(16.dp)
    ) {
        Column {
            GlideImage(
                model = url,
                contentDescription = null,
                loading = placeholder(
                    Resources.getSystem().getDrawable(R.drawable.baseline_image_24)
                )
            )
            Text(breed.name, modifier = Modifier.fillMaxWidth().padding(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BreedViewPreview() {
    BreedView(Breed("Breed"), "https://images.dog.ceo/breeds/affenpinscher/n02110627_10147.jpg")
}