package trycatch.hs.hansungadress.ui.login

import android.view.View
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.backgroundColorResource
import trycatch.hs.hansungadress.R

class LoginViewModel : ViewModel(), AnkoLogger {
    private val COLOR_FOCUS_ON = R.color.white
    private val COLOR_FOCUS_OUT = R.color.gray

    fun onFocusChange(view: View, hasFocus: Boolean) {
        (view.parent as View).run {
            backgroundColorResource = if (hasFocus) COLOR_FOCUS_ON else COLOR_FOCUS_OUT
        }
    }
}