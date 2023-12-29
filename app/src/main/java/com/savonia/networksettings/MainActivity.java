package com.savonia.networksettings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_BASICS = 1;
    private static final int REQUEST_CODE_ENCRYPTION = 2;
    private String basicInfo = "";
    private String encryptionInfo = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Aloitus sivulle näkyviin listview
        ListView startList = (ListView) findViewById(R.id.startList);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.aloitus, R.layout.custom_layout); //TEHTÄVÄ 15, CUSTOM TEKSTIT!! Muotoilut on omassa custom_layout tiedostossa, jota tässä kutsutaan
        startList.setAdapter(adapter);

        //eventhandler, kun klikataan jotain listan kohdetta
        startList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    openBasics();
                } else if (position == 1) {
                    openEncryption();
                } else openSummary();
            }
        });
    }

    //Tästä avautuu BasicsActivity
    private void openBasics() {
        Intent intent = new Intent(getApplicationContext(), BasicsActivity.class);
        startActivityForResult(intent, REQUEST_CODE_BASICS);
    }

    //Tästä avautuu EncryptionActivity
    private void openEncryption() {
        Intent intent = new Intent(getApplicationContext(), EncryptionActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ENCRYPTION);
    }


    //Kun palataan BasicActiviltyä tai EncryptionActivityltä
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_BASICS && resultCode == RESULT_OK) {
            basicInfo = data.getStringExtra("basic_info");
        } else if (requestCode == REQUEST_CODE_ENCRYPTION && resultCode == RESULT_OK) {
            encryptionInfo = data.getStringExtra("encryption_info");
        }
    }

    //Tästä avautuu SummaryActivity, jonne menee palautuneet tiedot.
    private void openSummary() {
        Intent intent = new Intent(getApplicationContext(), SummaryActivity.class);
        intent.putExtra("basics_info", basicInfo);
        intent.putExtra("encryption_info", encryptionInfo);
        startActivity(intent);
    }
}