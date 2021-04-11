package kim.hsl.keep_progress_alive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import kim.hsl.keep_progress_alive.account_service.AccountUtils;
import kim.hsl.keep_progress_alive.foreground_service.ForegroundService;
import kim.hsl.keep_progress_alive.jobscheduler.KeepAliveJobService;
import kim.hsl.keep_progress_alive.one_pixel_activity.KeepProgressAliveManager;
import kim.hsl.keep_progress_alive.stick_service.StickService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1.  1 像素 Activity 提升应用权限
        // 注册广播接收者 , 1 像素 Activity 启动的 广播接收者
        //KeepProgressAliveManager.getmInstance().registerReceiver(this);

        // 2. 通过前台 Service 提升应用权限
        // 启动普通 Service , 但是在该 Service 的 onCreate 方法中执行了 startForeground
        // 变成了前台 Service 服务
        //startService(new Intent(this, ForegroundService.class));

        // 3. 使用 Service 机制拉活
        //startService(new Intent(this, StickService.class));

        // 4. 账户同步拉活
        //AccountUtils.addAccount(this);
        // 开始同步
        //AccountUtils.autoSyncStart();

        // 5. JobScheduler 拉活
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            KeepAliveJobService.startJob(this);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 1. 取消注册广播接收者, 也可以不取消注册
        //KeepProgressAliveManager.getmInstance().registerReceiver(this);
    }
}