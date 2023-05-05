package com.example.coffee;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

        //实现OnItemClickListener，点击时打开二级页面
        AdapterView.OnItemClickListener itemClickListener =
                (parent, view, position, id) -> {
                    //Drinks位于第一项，索引为0，点击Drinks,启动DrinkCategoryActivity;
                    if (position == 0) {
                        Intent intent = new Intent(MainActivity.this,
                                DrinkCategoryActivity.class);
                        startActivity(intent);
                    }
                };
        //注册监听事件
        ListView listView = findViewById(R.id.store_list);
        listView.setOnItemClickListener(itemClickListener);
    }
}