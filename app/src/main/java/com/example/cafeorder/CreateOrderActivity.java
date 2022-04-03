package com.example.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {

    private TextView textViewHello;
    private TextView textViewAdditions;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxLemon;
    private Spinner spinnerCoffeDrink;
    private Spinner spinnerTeaDrink;
    private String name;
    private String password;
    private String drink;
    private StringBuilder builderAdditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        textViewHello = findViewById(R.id.textViewHello);
        textViewAdditions = findViewById(R.id.textViewAdditions);
        checkBoxSugar = findViewById(R.id.checkBoxSugar);
        checkBoxMilk = findViewById(R.id.checkBoxMilk);
        checkBoxLemon = findViewById(R.id.checkBoxLemon);
        spinnerCoffeDrink = findViewById(R.id.spinnerCoffeDrink);
        spinnerTeaDrink = findViewById(R.id.spinnerTeaDrink);
        Intent intent = getIntent();
        if (intent.hasExtra("name") && intent.hasExtra("password")) {
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        } else {
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }
        textViewHello.setText(String.format(getString(R.string.hello_user), name));
        drink = getString(R.string.tea);
        textViewAdditions.setText(String.format(getString(R.string.additions), drink));    ;

    }

    public void onClickChooseDrink(View view) {
        RadioButton radioButton = (RadioButton) view;
        int radioButtonId = radioButton.getId();
        if (radioButtonId == R.id.radioButtonTea) {
            drink = getString(R.string.tea);
            spinnerCoffeDrink.setVisibility(View.INVISIBLE);
            spinnerTeaDrink.setVisibility(View.VISIBLE);
            checkBoxLemon.setVisibility(View.VISIBLE);
        } else if (radioButtonId == R.id.radioButtonCoffe) {
            drink = getString(R.string.coffe);
            spinnerCoffeDrink.setVisibility(View.VISIBLE);
            spinnerTeaDrink.setVisibility(View.INVISIBLE);
            checkBoxLemon.setVisibility(View.INVISIBLE);
        }
        textViewAdditions.setText(String.format(getString(R.string.additions), drink));

    }

    public void onClickSendOrder(View view) {

        builderAdditions.setLength(0);
        if (checkBoxSugar.isChecked()) {
            builderAdditions.append(getString(R.string.sugar)).append(" ");
        }
        if (checkBoxMilk.isChecked()) {
            builderAdditions.append(getString(R.string.milk)).append(" ");
        }
        if (checkBoxLemon.isChecked() && drink.equals(getString(R.string.tea))) {
            builderAdditions.append(getString(R.string.lemon)).append(" ");
        }
        String typeOfDrink;
        if (drink.equals(getString(R.string.tea))) {
            typeOfDrink = spinnerTeaDrink.getSelectedItem().toString();
        } else {
            typeOfDrink = spinnerCoffeDrink.getSelectedItem().toString();
        }
        String order = String.format(getString(R.string.order), name, password, drink, typeOfDrink);
        String additions;
        if (builderAdditions.length() > 0) {
            additions = getString(R.string.need_additions) + builderAdditions.toString();
        } else {
            additions = "";
        }
        String fullOrder = order + additions;
        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("fullOrder", fullOrder);
        startActivity(intent);
    }
}