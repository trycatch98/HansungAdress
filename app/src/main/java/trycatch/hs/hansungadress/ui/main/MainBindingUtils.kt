package trycatch.hs.hansungadress.ui.main

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("searchEvent")
fun EditText.setSearchEvent(vm: MainViewModel) {
    setOnEditorActionListener { _, actionId, _ ->
        when(actionId) {
            EditorInfo.IME_ACTION_SEARCH -> vm.search()
        }
        true
    }
}