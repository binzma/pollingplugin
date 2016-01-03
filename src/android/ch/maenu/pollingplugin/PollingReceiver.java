package ch.maenu.pollingplugin;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Vibrator;
import android.util.Log;


public class PollingReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("PollingPlugin", "PollingEventReceived");

        PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
        WakeLock wakeLock = pm.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
        wakeLock.acquire();

        KeyguardManager keyguardManager = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardLock keyguardLock =  keyguardManager.newKeyguardLock("TAG");
        keyguardLock.disableKeyguard();


        // TODO: remove vibrator for production
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(1000);
//        performAction(context);

        intent = new Intent();
        intent.setAction("ch.maenu.pollingplugin.SCRAPE");
        intent.setPackage(context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
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
