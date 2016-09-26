package com.youyun.redpacket.chat.model;

/**
 * Created by 卫彪 on 2016/9/26.
 */
public class MessageEntity {

    public static final int CHAT_TYPE_RECV_TEXT = 0; // 接收文本
    public static final int CHAT_TYPE_SEND_TEXT = 1; // 发送文本

    private String text; // 消息内容
    private long timestamp; // 消息时间,13位
    private int msgType; // 消息类型，取值为上面static final定义的常量

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

}
