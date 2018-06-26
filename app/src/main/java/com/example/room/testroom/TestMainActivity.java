package com.example.room.testroom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.room.testroom.database.AppDatabase;
import com.example.room.testroom.database.UserDao;
import com.example.room.testroom.database.UserEntity;

import java.util.List;

public class TestMainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button, button1;
    private TextView textView;

    UserDao dao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_room_layout_activity);
        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.textView);
        button1.setOnClickListener(this);
        button.setOnClickListener(this);
        init();

    }

    public void init() {
        dao = AppDatabase.getInstance(getApplicationContext()).userDao();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                new Thread() {
                    @Override
                    public void run() {
                        final UserEntity entity = new UserEntity("bili", "bilibi");
                        dao.insertAll(entity);
                        TestMainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText("插入成功:" + entity.toString());
                            }
                        });
                    }
                }.start();

                break;
            case R.id.button2:
                List<UserEntity> list = dao.getAll();
                if (!list.isEmpty()) {
                    textView.setText("查询结果为：" + list.toString());
                }
                break;
        }

    }
}
