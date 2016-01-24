package ch.maenu.pollingplugin;

import android.app.Notification;
import android.app.NotificationManager;
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
    public void onReceive(Context context, Intent intent) {
        Log.d("PollingPlugin", "PollingEventReceived");

        // bitmap http://stackoverflow.com/questions/11182714/bitmapfactory-example


        Bitmap bMap = BitmapFactory.decodeResource(context.getResources(), R.drawable.screen);

        Notification noti = new Notification.Builder(context)
                .setContentTitle("Kurs XYZ")
                .setContentText("Du kannst dich nun anmelden!")
                .setSmallIcon(R.drawable.icon)
                .setLargeIcon(bMap)
                .build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.notify(9999, noti);



        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        int dot = 300;
        int short_gap = 200;
        long[] pattern = {
                0,  // Start immediately
                300, 200, 300
        };

        // Only perform this pattern one time (-1 means "do not repeat")
        v.vibrate(pattern, -1);


//        performAction(context);





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
