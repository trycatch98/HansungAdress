package trycatch.hs.hansungadress.ui.login

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("onFocus")
fun TextInputEditText.onFocus(vm: LoginViewModel) {
    setOnFocusChangeListener{ view: View, hasFocus: Boolean ->
        vm.onFocusChange(view, hasFocus)
    }
}