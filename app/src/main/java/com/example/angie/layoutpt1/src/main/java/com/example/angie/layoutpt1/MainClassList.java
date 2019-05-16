package com.example.angie.layoutpt1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainClassList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_class_list);
        final Switch swDegreeCert = findViewById(R.id.swDegreeCert);
        final Spinner spDegree = findViewById(R.id.spnDegree);
        final Spinner spCert = findViewById(R.id.spnCert);
        final TextView txtCert = findViewById(R.id.lblCert);
        final TextView txtDegree = findViewById(R.id.lblDegree);
        final Button btnNext = findViewById(R.id.btnNext);

        final EditText firstName = findViewById(R.id.firstName);
        final EditText lastName = findViewById(R.id.lastName);
        final EditText phone = findViewById(R.id.phoneNumber);

        final Spinner spMonth = findViewById(R.id.months);
        final EditText txtDay = findViewById(R.id.day);
        final EditText txtYear = findViewById(R.id.year);

        swDegreeCert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    spDegree.setVisibility(View.VISIBLE);
                    txtDegree.setVisibility(View.VISIBLE);
                    spCert.setVisibility(View.GONE);
                    txtCert.setVisibility(View.GONE);
                }
                else
                {
                    spDegree.setVisibility(View.GONE);
                    txtDegree.setVisibility(View.GONE);
                    spCert.setVisibility(View.VISIBLE);
                    txtCert.setVisibility(View.VISIBLE);
                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(checkData())
                {
                    String DoB = "";
                    DoB = spMonth.getSelectedItem().toString() + "/" + txtDay.getText().toString()
                    + "/" + txtYear.getText().toString() ;

                    Intent nextScreen = new Intent(MainClassList.this, ChooseClass.class);
                    nextScreen.putExtra("FirstName", firstName.getText().toString());
                    nextScreen.putExtra("LastName", lastName.getText().toString());
                    nextScreen.putExtra("Phone", phone.getText().toString());
                    nextScreen.putExtra("BirthDate", DoB);

                    if(spDegree.getVisibility()== View.VISIBLE)
                    {
                        nextScreen.putExtra("isDegreeCert", "Degree");
                        nextScreen.putExtra("degreeCert", spDegree.getSelectedItem().toString());
                    }
                    else
                    {
                        nextScreen.putExtra("isDegreeCert", "Certificate");
                        nextScreen.putExtra("degreeCert", spCert.getSelectedItem().toString());
                    }
                    startActivity(nextScreen);
                }
            }
        });
    }
    private boolean checkData()
    {
        final EditText firstName = findViewById(R.id.firstName);
        final EditText lastName = findViewById(R.id.lastName);
        final EditText phone = findViewById(R.id.phoneNumber);
        final EditText txtDay = findViewById(R.id.day);
        final EditText txtYear = findViewById(R.id.year);

        if(firstName.getText().toString().isEmpty())
        {
            firstName.setError("Invalid First Name");
            firstName.requestFocus();
            return false;
        }
        if(lastName.getText().toString().isEmpty())
        {
            lastName.setError("Invalid Last Name");
            lastName.requestFocus();
            return false;
        }
        if(phone.getText().toString().isEmpty())
        {
            phone.setError("Invalid Phone Number");
            phone.requestFocus();
            return false;
        }
        if(txtDay.getText().toString().isEmpty())
        {
            txtDay.setError("Invalid Phone Day");
            txtDay.requestFocus();
            return false;
        }
        if(txtYear.getText().toString().isEmpty())
        {
            txtYear.setError("Invalid Phone Year");
            txtYear.requestFocus();
            return false;
        }
        return true;
    }
}
