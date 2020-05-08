package co.com.monkeymobile.letscook.core.di

import android.content.Context
import co.com.monkeymobile.letscook.AndroidApplication
import co.com.monkeymobile.letscook.features.recipes.*
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("http://gl-endpoint.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideGetItemService(retrofit: Retrofit) = GetRecipesService(retrofit)

    @Provides
    @Singleton
    fun provideGetItemRepo(service: GetRecipesService): GetRecipesRepo = GetRecipesRepoImpl(service)
}