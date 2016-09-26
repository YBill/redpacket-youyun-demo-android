package com.youyun.redpacket.utils;

import android.app.Activity;
import android.provider.Settings;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * Created by 卫彪 on 2016/9/8.
 */
public class YouyunUtils {

    public final static String CLIENT_ID = "1-20521-1b766ad17389c94e1dc1f2615714212a-andriod";
    public final static String SECRET = "d5cf0a5812b4424f582ded05937e4387";
    public final static String FACETYPE = "facetype";
    public final static String EMOJITYPE = "emojitype";

    /**
     * 获取Android Id
     *
     * @return
     */
    public static String generateOpenUDID(Activity activity) {
        // Try to get the ANDROID_ID
        String OpenUDID = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (OpenUDID == null || OpenUDID.equals("9774d56d682e549c") | OpenUDID.length() < 15) {
            // if ANDROID_ID is null, or it's equals to the GalaxyTab generic
            // ANDROID_ID or bad, generates a new one
            final SecureRandom random = new SecureRandom();
            OpenUDID = new BigInteger(64, random).toString(16);
        }
        return OpenUDID;
    }

    public static final String MSG_ID_PRE = UUID.randomUUID() + "";
    public static int msg_p = 0;

    public static String genLocalMsgId() {
        msg_p++;
        String msgId = MSG_ID_PRE + msg_p;
        return msgId;
    }

}
