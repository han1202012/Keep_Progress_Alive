package kim.hsl.keep_progress_alive.foreground_service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class CancelNotificationService extends Service {
    public CancelNotificationService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        startForeground(10, new Notification());
        stopSelf();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}