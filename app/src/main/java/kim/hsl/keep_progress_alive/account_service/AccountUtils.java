package kim.hsl.keep_progress_alive.account_service;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;

public class AccountUtils {

    /**
     * 添加账户类型
     * 在 account-authenticator xml 标签中的 android:accountType 属性中定义的
     */
    public static final String ACCOUNT_TYPE = "keep_progress_alive.account";

    /**
     * 添加账户
     * @param context
     */
    public static void addAccount (Context context){
        AccountManager accountManager = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);

        // 需要使用 android.permission.GET_ACCOUNTS 权限
        Account[] accounts = accountManager.getAccounts();

        // 该类型账号不为空
        if (accounts.length > 0){
            // 账户已存在 , 不进行处理

        }else{
            //创建账户
            Account account = new Account("kim.hsl", ACCOUNT_TYPE);
            // 添加一个新账户
            accountManager.addAccountExplicitly(account, "123456", new Bundle());

        }
    }

    /**
     * 告知系统开始自动同步
     */
    public static void autoSyncStart(){
        //创建账户
        Account account = new Account("kim.hsl", ACCOUNT_TYPE);
        // 设置账户同步开启
        // 注意 : 该操作需要权限 android.permission.WRITE_SYNC_SETTINGS
        ContentResolver.setIsSyncable(account, "kim.hsl.keep_progress_alive.provider", 1);
        // 设置账户自动同步
        ContentResolver.setSyncAutomatically(account, "kim.hsl.keep_progress_alive.provider", true);
        // 设置账户同步周期
        // 最后一个参数是同步周期 , 这个值只是参考值, 系统并不会严格按照该值执行
        // 一般情况下同步的间隔 10 分钟 ~ 1 小时
        ContentResolver.addPeriodicSync(account, "kim.hsl.keep_progress_alive.provider", new Bundle(), 1);
    }

}
