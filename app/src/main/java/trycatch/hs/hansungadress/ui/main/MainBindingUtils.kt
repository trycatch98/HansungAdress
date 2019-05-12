package trycatch.hs.hansungadress.ui.main

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("searchEvent")
fun EditText.setSearchEvent(vm: MainViewModel) {
    setOnEditorActionListener { _, actionId, _ ->
        when(actionId) {
            EditorInfo.IME_ACTION_SEARCH -> vm.search()
        }
        true
    }
}

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String) {
    Glide.with(this.context).load(url).into(this)
}