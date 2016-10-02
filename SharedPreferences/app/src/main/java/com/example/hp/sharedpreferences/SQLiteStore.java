package com.example.hp.sharedpreferences;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SQLiteStore extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText editName, editNative, editID;
    Button btnAdd, btnRet, btnUpdate, btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_store);

        mydb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_sqname);
        editNative = (EditText) findViewById(R.id.editText_native);
        editID = (EditText) findViewById(R.id.editText_id);

        btnAdd = (Button) findViewById(R.id.button_insert);
        btnRet = (Button) findViewById(R.id.button_retreive);
        btnUpdate = (Button) findViewById(R.id.button_update);
        btnDel = (Button) findViewById(R.id.button_delete);
        addData();
        retrieveData();
        updateData();
        deleteDate();
    }

    public void addData() {
        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isAdded = mydb.insertData(editName.getText().toString(), editNative.getText().toString());
                        if (isAdded = true)
                            Toast.makeText(SQLiteStore.this, "Details Inserted Successfully!!", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(SQLiteStore.this, "Sorry, Details not saved!!", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void retrieveData() {
        btnRet.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Cursor result = mydb.getData();
                        if (result.getCount() == 0) {
                            Toast.makeText(SQLiteStore.this, "No record retrieved!!", Toast.LENGTH_LONG).show();
                            return;

                        }

                        StringBuffer stringBuffer = new StringBuffer();
                        while (result.moveToNext()) {
                            //if(result.getString(1)==editName.getText().toString()) {
                                stringBuffer.append("ID: " + result.getString(0) + "\t");
                                stringBuffer.append("Name: " + result.getString(1) + "\t");
                                stringBuffer.append("Native_Place: " + result.getString(2) + "\t\n");
                            //}
                            }
                        showMessage("Retrieved_Data", stringBuffer.toString());
                    }
                }

        );

    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    public void updateData(){
        btnUpdate.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        boolean isUpdated = mydb.updateDate(editID.getText().toString(), editName.getText().toString(), editNative.getText().toString());
                        if (isUpdated = true)
                            Toast.makeText(SQLiteStore.this, "Details Updated Successfully!!", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(SQLiteStore.this, "Sorry, Details not Updated!!", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void deleteDate(){
        btnDel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deletedRecords=mydb.deleteData(editID.getText().toString());
                        if(deletedRecords > 0)
                            Toast.makeText(SQLiteStore.this, "Records Deleted Successfully!!", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(SQLiteStore.this, "Sorry, Records not Deleted!!", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
}
