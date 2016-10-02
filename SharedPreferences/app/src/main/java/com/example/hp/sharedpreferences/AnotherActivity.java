package com.example.hp.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class AnotherActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    String name, roll, domain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        sharedpreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);

        name=sharedpreferences.getString("myname", "No Name found");
        roll=sharedpreferences.getString("myroll", "No RollNo. found");
        domain=sharedpreferences.getString("mydomain", "No Domain found");

        TextView textView1 = (TextView)findViewById(R.id.textView_display);
        TextView textView2 = (TextView)findViewById(R.id.textView_rolldisp);
        TextView textView3 = (TextView)findViewById(R.id.textView_domdisp);

        textView1.setText(name);
        textView2.setText(roll);
        textView3.setText(domain);

        Toast.makeText(AnotherActivity.this,"Here are the last saved details!!",Toast.LENGTH_LONG).show();
    }
}
