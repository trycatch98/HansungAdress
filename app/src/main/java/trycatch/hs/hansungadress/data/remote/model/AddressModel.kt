package trycatch.hs.hansungadress.data.remote.model

data class AddressModel(
        val department: String,
        val position: String,
        val name: String,
        val photo: String,
        var office: String?,
        var phone: String?,
        val email: String,
        var favorite: Boolean
)
