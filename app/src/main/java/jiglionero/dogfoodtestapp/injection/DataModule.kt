package jiglionero.dogfoodtestapp.injection

import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.os.Looper
import android.widget.Toast
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jiglionero.dogfoodtestapp.R
import jiglionero.dogfoodtestapp.data.api.dogfood.DogFoodApiService
import jiglionero.dogfoodtestapp.data.database.DogFoodDatabase
import jiglionero.dogfoodtestapp.data.database.dao.BreedDao
import jiglionero.dogfoodtestapp.data.repository.BreedPagedRepository
import jiglionero.dogfoodtestapp.data.repository.BreedRepository
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(context.resources.getString(R.string.dogfood_api_base_url))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context, connectivityManager: ConnectivityManager): OkHttpClient {
        val cacheSize = context.resources.getInteger(R.integer.dogfood_api_cache_size).toLong()
        val httpCacheDirectory = File(context.cacheDir, "dogfood_api_cache")
        val cache = Cache(httpCacheDirectory, cacheSize)

        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor { chain ->
                val network = connectivityManager.activeNetwork
                val isConnected = network != null

                var request = chain.request()
                request = if (isConnected) {
                    request.newBuilder().header("Cache-Control", "public").build()
                } else {
                    request.newBuilder().header("Cache-Control", "public, only-if-cached").build()
                }
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideDogApiService(retrofit: Retrofit): DogFoodApiService {
        return retrofit.create(DogFoodApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DogFoodDatabase {
        return Room.databaseBuilder(
            context,
            DogFoodDatabase::class.java,
            context.resources.getString(R.string.dogfood_database_name)
        ).build()
    }

    @Provides
    @Singleton
    fun provideBreedDao(database: DogFoodDatabase) = database.breedDao()

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager = context.getSystemService(ConnectivityManager::class.java)

    @Provides
    @Singleton
    fun provideBreedRepository(@ApplicationContext context: Context, connectivityManager: ConnectivityManager, breedDao: BreedDao, dogFoodApi: DogFoodApiService) = BreedRepository(context, connectivityManager, breedDao, dogFoodApi)

    @Provides
    @Singleton
    fun provideBreedPagedRepository(@ApplicationContext context: Context, breedRepository: BreedRepository): BreedPagedRepository{
        return BreedPagedRepository(context.resources.getInteger(R.integer.data_page_size), breedRepository)
    }

}