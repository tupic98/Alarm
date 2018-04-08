package com.alvarenga.setalarm;

import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button setButton = findViewById(R.id.send);
        final EditText desc = (EditText) findViewById(R.id.desc);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = Integer.parseInt(((EditText) findViewById(R.id.hour)).getText().toString());
                int minutes = Integer.parseInt(((EditText) findViewById(R.id.minutes)).getText().toString());
                String message = desc.getText().toString();
                createAlarm(message, hour, minutes);
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
}
