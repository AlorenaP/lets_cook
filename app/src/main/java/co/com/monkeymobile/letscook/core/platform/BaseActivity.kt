package co.com.monkeymobile.letscook.core.platform

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import co.com.monkeymobile.letscook.AndroidApplication
import co.com.monkeymobile.letscook.R
import co.com.monkeymobile.letscook.core.di.ApplicationComponent

abstract class BaseActivity : AppCompatActivity() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as AndroidApplication).appComponent
    }

    private var indeterminateDialog: AlertDialog? = null

    private fun initIndeterminateModalDialog() {
        val activity = this
        val view = activity.layoutInflater.inflate(R.layout.modal_progress_indeterminate, null)
        indeterminateDialog = AlertDialog.Builder(activity).create()
        indeterminateDialog?.let {
            it.setCancelable(false)
            it.setView(view)
        }
    }

    fun showIndeterminateModalDialog(): Unit = indeterminateDialog?.show() ?: run {
        initIndeterminateModalDialog()
        showIndeterminateModalDialog()
    }

    fun hideIndeterminateModalDialog() = indeterminateDialog?.cancel().also { indeterminateDialog = null }
        ?: Unit

    override fun onStop() {
        super.onStop()
        hideIndeterminateModalDialog()
    }
}