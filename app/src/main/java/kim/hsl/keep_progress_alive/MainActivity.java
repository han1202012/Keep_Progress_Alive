package kim.hsl.keep_progress_alive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import kim.hsl.keep_progress_alive.foreground_service.ForegroundService;
import kim.hsl.keep_progress_alive.one_pixel_activity.KeepProgressAliveManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1 像素 Activity 提升应用权限
        // 注册广播接收者 , 1 像素 Activity 启动的 广播接收者
        //KeepProgressAliveManager.getmInstance().registerReceiver(this);

        // 通过前台 Service 提升应用权限
        // 启动普通 Service , 但是在该 Service 的 onCreate 方法中执行了 startForeground
        // 变成了前台 Service 服务
        startService(new Intent(this, ForegroundService.class));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 取消注册广播接收者, 也可以不取消注册
        //KeepProgressAliveManager.getmInstance().registerReceiver(this);
    }
}