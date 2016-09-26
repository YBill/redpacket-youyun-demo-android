package com.youyun.redpacket.observer;


import com.youyun.redpacket.chat.model.MessageEntity;

/**
 * Created by Bill on 2016/9/8.
 */
public class Observer {

    public interface MessageObserver {
        void update(MessageEntity message);
    }

}
