package trycatch.hs.hansungadress.util

import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import javax.net.ssl.*
import javax.security.cert.CertificateException
import javax.security.cert.X509Certificate

class SSLConnect {
    // always verify the host - dont check for certificate
    val DO_NOT_VERIFY: HostnameVerifier = object : HostnameVerifier {
        override fun verify(hostname: String?, session: SSLSession?): Boolean {
            return true
        }
    }

    /**
     * Trust every server - don't check for any certificate
     */
    private fun trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out java.security.cert.X509Certificate>?, authType: String?) {
            }

            override fun checkServerTrusted(chain: Array<out java.security.cert.X509Certificate>?, authType: String?) {
            }

            override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                return arrayOf()
            }
        })

        // Install the all-trusting trust manager
        try {
            val sc = SSLContext.getInstance("TLS")
            sc.init(null, trustAllCerts, java.security.SecureRandom())
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory())
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun postHttps(url: String, connTimeout: Int, readTimeout: Int): HttpsURLConnection? {
        trustAllHosts()

        var https: HttpsURLConnection? = null
        try {
            https = URL(url).openConnection() as HttpsURLConnection
            https!!.setHostnameVerifier(DO_NOT_VERIFY)
            https!!.setConnectTimeout(connTimeout)
            https!!.setReadTimeout(readTimeout)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
            return null
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

        return https
    }
}