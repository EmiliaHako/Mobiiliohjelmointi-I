
package com.savonia.networksettings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Button back = (Button)findViewById(R.id.buttonBackMain);
        TextView tvBasicsSummary = findViewById(R.id.tvBasicsSummary);
        TextView tvEncryptionSummary = findViewById(R.id.tvEncryptionSummary);

        // otetaan intentin mukana tullut data vastaan
        String basicsInfo = getIntent().getStringExtra("basics_info");
        String encryptionInfo = getIntent().getStringExtra("encryption_info");

        // Näytetään data textvievissä
        tvBasicsSummary.setText("Basics Info: \n" + basicsInfo);
        tvEncryptionSummary.setText("Encryption Info: \n " + encryptionInfo);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}