package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    Button button;
    EditText editText1;
    EditText editText2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = new User();
        firebaseDatabase = FirebaseDatabase.getInstance();

        button = findViewById(R.id.button);
        editText1 = findViewById(R.id.editTextTextPersonName2);
        editText2 = findViewById(R.id.editTextTextPersonName);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(v -> {
            String login = editText1.getText().toString();
            firebaseDatabase.getReference("lastvvvLogin").setValue(login);
            System.out.println(login);
        });

    }

}