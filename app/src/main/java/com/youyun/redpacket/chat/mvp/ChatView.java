package com.youyun.redpacket.chat.mvp;


import com.youyun.redpacket.chat.model.MessageEntity;

/**
 * Created by 卫彪 on 2016/9/26.
 */
public interface ChatView {

    /**
     * 发送文本回调
     *
     * @param result
     * @param text
     * @param time
     */
    void sendText(boolean result, String text, long time);

    /**
     * 收到消息
     *
     * @param message
     */
    void receiveText(MessageEntity message);
}
