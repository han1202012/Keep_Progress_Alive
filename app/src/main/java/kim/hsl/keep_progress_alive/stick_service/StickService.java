package kim.hsl.keep_progress_alive.stick_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class StickService extends Service {
    public StickService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}