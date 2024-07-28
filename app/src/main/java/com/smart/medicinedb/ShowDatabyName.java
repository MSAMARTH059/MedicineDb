package com.smart.medicinedb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ShowDatabyName extends AppCompatActivity {

    TextView txtresults;
    EditText editTextname;
    Button buttonFetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_databy_name);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtresults=findViewById(R.id.textView5);
        editTextname = findViewById(R.id.editTextName);
        buttonFetch = findViewById(R.id.buttonfetch);

        buttonFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchName = editTextname.getText().toString().trim();
                if (!searchName.isEmpty()) {
                    fetchData(searchName);
                } else {
                    Toast.makeText(getBaseContext(), "Please enter a medicine name", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void fetchData(String searchName) {
        MedicineHelper helper = new MedicineHelper(this,MedicineHelper.DATABASE_NAME,null,1);
        SQLiteDatabase database = helper.getReadableDatabase();

        String query = "SELECT date,time FROM "+MedicineHelper.MEDICINE_TABLE + " WHERE "+ MedicineHelper.col1 + "=?";
        String[] selectionArgs = { searchName };

        try(Cursor cursor = database.rawQuery(query,selectionArgs)){
            if (cursor.moveToFirst()){
                StringBuilder results = new StringBuilder();
                do {
                    String date = cursor.getString(0);
                    String time = cursor.getString(1);

                    results.append("Date: ").append(date).append(", Time: ").append(time).append("\n");
                }while (cursor.moveToNext());

                txtresults.setText(results.toString());
            }else {
                Toast.makeText(this, "No data found for the given medicine name", Toast.LENGTH_SHORT).show();
                txtresults.setText("No data available.");
            }
        }catch (Exception e){
            Toast.makeText(this, "An error occurred while fetching data", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        finally {
            if (database!=null && database.isOpen()){
                database.close();
            }
        }
    }
}