package com.youyun.redpacket.chat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.youyun.redpacket.R;
import com.youyun.redpacket.chat.model.MessageEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 卫彪 on 2016/9/26.
 */
public class ChatAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<MessageEntity> datas = null;
    private SimpleDateFormat format;

    public ChatAdapter(Context context, List<MessageEntity> datas) {
        this.context = context;
        if (datas == null) {
            datas = new ArrayList<>(0);
        }
        this.datas = datas;
        inflater = LayoutInflater.from(context);
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public void refresh(List<MessageEntity> datas) {
        if (datas == null) {
            datas = new ArrayList<>(0);
        }
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public MessageEntity getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getMsgType();
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MessageEntity entity = getItem(position);
        int viewType = getItemViewType(position);
        if (viewType == MessageEntity.CHAT_TYPE_RECV_TEXT) {
            TextHolder holder;
            if (convertView == null || !(convertView.getTag() instanceof TextHolder)) {
                holder = new TextHolder();
                convertView = inflater.inflate(R.layout.adapter_chat_text_left, null);
                holder.dataText = (TextView) convertView.findViewById(R.id.tv_send_time);
                holder.contentText = (TextView) convertView.findViewById(R.id.tv_chat_content);
                convertView.setTag(holder);
            } else {
                holder = (TextHolder) convertView.getTag();
            }
            holder.dataText.setText(format.format(new Date(entity.getTimestamp())));
            holder.contentText.setText(entity.getText());

        } else if (viewType == MessageEntity.CHAT_TYPE_SEND_TEXT) {
            TextHolder holder;
            if (convertView == null || !(convertView.getTag() instanceof TextHolder)) {
                holder = new TextHolder();
                convertView = inflater.inflate(R.layout.adapter_chat_text_right, null);
                holder.dataText = (TextView) convertView.findViewById(R.id.tv_send_time);
                holder.contentText = (TextView) convertView.findViewById(R.id.tv_chat_content);
                convertView.setTag(holder);
            } else {
                holder = (TextHolder) convertView.getTag();
            }
            holder.dataText.setText(format.format(new Date(entity.getTimestamp())));
            holder.contentText.setText(entity.getText());

        }

        return convertView;
    }

    private static class TextHolder {
        TextView contentText;
        TextView dataText;
    }

}
