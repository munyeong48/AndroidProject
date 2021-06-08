package com.goldenplanet.www.gp_android.Firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.NotificationCompat;


import com.goldenplanet.www.gp_android.MainActivity;
import com.goldenplanet.www.gp_android.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    Bitmap mbitmap = null;
    private static String title, message, img_url;
    Bitmap bitmap;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if(remoteMessage.getData().size() > 0 ){
            title = remoteMessage.getData().get("title");
            message = remoteMessage.getData().get("message");
            img_url = remoteMessage.getData().get("image_url");
            //new getNotification(this,title, message, img_url).execute();

        }

    }

    public class getNotification extends AsyncTask<String, Void, Bitmap> {

        private Context mContext;
        private String title, message, imageUrl;

        public getNotification(Context context, String title, String message, String imageUrl) {
            super();
            this.mContext = context;
            this.title = title;
            this.message = message;
            this.imageUrl = imageUrl;
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            InputStream in;
            try {
//                trustAllHosts();
                HttpURLConnection connection;
                URL url = new URL(this.imageUrl);
                if (url.getProtocol().toLowerCase().equals("https")) {
                    HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
                    https.setHostnameVerifier(DO_NOT_VERIFY);
                    connection = https;
                } else {
                    connection = (HttpURLConnection) url.openConnection();
                }
                connection.setDoInput(true);
                connection.connect();
                in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }
        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            String channelId = getString(R.string.default_notification_channel_id);
            Intent intent = new Intent(mContext, MainActivity.class);
            intent.putExtra("notificationKey", "notificationValue");
            PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 100, intent, PendingIntent.FLAG_ONE_SHOT);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mContext, channelId)
                        .setContentTitle(title).setContentText(message).setSmallIcon(R.drawable.goldenplaneticon)
                        .setAutoCancel(true).setSound(defaultSoundUri).setLargeIcon(result)
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(result).bigLargeIcon(null))
                        .setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationChannel channel = new NotificationChannel(channelId, "android", NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);
                notificationManager.notify(0 , notificationBuilder.build());
            } else {
                Notification notification = new NotificationCompat.Builder(mContext)
                        .setContentTitle(title).setContentText(message).setSmallIcon(R.drawable.goldenplaneticon)
                        .setAutoCancel(true).setSound(defaultSoundUri).setLargeIcon(result)
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(result).bigLargeIcon(null))
                        .setContentIntent(pendingIntent).setVisibility(NotificationCompat.VISIBILITY_PUBLIC).build();

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0, notification);

            }
        }
    }

       final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };


    private static void trustAllHosts() {
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() { return new java.security.cert.X509Certificate[] {}; }
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException { }
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException { }
                }
        };

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}