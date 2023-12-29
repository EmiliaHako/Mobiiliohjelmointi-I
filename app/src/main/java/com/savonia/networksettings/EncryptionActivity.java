package com.savonia.networksettings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EncryptionActivity extends AppCompatActivity {

    private ArrayList<String> selectedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);

        //ListViewiin asiat esille ja voi valita useita kohteita
        ListView encList = (ListView) findViewById(R.id.encryprionList);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.encryption, android.R.layout.simple_list_item_multiple_choice);

        encList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        encList.setAdapter(adapter);

        Button nappula = (Button) findViewById(R.id.buttonClose);

        //Tehtävä 16. Voit valita useita kohteita
        encList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                selectedItems.clear();
                for (int j = 0; j < adapterView.getCount(); j++) {
                    if (encList.isItemChecked(j)) {
                        selectedItems.add((String) adapterView.getItemAtPosition(j));
                    }
                }
                // Tarkista valittujen kohteiden määrä
                int selectedItems = encList.getCheckedItemCount();

                // Näytä "back to main menu"-nappi, jos ainakin yksi kohde on valittu, muuten piilota se
                if (selectedItems > 0) {
                    nappula.setVisibility(View.VISIBLE);
                } else {
                    nappula.setVisibility(View.INVISIBLE);
                }
            }
        });

     /*   //TEHTÄVÄ 16. paluu takaisin mainmenuun
        nappula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/

        //TEHTÄVÄ 17. paluu takaisin mainmenuun tiedot mukana
        nappula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                String EncryptionInfo = selectedItems.toString();
                intent.putExtra("encryption_info", EncryptionInfo);

                // Aseta tulos Intentiin ja lopeta BasicsActivity
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}