package com.mcuhq.simplebluetooth;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button OkeyBtn = findViewById(R.id.Okey);
        OkeyBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                TextView EnterName = findViewById(R.id.EnterName);
                TextView EnterAge = findViewById(R.id.EnterAge);
                TextView EnterSize = findViewById(R.id.EnterSize);
                TextView EnterWeight = findViewById(R.id.EnterWeight);

                RadioGroup radioG = findViewById(R.id.GenderGroup);
                int choosenRadio = radioG.getCheckedRadioButtonId();
                RadioButton choosenGender = findViewById(choosenRadio);

                String NAME = EnterName.getText().toString();
                String AGE = EnterAge.getText().toString();
                String SIZE = EnterSize.getText().toString();
                String WEIGHT = EnterWeight.getText().toString();
                String SEX = choosenGender.getText().toString();

                SharedPreferences userDetails = getSharedPreferences("Bracelet", MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = userDetails.edit();

                AlertDialog alertDialog = new AlertDialog.Builder(Login.this).create();
                alertDialog.setTitle(getString(R.string.app_name));
                alertDialog.setButton("Tamam", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        /* Do nothing */
                    }
                });

                if (TextUtils.isEmpty(NAME) || TextUtils.isEmpty(AGE) || TextUtils.isEmpty(SIZE) || TextUtils.isEmpty(WEIGHT) || TextUtils.isEmpty(SEX)) {
                    alertDialog.setMessage("Her alan doldurulmalıdır.");
                    alertDialog.show();
                } else if (!NAME.contains(" ")) {
                    alertDialog.setMessage("İsminiz \"Ad Soyad\" şeklinde tam olmalıdır.");
                    alertDialog.show();
                } else if (NAME.length() <= 4) {
                    alertDialog.setMessage("İsim daha uzun olmalıdır.");
                    alertDialog.show();
                } else {

                    //System.out.println("------->" + NAME + AGE + SIZE + WEIGHT + SEX);
                    prefEditor.putString("NAME", NAME);
                    prefEditor.putString("AGE", AGE);
                    prefEditor.putString("SIZE", SIZE);
                    prefEditor.putString("WEIGHT", WEIGHT);
                    prefEditor.putString("SEX", SEX);

                    prefEditor.apply();

                    //saveDatas(new HumanBasicInfos());

                    startActivity(new Intent(Login.this, MainActivity.class));
                    finish();
                }

            }
        });
    }

}
