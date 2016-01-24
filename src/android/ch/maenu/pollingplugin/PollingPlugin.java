package ch.maenu.pollingplugin;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;


public class PollingPlugin extends CordovaPlugin {


    @Override
    public void onPause(boolean multitasking) {
        Log.d("PollingPlugin", "onPause");
        super.onPause(multitasking);
    }

    @Override
    public void onResume(boolean multitasking) {
        Log.d("PollingPlugin", "onResume " );
        super.onResume(multitasking);

//        PowerManager pm = (PowerManager)this.cordova.getActivity().getSystemService(Context.POWER_SERVICE);
//        WakeLock wakeLock = pm.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
//        wakeLock.acquire();
//
//        KeyguardManager keyguardManager = (KeyguardManager) this.cordova.getActivity().getSystemService(Context.KEYGUARD_SERVICE);
//        KeyguardLock keyguardLock =  keyguardManager.newKeyguardLock("TAG");
//        keyguardLock.disableKeyguard();
    }

    @Override
    public boolean execute(final String action, final JSONArray args, final CallbackContext command) {

        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try{
                    if ("addUrl".equals(action)) {
                        addUrl(args);
                        command.success();
                    } else if ("removeUrl".equals(action)) {
                        removeUrl(args);
                        command.success();
                    } else if ("deactivate".equals(action)) {
                        deactivate(args);
                        command.success();
                    } else if ("setInterval".equals(action)) {
                        setInterval(args);
                        command.success();
                    }
                }
                catch(Exception e){
                    command.error(e.getMessage());
                }
            }
        });

        return true;
    }


    private void addUrl(JSONArray args) throws JSONException {

        String url = args.getString(0);

        // TODO: get settings, add url, commit settings


        // TODO: remove vibrator for production
        Vibrator v = (Vibrator) this.cordova.getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);
    }

    private void removeUrl(JSONArray args) throws JSONException {

        String url = args.getString(0);

        // TODO: get settings, add url, commit settings




        // TODO: remove vibrator for production
        Vibrator v = (Vibrator) this.cordova.getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);
    }


    private void deactivate(JSONArray args) throws JSONException {

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this.cordova.getActivity());
        SharedPreferences.Editor editor = settings.edit();
        // writes the PollingDate to the settings
        editor.putLong("PollingPlugin.PollingInterval", 0);
        editor.commit();

        AlarmManager alarmMgr = (AlarmManager)(this.cordova.getActivity().getSystemService(Context.ALARM_SERVICE));

        PendingIntent alarmIntent;
        Intent intent = new Intent(this.cordova.getActivity(), PollingReceiver.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        alarmIntent = PendingIntent.getBroadcast(this.cordova.getActivity(), 0, intent, 0);
        alarmMgr.cancel(alarmIntent);

        // TODO: remove vibrator for production
        Vibrator v = (Vibrator) this.cordova.getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);
    }


    private void setInterval(JSONArray args) throws JSONException {

        long interval = args.getLong(0);

        // TODO: uncomment this for production
//				if(interval < MIN_INTERVAL) {
//					callbackContext.error("The interval must be at least " + MIN_INTERVAL);
//					return true;
//				}

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this.cordova.getActivity());
        SharedPreferences.Editor editor = settings.edit();
        // writes the PollingDate to the settings
        editor.putLong("PollingPlugin.PollingInterval", interval);
        editor.commit();

        AlarmManager alarmMgr = (AlarmManager)(this.cordova.getActivity().getSystemService(Context.ALARM_SERVICE));

        // Create the intent, which will be triggered when the alarm goes off
        PendingIntent alarmIntent;
        Intent intent = new Intent(this.cordova.getActivity(), PollingReceiver.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        alarmIntent = PendingIntent.getBroadcast(this.cordova.getActivity(), 0, intent, 0);
        alarmMgr.cancel(alarmIntent);


        // power saving mode
        // alarm interval: (see https://developer.android.com/training/scheduling/alarms.html)
        alarmMgr.setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                interval /* time until first trigger in millis */,
                interval /* interval to trigger again in millis */,
                alarmIntent);
        // exact mode:
        // TODO: exact alarm scheduling needs one-time alarms that reschedule a new one-time alarm when fired.


        // TODO: remove vibrator for production
        Vibrator v = (Vibrator) this.cordova.getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);
    }
}
