package ch.maenu.pollingplugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;


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
         * http://www.vogella.com/tutorials/AndroidNotifications/article.html
         */
       /*#######################################################################*/


        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(this, NotificationReceiverActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(this)
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText("Subject").setSmallIcon(R.drawable.icon)
                .setContentIntent(pIntent)
                .addAction(R.drawable.icon, "Call", pIntent)
                .addAction(R.drawable.icon, "More", pIntent)
                .addAction(R.drawable.icon, "And more", pIntent).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);


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
