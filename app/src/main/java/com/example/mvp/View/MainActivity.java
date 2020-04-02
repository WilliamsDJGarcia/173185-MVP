package com.example.mvp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvp.Presenter.MainActivityPrensent;
import com.example.mvp.R;

public class MainActivity extends AppCompatActivity implements MainActivityPrensent.View {

    private MainActivityPrensent prensent;
    private EditText user;
    private EditText pass;
    private TextView lin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        lin = findViewById(R.id.lin);

        prensent = new  MainActivityPrensent(this);

        lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prensent.update(user.getText().toString(),pass.getText().toString());
            }
        });

    }

    public void updateUserTextView(String token){
        System.out.println("Token "+token);

        if (token!=null){
            Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,HomeActivity.class); //crear el appcomp hacia donde irá
            intent.putExtra("Token",token);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), "An ocurred a error, but not is you, not worry ;)", Toast.LENGTH_SHORT).show();
        }

    }

}
