package com.savonia.networksettings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class BasicsActivity extends AppCompatActivity {

    static final String[] TYPES= new String[]{"WiFI", "LAN", "Kotinetti", "Työpaikka", "mobiilidata"}; //kovakoodattu lista
    private String selectedText;
    private EditText name;
    private Button nappula;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basics);

        name = (EditText)findViewById(R.id.idName);
        Spinner spinner = (Spinner)findViewById(R.id.spinnerType);

        //Spinneriin sisältöä
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, TYPES));
        nappula = (Button)findViewById(R.id.buttonBack);

        //Back to main menu - tulee esille, kun nimi kirjoitettu
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Ei tarvita
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateButtonState();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Ei tarvita
            }
        });

        // Kuuntelija spinneriin
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedText = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //ei tarvita
            }
        });

        /*
        //TEHTÄVÄ 14. Kun painetaan "back to main menu, niin palataan takaisin mainmenuun

        nappula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            finish();
            }
        });*/

        //TEHTÄVÄ 17. Kun painetaan "back to main menu, niin palataan takaisin mainmenuun ja viedään sinne kirjatut tiedot
        nappula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                String basicInfo = "Name of the network: " + name.getText() +"\n" + "Type: " +  selectedText;
                intent.putExtra("basic_info", basicInfo);

                // Aseta tulos Intentiin ja lopeta BasicsActivity
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
    private void updateButtonState() { //Nappula esille, kun kirjoitettu nimi
        boolean isNameEmpty = name.getText().toString().isEmpty();

        if (!isNameEmpty) {
            nappula.setEnabled(true);
        } else {
            nappula.setEnabled(false);
        }
    }
}