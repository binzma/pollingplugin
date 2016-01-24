package ch.maenu.pollingplugin;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Vibrator;
import android.util.Log;

import ch.unisport.R;


public class PollingReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent pIntent) {
        Log.d("PollingPlugin", "PollingEventReceived");

        notifyCourse(context, "Kurs XYZ (test)", "Du kannst dich nun anmelden!");

//        performAction(context);



    }



    private void notifyCourse(Context context, String title, String message){

        // Starts the main app (cordova app)
        Intent intent = new Intent(context, ch.unisport.MainActivity.class);
        intent.setAction("android.intent.action.MAIN");
        intent.setPackage("ch.unisport");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent startCordovaApp = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);


        // bitmap http://stackoverflow.com/questions/11182714/bitmapfactory-example


        Bitmap bMap = BitmapFactory.decodeResource(context.getResources(), R.drawable.screen);

        Notification noti = new Notification.Builder(context)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.icon)
                .setLargeIcon(bMap)
                .setAutoCancel(true)
                .setVibrate(new long[] {0,
                        500, 100,
                        100, 100,
                        100})
                .setContentIntent(startCordovaApp)
                .build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.notify(9999, noti);

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
