package kim.hsl.keep_progress_alive;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class KeepProgressAliveManager {

    private static KeepProgressAliveManager mInstance;
    private KeepProgressAliveManager(){}
    public static KeepProgressAliveManager getmInstance(){
        if (mInstance == null){
            mInstance = new KeepProgressAliveManager();
        }
        return mInstance;
    }

    /**
     * 注册的广播接收者
     */
    private KeepProgressAliveReceiver mKeepProgressAliveReceiver;

    /**
     * 打开的 1 像素 Activity 界面
     */
    private OnePixelActivity mOnePixelActivity;


    /**
     * 注册广播接收者
     * @param context
     */
    @SuppressLint("LongLogTag")
    public void registerReceiver(Context context){

        Log.i("KeepProgressAliveManager", "注册广播接收者");

        IntentFilter intentFilter = new IntentFilter();
        // 监听屏幕解除锁定广播
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        // 监听[屏幕锁定广播
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);

        // 创建广播接收者
        mKeepProgressAliveReceiver = new KeepProgressAliveReceiver();
        // 注册广播接收者
        context.registerReceiver(mKeepProgressAliveReceiver, intentFilter);
    }

    /**
     * 解除广播接收者注册
     */
    @SuppressLint("LongLogTag")
    public void unregisterReceiver(Context context){
        Log.i("KeepProgressAliveManager", "取消注册广播接收者");
        if(mKeepProgressAliveReceiver != null){
            context.unregisterReceiver(mKeepProgressAliveReceiver);
            mKeepProgressAliveReceiver = null;
        }
    }

    /**
     * 开启 Activity 界面
     */
    public void startActivity(Context context){
        // 开启 OnePixelActivity 界面
        Intent intent = new Intent(context, OnePixelActivity.class);
        // 重新创建一个任务栈 ( 前提是亲和性不同 )
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 设置 1 像素 Activity 界面, 用于关闭
     * @param mOnePixelActivity
     */
    public void setmOnePixelActivity(OnePixelActivity mOnePixelActivity) {
        this.mOnePixelActivity = mOnePixelActivity;
    }

    /**
     * 关闭 Activity 界面
     */
    public void finishActivity(){
        // 关闭 Activity 界面
        this.mOnePixelActivity.finish();
        // 不要长期持有该 Activity 界面
        this.mOnePixelActivity = null;
    }

}
