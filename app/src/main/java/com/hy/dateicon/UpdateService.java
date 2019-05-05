package com.hy.dateicon;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class UpdateService extends Service {
    public UpdateService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        upIcon();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void upIcon(){
        boolean b = Util.setDateICon(UpdateService.this);
        if (b){
            Intent intent = new Intent(UpdateService.this,UpdateService.class);
            stopService(intent);
        } else {
            upIcon();
        }

    }
}
