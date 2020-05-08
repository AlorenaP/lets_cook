package co.com.monkeymobile.letscook.features.recipe_detail

import android.util.Log
import co.com.monkeymobile.letscook.core.utils.ERROR_CODE_IO_EXCEPTION
import co.com.monkeymobile.letscook.core.utils.ERROR_CONTENT_LENGTH
import co.com.monkeymobile.letscook.core.utils.MEDIA_TYPE_JSON
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class GetRecipeDetailsService @Inject constructor(private val retrofit: Retrofit) {

    fun fetchRecipeDetails(id: String): Response<RecipeDetails> {
        return try {
            retrofit.create(RecipeDetailsApi::class.java).fetchRecipeDetails(id).execute()
        } catch (exception: Exception) {
            Log.e(javaClass.name, exception.toString())
            Response.error(
                ERROR_CODE_IO_EXCEPTION,
                ResponseBody.create(
                    MediaType.get(MEDIA_TYPE_JSON),
                    ERROR_CONTENT_LENGTH.toLong(),
                    Buffer()
                )
            )
        }
    }
}