package com.youyun.redpacket.chat.mvp;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

import com.ioyouyun.wchat.WeimiInstance;
import com.ioyouyun.wchat.message.ConvType;
import com.ioyouyun.wchat.message.WChatException;
import com.youyun.redpacket.chat.model.MessageEntity;
import com.youyun.redpacket.observer.Observer;
import com.youyun.redpacket.observer.Subject;
import com.youyun.redpacket.utils.YouyunUtils;

/**
 * Created by 卫彪 on 2016/9/26.
 */
public class ChatPresenter {

    private ChatView chatView;
    private Handler handler;

    public ChatPresenter(ChatView chatView, Activity activity) {
        this.chatView = chatView;
        handler = new Handler(Looper.getMainLooper());
        registerObserver();
    }

    private Observer.MessageObserver observer = new Observer.MessageObserver() {
        @Override
        public void update(final MessageEntity message) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    chatView.receiveText(message);
                }
            });
        }
    };

    private void registerObserver() {
        Subject.INSTANCE.addMessageObserver(observer);
    }

    private void unRegisterObserver() {
        Subject.INSTANCE.removeMessageObserver(observer);
    }

    public void onDestroy() {
        unRegisterObserver();
    }

    public void sendText(String touid, String text) {
        String msgId = YouyunUtils.genLocalMsgId();
        try {
            boolean result = WeimiInstance.getInstance().sendText(msgId, touid, text, ConvType.single, null, 60);
            if (result) {
                chatView.sendText(result, text, System.currentTimeMillis());
            }
        } catch (WChatException e) {
            e.printStackTrace();
        }
    }

}
