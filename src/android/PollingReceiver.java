package ch.maenu.pollingplugin;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.util.Log;
import android.os.Vibrator;

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

        performAction();

        intent = new Intent();
        intent.setAction("ch.maenu.pollingplugin.ALARM");
        intent.setPackage(context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void performAction(){
        String urlArrayJson = settings.getString("PollingPlugin.PollingUrls", "[]");
        JSONArray urlArray = new JSONArray(urlArrayJson);
        for(var i = 0; i < urlArray.length(); i++){
            pollUrl(urlArray.getString(i));
        }


        // TODO: do polling and notify if necessary
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(2000);
    }

    private void pollUrl(String url){
        Document doc  = Jsoup.connect(url).get();


    }
}
