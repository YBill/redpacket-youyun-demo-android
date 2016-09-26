package com.youyun.redpacket.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.youyun.redpacket.R;
import com.youyun.redpacket.chat.adapter.ChatAdapter;
import com.youyun.redpacket.chat.model.MessageEntity;
import com.youyun.redpacket.chat.mvp.ChatPresenter;
import com.youyun.redpacket.chat.mvp.ChatView;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements ChatView, View.OnClickListener {

    private ListView mRealListView;
    List<MessageEntity> datas = new ArrayList<>();
    private ChatAdapter adapter;
    private Button sendBtn;
    private View inputbox;
    private EditText editView;
    private TextView titleText;

    private InputMethodManager manager;
    private ChatPresenter presenter;
    private String toUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        initView();
        addListener();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        toUid = intent.getStringExtra("touid");
        titleText.setText(toUid);

        presenter = new ChatPresenter(this, this);
        adapter = new ChatAdapter(this, datas);
        mRealListView.setAdapter(adapter);
    }

    private void addListener() {
        sendBtn.setOnClickListener(this);
        mRealListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideSoftInput(editView);
                return false;
            }
        });
    }

    private void initView() {
        setToolBar();

        titleText = (TextView) findViewById(R.id.tv_title);
        inputbox = findViewById(R.id.messageToolBox);
        mRealListView = (ListView) findViewById(R.id.chat_listview);
        mRealListView.setSelector(android.R.color.transparent);
        sendBtn = (Button) findViewById(R.id.chatbox_send);
        editView = (EditText) findViewById(R.id.chatbox_message);
        editView.requestFocus();
    }

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


    @Override
    public void sendText(boolean result, String text, long time) {
        if (result) {
            MessageEntity message = new MessageEntity();
            message.setText(text);
            message.setTimestamp(time);
            message.setMsgType(MessageEntity.CHAT_TYPE_SEND_TEXT);
            datas.add(message);
            adapter.refresh(datas);

            editView.setText("");
        }
    }

    @Override
    public void receiveText(MessageEntity message) {
        if (message != null) {
            datas.add(message);
            adapter.refresh(datas);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chatbox_send:
                String msgString = editView.getText().toString();
                presenter.sendText(toUid, msgString);
                break;
        }

    }

    /**
     * 隐藏软件盘
     */
    private void hideSoftInput(View view) {
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
