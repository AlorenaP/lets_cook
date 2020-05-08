package co.com.monkeymobile.letscook.features.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import co.com.monkeymobile.letscook.R
import co.com.monkeymobile.letscook.core.platform.BaseActivity
import co.com.monkeymobile.letscook.features.recipes.RecipesListActivity

class SplashActivity : BaseActivity() {

    companion object {
        const val TIMER = 1000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        supportActionBar?.hide()
        Handler().postDelayed({
            startActivity(Intent(this, RecipesListActivity::class.java))
            finish()
        }, TIMER)
    }
}
