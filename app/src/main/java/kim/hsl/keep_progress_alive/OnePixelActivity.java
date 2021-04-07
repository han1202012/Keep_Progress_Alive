package kim.hsl.keep_progress_alive;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

/**
 * 只有 1 像素的 Activity
 */
public class OnePixelActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("OnePixelActivity", "onCreate");

        // 获取本界面的窗口 Window 对象
        Window window = getWindow();
        // 屏幕左上角展示
        window.setGravity(Gravity.LEFT | Gravity.TOP);

        // 将 Activity 设置成 1 像素
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        // 宽高都设置 1 像素
        layoutParams.width = 1;
        layoutParams.height = 1;
        // 放置位置 (0, 0) 坐标开始放置
        layoutParams.x = 0;
        layoutParams.y = 0;

        // 在将布局参数设置会 Window 对象中
        window.setAttributes(layoutParams);

        // 设置界面到 KeepProgressAliveManager 单例对象中 , 用于关闭界面
        KeepProgressAliveManager.getmInstance().setmOnePixelActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("OnePixelActivity", "onDestroy");
    }
}
