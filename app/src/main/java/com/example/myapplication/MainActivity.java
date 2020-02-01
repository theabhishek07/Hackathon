//Second page name contact number dob
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity{

    EditText name1, contact1;
    TextView name, contact,dob;
    Button register;
    CircleImageView img;
    String lang;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView mDisplayDate;
    String date;
    String pickDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        lang = intent.getStringExtra("lang");
        setAppLocale(lang);
        setContentView(R.layout.activity_main);


        name1=findViewById(R.id.name1);
        contact1=findViewById(R.id.number1);
        name=findViewById(R.id.name);
        contact=findViewById(R.id.number);
        dob=findViewById(R.id.dob);
        register=findViewById(R.id.register_btn);
        img=findViewById(R.id.image);
        date = null;

        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        //addListenerOnButton();


        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                pickDate = dialog.toString();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(date == null)
                    Toast.makeText(getApplicationContext(), "Pick a date", Toast.LENGTH_SHORT).show();
                if(name1.getText().toString().length() == 0)
                    Toast.makeText(getApplicationContext(), "Enter name", Toast.LENGTH_SHORT).show();
                if(contact1.getText().toString().length() == 0)
                    Toast.makeText(getApplicationContext(), "Enter contact", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                    String name = name1.getText().toString();
                    String contact = contact1.getText().toString();
                    String date1 = date;
                    intent.putExtra("name", name);
                    intent.putExtra("contact", contact);
                    intent.putExtra("date", date);
                    intent.putExtra("lang", lang);
                    startActivity(intent);
                }
            }
        });



    }

    private void setAppLocale(String localeCode)
    {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            conf.setLocale(new Locale(localeCode.toLowerCase()));
        }
        else
        {
            conf.locale = new Locale(localeCode.toLowerCase());
        }
        res.updateConfiguration(conf, dm);
    }

}