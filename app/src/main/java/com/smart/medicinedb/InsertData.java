package com.smart.medicinedb;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InsertData extends AppCompatActivity {
    EditText nametxt, datetxt, timetxt;
    Button btninsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insert_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nametxt = findViewById(R.id.nametext);
        datetxt = findViewById(R.id.datetext);
        timetxt = findViewById(R.id.timetext);
        btninsert = findViewById(R.id.buttonins);

        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nametxt.getText().toString();
                String date = datetxt.getText().toString();
                String time = timetxt.getText().toString();

                MedicineHelper helper = new MedicineHelper(getBaseContext(),MedicineHelper.DATABASE_NAME,null,1);
                SQLiteDatabase database = helper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("name",name);
                cv.put("date",date);
                cv.put("time",time);

                database.insert("Medicine",null,cv);


                Toast.makeText(getBaseContext(),"Insertion Successfull",Toast.LENGTH_LONG).show();
            }
        });
    }
}