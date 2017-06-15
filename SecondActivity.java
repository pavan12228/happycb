package com.example.ravi.listpractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import java.util.List;



public class SecondActivity extends AppCompatActivity
{
    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        List<Person> personList= (List<Person>) getIntent().getSerializableExtra("selectedValues");
        listView= (ListView) findViewById(R.id.lvItems);

        HappyAdapter happyAdapter=new HappyAdapter(getApplicationContext(),personList);
        listView.setAdapter(happyAdapter);

    }

}
