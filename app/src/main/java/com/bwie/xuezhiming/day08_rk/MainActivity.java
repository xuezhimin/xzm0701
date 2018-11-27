package com.bwie.xuezhiming.day08_rk;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.bwie.xuezhiming.day08_rk.adapter.MyAdapter;
import com.bwie.xuezhiming.day08_rk.bean.Bean;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private List<Bean.DataBean> list;
    private Handler handler = new Handler() {



        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String data = (String) msg.obj;
                Gson gson = new Gson();
                Bean bean = gson.fromJson(data, Bean.class);
                list = bean.getData();

                MyAdapter adapter = new MyAdapter(MainActivity.this, list);
                lv.setAdapter(adapter);


            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.list_view);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://www.xieast.com/api/news/news.php?page=2");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setConnectTimeout(5000);
                    if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        String data = CharStreams.toString(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));

                        Message message = Message.obtain();
                        message.obj = data;
                        handler.sendMessage(message);


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
