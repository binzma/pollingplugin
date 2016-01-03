package ch.maenu.pollingplugin;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PollingBoot extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intentp)
	{
		if (!intentp.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
			return;
		}

		// reads the PollingDate from the settings
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
		long interval = settings.getLong("PollingPlugin.PollingInterval", 0);

		// interval is 0 if deactivated
		if(interval == 0){
			return;
		}

		AlarmManager alarmMgr = (AlarmManager)(context.getSystemService(Context.ALARM_SERVICE));

		PendingIntent alarmIntent;
		Intent intent = new Intent(context, PollingReceiver.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

		// power saving mode
		// alarm interval: (see https://developer.android.com/training/scheduling/alarms.html)
		alarmMgr.setInexactRepeating(
				AlarmManager.ELAPSED_REALTIME_WAKEUP,
				interval /* time until first trigger in millis */,
				interval /* interval to trigger again in millis */,
				alarmIntent);
		// exact mode:
		// TODO: exact alarm scheduling needs one-time alarms that reschedule a new one-time alarm when fired.


	}
}
