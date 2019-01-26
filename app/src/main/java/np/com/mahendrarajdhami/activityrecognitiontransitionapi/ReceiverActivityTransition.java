package np.com.mahendrarajdhami.activityrecognitiontransitionapi;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.android.gms.location.ActivityTransitionEvent;
import com.google.android.gms.location.ActivityTransitionResult;

import np.com.mahendrarajdhami.activityrecognitiontransitionapi.utility.MFunction;

public class ReceiverActivityTransition extends BroadcastReceiver {

    public ReceiverActivityTransition() {
        super();
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        if (ActivityTransitionResult.hasResult(intent)) {
            ActivityTransitionResult result = ActivityTransitionResult.extractResult(intent);
            for (ActivityTransitionEvent event : result.getTransitionEvents()) {
                // chronological sequence of events....
                int activityType = event.getActivityType();
                int transitionType = event.getTransitionType();
                String transitionInfo = "("+activityType+","+transitionType+","+ MFunction.getCurrentDateTime()+")";
                broadcastActivity(context,transitionInfo);

            }
        }
    }

    private void broadcastActivity(Context mContext, String transitionInfo) {
        Intent intent = new Intent("activity_transition");
        intent.putExtra("transitionInfo", transitionInfo);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
    }

}