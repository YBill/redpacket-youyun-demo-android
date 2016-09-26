package com.youyun.redpacket;

import android.app.Application;

import com.yunzhanghu.redpacketsdk.RedPacket;
import com.yunzhanghu.redpacketsdk.constant.RPConstant;

/**
 * Created by 卫彪 on 2016/9/26.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RedPacket.getInstance().initContext(getApplicationContext(), RPConstant.AUTH_METHOD_SIGN);
        RedPacket.getInstance().setDebugMode(true);
    }
}
