package ch.maenu.pollingplugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;


public class PollingReceiver extends BroadcastReceiver {

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
//
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
//                        .setSmallIcon(R.drawable.notification_icon)
//                        .setContentTitle("My notification")
//                        .setContentText("Hello World!");
//// Creates an explicit intent for an Activity in your app
//        Intent resultIntent = new Intent(this, ResultActivity.class);
//
//// The stack builder object will contain an artificial back stack for the
//// started Activity.
//// This ensures that navigating backward from the Activity leads out of
//// your application to the Home screen.
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//// Adds the back stack for the Intent (but not the Intent itself)
//        stackBuilder.addParentStack(ResultActivity.class);
//// Adds the Intent that starts the Activity to the top of the stack
//        stackBuilder.addNextIntent(resultIntent);
//        PendingIntent resultPendingIntent =
//                stackBuilder.getPendingIntent(
//                        0,
//                        PendingIntent.FLAG_UPDATE_CURRENT
//                );
//        mBuilder.setContentIntent(resultPendingIntent);
//        NotificationManager mNotificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//// mId allows you to update the notification later on.
//        mNotificationManager.notify(mId, mBuilder.build());
//


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
