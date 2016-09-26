package com.youyun.redpacket.receive;

import android.content.Context;
import android.util.Log;

import com.ioyouyun.wchat.message.NoticeType;
import com.ioyouyun.wchat.message.NotifyCenter;
import com.ioyouyun.wchat.message.TextMessage;
import com.ioyouyun.wchat.message.WeimiNotice;
import com.youyun.redpacket.chat.model.MessageEntity;
import com.youyun.redpacket.observer.Subject;

/**
 * Created by 卫彪 on 2016/9/8.
 */
public class ReceiveRunnable implements Runnable {

    private Context context;

    public ReceiveRunnable(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        WeimiNotice weimiNotice = null;
        while (true) {
            try {
                weimiNotice = NotifyCenter.clientNotifyChannel.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            NoticeType type = weimiNotice.getNoticeType();
            Log.v("Bill", "type:" + type);

            if (NoticeType.textmessage == type) {
                textMessageMethod(weimiNotice);
            }

        }
    }


    private void textMessageMethod(WeimiNotice weimiNotice) {
        TextMessage textMessage = (TextMessage) weimiNotice.getObject();
        MessageEntity message = new MessageEntity();
        message.setText(textMessage.text);
        message.setTimestamp(textMessage.time);
        message.setMsgType(MessageEntity.CHAT_TYPE_RECV_TEXT);
        Subject.INSTANCE.notifyMessageObservers(message);
    }

}
