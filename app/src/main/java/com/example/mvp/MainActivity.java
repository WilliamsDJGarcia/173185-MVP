package com.example.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainActivityPrensent.View {

    private MainActivityPrensent prensent;
    private TextView MyTextView;
    private TextView Texto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyTextView = findViewById(R.id.name);
        Texto = findViewById(R.id.name2);
        // Texto = findViewById(R.id.dato);


        prensent = new  MainActivityPrensent(this);

        // prensent.update("Hola Mundo Android");

        MyTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                prensent.update(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void updateUserTextView(String Name){

        Texto.setText(Name);
    }
}
