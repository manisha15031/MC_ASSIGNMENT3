package com.example.hp.sharedpreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class InternalStorage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        String msg = getIntent().getStringExtra("str");

        TextView textView1 = (TextView)findViewById(R.id.textView_namein);
        TextView textView2 = (TextView)findViewById(R.id.textView_rollin);
        TextView textView3 = (TextView)findViewById(R.id.textView_domin);

        textView1.setText(msg);
        Toast.makeText(InternalStorage.this,"These are the details saved on Internal Storage!!",Toast.LENGTH_LONG).show();

    }
}
