package com.goldenplanet.Install_AOSkt

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.APP_OPS_SERVICE
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.AsyncTask
import android.os.Build
import android.provider.Settings.Global.getString
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat.getSystemService
import com.goldenplanet.`Install_AOSkt`.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

//javax.net.ssl.HostnameVerifier

class MyFirebaseMessagingService : FirebaseMessagingService() {
    var mbitmap: Bitmap? = null
    var bitmap: Bitmap? = null
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.data.size > 0) {
            title = remoteMessage.data["title"]
            message = remoteMessage.data["message"]
            img_url = remoteMessage.data["image_url"]
            //new getNotification(this,title, message, img_url).execute();
        }
    }
    class getNotification(private val mContext: Context, private val title: String, private val message: String, private val imageUrl: String) : AsyncTask<String?, Void?, Bitmap?>() {
        protected override fun doInBackground(vararg params: String?): Bitmap? {
            val `in`: InputStream
            try {
//                trustAllHosts();
                val connection: HttpURLConnection
                val url = URL(imageUrl)
                if (url.protocol.toLowerCase() == "https") {
                    val https = url.openConnection() as HttpsURLConnection
                    https.hostnameVerifier = DO_NOT_VERIFY
                    connection = https
                } else {
                    connection = url.openConnection() as HttpURLConnection
                }
                connection.doInput = true
                connection.connect()
                `in` = connection.inputStream
                return BitmapFactory.decodeStream(`in`)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            //val channelId: String = getString(R.string.default_notification_channel_id)
            val channelId: String = "android"
            val intent = Intent(mContext, MainActivity::class.java)
            intent.putExtra("notificationKey", "notificationValue")
            val pendingIntent = PendingIntent.getActivity(mContext, 100, intent, PendingIntent.FLAG_ONE_SHOT)
            val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationBuilder = NotificationCompat.Builder(mContext, channelId)
                        .setContentTitle(title).setContentText(message).setSmallIcon(R.drawable.goldenplaneticon)
                        .setAutoCancel(true).setSound(defaultSoundUri).setLargeIcon(result)
                        .setStyle(NotificationCompat.BigPictureStyle().bigPicture(result).bigLargeIcon(null))
                        .setContentIntent(pendingIntent)
                //val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                //val notificationManager = Context.APP_OPS_SERVICE(NOTIFICATION_SERVICE) as NotificationManager
                val channel = NotificationChannel(channelId, "android", NotificationManager.IMPORTANCE_DEFAULT)
                //notificationManager.createNotificationChannel(channel)
                //notificationManager.notify(0, notificationBuilder.build())
            } else {
                /*val notification = NotificationCompat.Builder(mContext)
                        .setContentTitle(title).setContentText(message).setSmallIcon(R.drawable.goldenplaneticon)
                        .setAutoCancel(true).setSound(defaultSoundUri).setLargeIcon(result)
                        .setStyle(NotificationCompat.BigPictureStyle().bigPicture(result).bigLargeIcon(null))
                        .setContentIntent(pendingIntent).setVisibility(NotificationCompat.VISIBILITY_PUBLIC).build()
                 */
                //val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                //notificationManager.notify(0, notification)
            }
        }

    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
        private var title: String? = null
        private var message: String? = null
        private var img_url: String? = null
        val DO_NOT_VERIFY = HostnameVerifier { hostname, session -> true }
        private fun trustAllHosts() {
            val trustAllCerts = arrayOf<TrustManager>(
                    object : X509TrustManager {
                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }

                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                        }

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                        }
                    }
            )
            try {
                val sc = SSLContext.getInstance("TLS")
                sc.init(null, trustAllCerts, SecureRandom())
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}