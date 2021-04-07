package kim.hsl.keep_progress_alive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 注册广播接收者
        KeepProgressAliveManager.getmInstance().registerReceiver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 取消注册广播接收者, 也可以不取消注册
        //KeepProgressAliveManager.getmInstance().registerReceiver(this);
    }
}