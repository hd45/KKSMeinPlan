package com.example.kks_meinplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class anmelden_activity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anmelden);

        Button button = findViewById(R.id.anmelden);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainactivity();
            }
        });
    }


    public void openMainactivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
