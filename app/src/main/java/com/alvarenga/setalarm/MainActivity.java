package com.alvarenga.setalarm;

import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner spinner = (Spinner) findViewById(R.id.time_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hour_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        final EditText hourss = (EditText)findViewById(R.id.hour);
        final EditText minutess = (EditText)findViewById(R.id.minutes);

        final Button setButton = findViewById(R.id.send);
        final EditText desc = (EditText) findViewById(R.id.desc);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = Integer.parseInt(((EditText) findViewById(R.id.hour)).getText().toString());
                int minutes = Integer.parseInt(((EditText) findViewById(R.id.minutes)).getText().toString());
                String message = desc.getText().toString();
                final String spintext = spinner.getSelectedItem().toString();
                Editable hours = hourss.getText();
                Editable minute = minutess.getText();
                afterTextChangedHour(hours);
                afterTextChangedMinute(minute);
                if(spintext.matches("P.M")){
                    hour = hour + 12;
                    createAlarm(message, hour, minutes);
                }else{
                    createAlarm(message, hour, minutes);
                }

            }
        });
    }

    private void createAlarm(String message, int hour, int minutes) {
        Intent alarmintent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (alarmintent.resolveActivity(getPackageManager()) != null) {
            startActivity(alarmintent);
        }
    }
    public void afterTextChangedMinute(Editable s) {
        try {
            int val = Integer.parseInt(s.toString());
            if(val > 59) {
                s.replace(0, s.length(), "59", 0, 2);
            } else if(val < 0) {
                s.replace(0, s.length(), "0", 0, 1);
            }
        } catch (NumberFormatException ex) {
            // Do something
        }
    }
    public void afterTextChangedHour(Editable s) {
        try {
            int val = Integer.parseInt(s.toString());
            if(val > 12) {
                s.replace(0, s.length(), "12", 0, 2);
            } else if(val < 1) {
                s.replace(0, s.length(), "1", 0, 1);
            }
        } catch (NumberFormatException ex) {
            // Do something
        }
    }
}
