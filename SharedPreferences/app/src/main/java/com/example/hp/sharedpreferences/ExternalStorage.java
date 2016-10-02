package com.example.hp.sharedpreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ExternalStorage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        String msg_ext = getIntent().getStringExtra("ext_str");

        TextView textView1 = (TextView)findViewById(R.id.textView_dataext);

        textView1.setText(msg_ext);

        Toast.makeText(ExternalStorage.this,"These are the details saved on External Storage!!",Toast.LENGTH_LONG).show();
    }
}
