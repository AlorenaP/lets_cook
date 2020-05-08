package co.com.monkeymobile.letscook.core.di

import co.com.monkeymobile.letscook.AndroidApplication
import co.com.monkeymobile.letscook.features.recipes.RecipesListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: AndroidApplication)
    fun inject(recipesListActivity: RecipesListActivity)
}