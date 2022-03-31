package com.example.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextTextPersonName;
    private EditText editTextTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
    }


    public void onClickCreateOrder(View view) {
        String name = editTextTextPersonName.getText().toString().trim();
        String password = editTextTextPassword.getText().toString().trim();
        if (!name.isEmpty() && !password.isEmpty()){
            Intent intent = new Intent(this, CreateOrderActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("password", password);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.toast_fill_fields, Toast.LENGTH_SHORT).show();
        }

    }
}