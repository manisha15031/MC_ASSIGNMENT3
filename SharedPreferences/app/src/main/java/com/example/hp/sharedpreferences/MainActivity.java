package com.example.hp.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    TextView textViewHead;
    EditText editTextName, editTextRoll, editTextDomain;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewHead = (TextView)findViewById(R.id.textView);

        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextRoll = (EditText) findViewById(R.id.editText_roll);
        editTextDomain = (EditText) findViewById(R.id.editText_domain);

        Button saveButton = (Button)findViewById(R.id.button_save);
        Button viewButton = (Button)findViewById(R.id.button_view);

        Button sqliteButton = (Button)findViewById(R.id.button_sqlite);

        saveButton.setOnClickListener(this);
        viewButton.setOnClickListener(this);
        sqliteButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v.getId()==R.id.button_save) {
            String name= editTextName.getText().toString();
            String roll= editTextRoll.getText().toString();
            String domain= editTextDomain.getText().toString();

            sharedpreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("myname",name);
            editor.putString("myroll",roll);
            editor.putString("mydomain",domain);

            editor.commit();

            Toast.makeText(MainActivity.this,"Thanks, your details have been saved using shared preferences!!",Toast.LENGTH_LONG).show();
        }

        else if(v.getId()==R.id.button_sqlite) {
            startActivity(new Intent(getApplicationContext(),SQLiteStore.class));
        }

        else {
            startActivity(new Intent(getApplicationContext(),AnotherActivity.class));
        }
    }


    public void writeMessage(View view){

        String name_in=editTextName.getText().toString();
        String roll_in=editTextRoll.getText().toString();
        String dom_in=editTextDomain.getText().toString();

        String file_name="details_in";

        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name,MODE_PRIVATE);
            fileOutputStream.write(name_in.getBytes());
            fileOutputStream.write(roll_in.getBytes());
            fileOutputStream.write(dom_in.getBytes());
            fileOutputStream.close();
            Toast.makeText(MainActivity.this,"Details saved on Internal Storage!!",Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readMessage(View view){
        try {
            String data_in;
           // String message;
            FileInputStream fileInputStream = openFileInput("details_in");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((data_in=bufferedReader.readLine())!=null) {
                stringBuffer.append(data_in+" ");
            }
            //bufferedReader.close();
            //message=stringBuffer.toString();

            //Intent i = new Intent (this,InternalStorage.class);
            Intent i = new Intent (getApplicationContext(),InternalStorage.class);
            i.putExtra("str",stringBuffer.toString());
            startActivity(i);
            finish();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeExternalStorage(View view) {
        String state;
        state = Environment.getExternalStorageState(); //returns current state of external storage

        if(Environment.MEDIA_MOUNTED.equals(state))
        {
            //To store in External Storage, defining root, folder and filename
            File Root = Environment.getExternalStorageDirectory();
            File Dir = new File(Root.getAbsolutePath()+"/Details_ext"); //folder path specified
            if(!Dir.exists()) {
                Dir.mkdir(); //checks whether folder already available or not, if not available creates one
            }
            File file = new File(Dir,"DetailsExternal.txt"); //new file created under the folder

            String name_ext=editTextName.getText().toString();
            String roll_ext=editTextRoll.getText().toString();
            String dom_ext=editTextDomain.getText().toString();

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(name_ext.getBytes());
                fileOutputStream.write(roll_ext.getBytes());
                fileOutputStream.write(dom_ext.getBytes());
                fileOutputStream.close();
                Toast.makeText(MainActivity.this,"Details saved on External Storage!!",Toast.LENGTH_LONG).show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else
        {
            Toast.makeText(getApplicationContext(),"SD Card Not Found!!",Toast.LENGTH_LONG).show();
        }

        File Root = Environment.getExternalStorageDirectory();
    }

    public void readExternalStorage(View view) {
        File Root = Environment.getExternalStorageDirectory();
        File Dir = new File(Root.getAbsolutePath()+"/Details_ext"); //folder path specified
        File file = new File(Dir,"DetailsExternal.txt"); //textFile in which data gets stored

        String Data_ext;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            while((Data_ext=bufferedReader.readLine())!=null){
                stringBuffer.append(Data_ext+" ");
            }

            Intent i = new Intent (getApplicationContext(),ExternalStorage.class);
            i.putExtra("ext_str",stringBuffer.toString());
            startActivity(i);
            finish();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(getApplicationContext(), "Portrait Mode", Toast.LENGTH_SHORT).show();
        }

        else if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(getApplicationContext(), "Landscape Mode", Toast.LENGTH_SHORT).show();
        }
    }
}
