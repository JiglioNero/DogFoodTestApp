package jiglionero.dogfoodtestapp.data.api.dogfood

import jiglionero.dogfoodtestapp.data.api.dogfood.dto.MessageAllBreads
import jiglionero.dogfoodtestapp.data.api.dogfood.dto.MessageImages
import jiglionero.dogfoodtestapp.data.api.dogfood.dto.MessageSubBreeds
import retrofit2.http.GET
import retrofit2.http.Path

interface DogFoodApiService {
    @GET("api/breeds/list/all")
    suspend fun getAllBreeds(): MessageAllBreads

    @GET("api/breed/{breed}/list")
    suspend fun getAllSubBreeds(@Path("breed") breedName: String): MessageSubBreeds

    @GET("api/breeds/image/random/{count}")
    suspend fun getRandomImage(@Path("count") count: Int = 1): MessageImages

    @GET("api/breed/{breed}/images")
    suspend fun getAllBreedImages(@Path("breed") breedName: String): MessageImages

    @GET("api/breed/{breed}/{subBreed}/images")
    suspend fun getAllSubBreedImages(
        @Path("breed") breedName: String,
        @Path("subBreed") subBreedName: String
    ): MessageImages

    @GET("api/breed/{breed}/images/random/{count}")
    suspend fun getRandomBreedImage(
        @Path("breed") breedName: String,
        @Path("count") count: Int = 1
    ): MessageImages

    @GET("api/breed/{breed}/{subBreed}/images/random/{count}")
    suspend fun getRandomSubBreedImages(
        @Path("breed") breedName: String,
        @Path("subBreed") subBreedName: String,
        @Path("count") count: Int = 1
    ): MessageImages
}