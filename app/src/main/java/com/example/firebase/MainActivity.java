package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    Button button;
    EditText editText1;
    EditText editText2;
    TextView textView;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseDatabase = FirebaseDatabase.getInstance();

        button = findViewById(R.id.button);
        editText1 = findViewById(R.id.editTextTextPersonName2);
        editText2 = findViewById(R.id.editTextTextPersonName);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(v -> {
            user = new User();

            String login = editText1.getText().toString();
            String pass = editText2.getText().toString();

            user.setPassword(pass);
            user.setUserName(login);


            loginUser();

            registerNewUser();

            firebaseDatabase.getReference("lastLogin").setValue(login);
        });

        firebaseDatabase.getReference("lastLogin").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String lastLogin = snapshot.getValue(String.class);
                textView.setText(lastLogin);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void loginUser() {
        firebaseDatabase.getReference("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    User serverUser = childSnapshot.getValue(User.class);
                    onSignInSuccess();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void onSignInSuccess() {
    }

    private void registerNewUser() {
        firebaseDatabase.getReference("users").push().setValue(user);
    }

}