package ch.maenu.pollingplugin;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.app.PendingIntent;
import android.util.Log;
import android.os.Bundle;


public class PollingReceiver extends BroadcastReceiver {


    public static final String TITLE = "ALARM_TITLE";
    public static final String SUBTITLE = "ALARM_SUBTITLE";
    public static final String TICKER_TEXT = "ALARM_TICKER";
    public static final String NOTIFICATION_ID = "NOTIFICATION_ID";
    public static final String ICON = "ALARM_ICON";




    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("PollingPlugin", "PollingEventReceived");


//        // removes lock screen and activates screen
//        PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
//        WakeLock wakeLock = pm.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
//        wakeLock.acquire();
//
//        KeyguardManager keyguardManager = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);
//        KeyguardLock keyguardLock =  keyguardManager.newKeyguardLock("TAG");
//        keyguardLock.disableKeyguard();



        // TODO: remove vibrator for production
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(1000);
//        performAction(context);



       /*#######################################################################*/
        /**
         * NOTIFICATION
         *
         * http://developer.android.com/guide/topics/ui/notifiers/notifications.html
         */
       /*#######################################################################*/


        Bundle bundle = intent.getExtras();
        NotificationManager notificationMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Get notification Id
        int notificationId = 0;
        try {
            notificationId = Integer.parseInt(bundle.getString(NOTIFICATION_ID));
        } catch (Exception e) {
//            Log.e(LocalNotification.TAG, "Unable to process alarm with id: " + bundle.getString(NOTIFICATION_ID));
        }


        // Create onClick for toast notification
        Intent onClick = new Intent()
                .setAction("ch.maenu.pollingplugin.MAIN")
                .setPackage(context.getPackageName())
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .putExtra(PollingReceiver.NOTIFICATION_ID, notificationId);
        // Create pending intent for onClick
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, onClick, PendingIntent.FLAG_CANCEL_CURRENT);

        // Build Notification
        Notification notification = new Notification.Builder(context)
                .setSmallIcon(bundle.getInt(ICON))
                .setContentTitle(bundle.getString(TITLE))
                .setContentText(bundle.getString(SUBTITLE))
                .setTicker(bundle.getString(TICKER_TEXT))
                .setContentIntent(contentIntent)
                .setVibrate(new long[] { 0, 100, 200, 300 })
                .setWhen(System.currentTimeMillis())
                .build();

//        Log.d(LocalNotification.TAG, "Notification Instantiated: Title: " + bundle.getString(TITLE) + ", Sub Title: " + bundle.getString(SUBTITLE) + ", Ticker Text: " + bundle.getString(TICKER_TEXT));
		/*
		 * If you want all reminders to stay in the notification bar, you should
		 * generate a random ID. If you want do replace an existing
		 * notification, make sure the ID below matches the ID that you store in
		 * the alarm intent.
		 */
        notificationMgr.notify(notificationId, notification);
//        Log.d(LocalNotification.TAG, "Notification created!");

        // NOTE: If the application is closed state, JS gets the notification when the application is opened from tapping the notification
        // see AlarmHelper.java

//        // If we are in background state we still have access to Cordova WebView
//        if (LocalNotification.getCordovaWebView() != null) {
//            // Send JS a message
//            LocalNotification.getCordovaWebView().sendJavascript("cordova.fireDocumentEvent('receivedLocalNotification', { active : true, notificationId : " + notificationId + " })");
//        }

       /*#######################################################################*/





//        // Starts the main app (cordova app)
//        intent = new Intent();
//        intent.setAction("ch.maenu.pollingplugin.MAIN");
//        intent.setPackage(context.getPackageName());
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
    }


//    private void performAction(Context context){
//
//        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
//        String urlArrayJson = settings.getString("PollingPlugin.PollingUrls", "[]");
//
//        JSONArray urlArray;
//
//        try{
//            urlArray = new JSONArray(urlArrayJson);
//            for(int i = 0; i < urlArray.length(); i++){
//                pollUrl(urlArray.getString(i), context);
//            }
//        }
//        catch(JSONException e){
//            e.printStackTrace();
//        }
//    }
//
//    private void pollUrl(String url, Context context){
//        Document doc;
//        try {
//            doc  = Jsoup.connect(url).get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // TODO: do polling and notify if necessary
//
//        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
//        v.vibrate(2000);
//    }
}
