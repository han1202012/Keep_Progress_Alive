package kim.hsl.keep_progress_alive.account_service;

import android.accounts.Account;
import android.app.Service;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class AccountSyncService extends Service {

    // 账户同步 IBinder 对象
    private ThreadSyncAdapter mThreadSyncAdapter;

    public AccountSyncService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mThreadSyncAdapter.getSyncAdapterBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mThreadSyncAdapter = new ThreadSyncAdapter(getApplicationContext(), true);
    }

    class ThreadSyncAdapter extends AbstractThreadedSyncAdapter{

        public ThreadSyncAdapter(Context context, boolean autoInitialize) {
            super(context, autoInitialize);
        }

        public ThreadSyncAdapter(Context context, boolean autoInitialize,
                                 boolean allowParallelSyncs) {
            super(context, autoInitialize, allowParallelSyncs);
        }

        @Override
        public void onPerformSync(Account account, Bundle extras, String authority,
                                  ContentProviderClient provider, SyncResult syncResult) {
            // 账户同步操作
            // 与数据库 , 服务器同步操作 , 这里只是为了应用进程拉活 , 不实现具体的逻辑
            Log.i("AccountSyncService", "账户同步拉活激活");
        }
    }

}