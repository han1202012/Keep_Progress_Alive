package kim.hsl.keep_progress_alive;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class KeepProgressAliveReceiver extends BroadcastReceiver {
    @SuppressLint("LongLogTag")
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i("KeepProgressAliveReceiver", "action : " + action);

        if (Intent.ACTION_SCREEN_OFF.equals(action)){
            // 锁屏时开启 Activity
            KeepProgressAliveManager.getmInstance().startActivity(context);
            Log.i("KeepProgressAliveReceiver", "锁屏, 开启 1 像素 Activity");

        }else if (Intent.ACTION_SCREEN_ON.equals(action)){
            // 解除所屏蔽关闭 Activity
            KeepProgressAliveManager.getmInstance().finishActivity();
            Log.i("KeepProgressAliveReceiver", "解除锁屏, 关闭 1 像素 Activity");
        }
    }
}
