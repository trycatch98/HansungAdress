package trycatch.hs.hansungadress.data.remote.model

import com.google.gson.annotations.SerializedName

data class AddressModel(
        val department: String,
        val position: String,
        val name: String,
        @SerializedName("photo")
        private val _photo: String,
        var office: String?,
        var phone: String?,
        val email: String,
        var favorite: Boolean
) {
    val photo get() = "https://smart.hansung.ac.kr/profile_image?u=" + _photo.substring(_photo.length - 6, _photo.length)
}
