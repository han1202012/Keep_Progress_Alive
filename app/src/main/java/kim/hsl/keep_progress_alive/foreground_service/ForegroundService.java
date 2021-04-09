package kim.hsl.keep_progress_alive.foreground_service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import kim.hsl.keep_progress_alive.R;

import static androidx.core.app.NotificationCompat.PRIORITY_MIN;

public class ForegroundService extends Service {
    public ForegroundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // startForeground();

            // 创建通知通道
            NotificationChannel channel = new NotificationChannel("service",
                    "service", NotificationManager.IMPORTANCE_NONE);
            channel.setLightColor(Color.BLUE);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            NotificationManager service = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // 正式创建
            service.createNotificationChannel(channel);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "service");
            Notification notification = builder.setOngoing(true)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setPriority(PRIORITY_MIN)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .build();

            // 开启前台进程 , API 26 以上无法关闭通知栏
            startForeground(10, notification);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
            startForeground(10, new Notification());
            // API 18 ~ 25 以上的设备 , 启动相同 id 的前台服务 , 并关闭 , 可以关闭通知
            startService(new Intent(this, CancelNotificationService.class));

        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2){
            // 将该服务转为前台服务
            // 需要设置 ID 和 通知
            // 设置 ID 为 0 , 就不显示已通知了 , 但是 oom_adj 值会变成后台进程 11
            // 设置 ID 为 1 , 会在通知栏显示该前台服务
            // 8.0 以上该用法报错
            startForeground(10, new Notification());
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    /**
     * 启动前台服务
     */
    private void startForeground() {
        String channelId = null;
        // 8.0 以上需要特殊处理
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channelId = createNotificationChannel("kim.hsl", "ForegroundService");
        } else {
            channelId = "";
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId);
        Notification notification = builder.setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(PRIORITY_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(10, notification);
    }

    /**
     * 创建通知通道
     * @param channelId
     * @param channelName
     * @return
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private String createNotificationChannel(String channelId, String channelName){
        // 创建通知通道
        NotificationChannel channel = new NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_NONE);
        channel.setLightColor(Color.BLUE);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager service = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // 正式创建
        service.createNotificationChannel(channel);
        return channelId;
    }
}