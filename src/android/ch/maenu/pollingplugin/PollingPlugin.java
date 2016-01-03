package ch.maenu.pollingplugin;

import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.preference.PreferenceManager;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;


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

		PowerManager pm = (PowerManager)this.cordova.getActivity().getSystemService(Context.POWER_SERVICE);
		WakeLock wakeLock = pm.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
		wakeLock.acquire();

		KeyguardManager keyguardManager = (KeyguardManager) this.cordova.getActivity().getSystemService(Context.KEYGUARD_SERVICE);
		KeyguardLock keyguardLock =  keyguardManager.newKeyguardLock("TAG");
		keyguardLock.disableKeyguard();
	}

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		try {
			if ("programAlarm".equals(action)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
				Date aDate = sdf.parse(args.getString(0).replace("Z", "+0000"));
				JSONArray urls = args.getJSONArray(1);
				Date n = new Date();

				if(aDate.before(n)) {
					callbackContext.error("The date is in the past");
					return true;
				}

				SharedPreferences settings = context.getSharedPreferences("uspSettings", Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = settings.edit();
				// writes the PollingDate to the settings
				editor.putLong("PollingPlugin.PollingDate", aDate.getTime());
				// writes the urls to poll to the settings
				editor.putString("PollingPlugin.PollingUrls", urls.toString());
				editor.commit();

				AlarmManager alarmMgr = (AlarmManager)(this.cordova.getActivity().getSystemService(Context.ALARM_SERVICE));

				Intent intent = new Intent(this.cordova.getActivity(), PollingReceiver.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				alarmIntent = PendingIntent.getBroadcast(this.cordova.getActivity(), 0, intent, 0);
				// Create the intent, which will be triggered when the alarm goes off
				PendingIntent alarmIntent;

				alarmMgr.cancel(alarmIntent);

//				// set the alarm to be triggered at the specified time
//				alarmMgr.set(AlarmManager.RTC_WAKEUP,  aDate.getTime(), alarmIntent);

				// alarm interval: (see https://developer.android.com/training/scheduling/alarms.html)
				alarmMgr.setInexactRepeating(
						AlarmManager.ELAPSED_REALTIME_WAKEUP,
						1000 /*trigger first time in millis*/,
						15 * 60 * 1000 /*interval to trigger again in millis*/,
						alarmIntent);

				callbackContext.success("Alarm set at: " +sdf.format(aDate));
				return true;
			}
			return false;
		} catch(Exception e) {
			System.err.println("Exception: " + e.getMessage());
			callbackContext.error(e.getMessage());
			return false;
		}
	}
}