package com.example.vishal.saltnpepper;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class couponapp extends AppCompatActivity {

    EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_couponapp);
        e=(EditText)findViewById(R.id.coupon);


    }

    public void check(View view) {

        if(e.getText().toString().equals("ORDER50"))
        {
            Toast.makeText(getApplicationContext(),"coupon applied",Toast.LENGTH_SHORT).show();
            cartfrag cf=new cartfrag();
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.rl,cf);
            Button b;
            b=(Button)findViewById(R.id.button5);
            b.setVisibility(View.INVISIBLE);
            e.setVisibility(View.INVISIBLE);
            fragmentTransaction.commit();

        }

        else
        {
            Toast.makeText(getApplicationContext(),"Invalid coupon id",Toast.LENGTH_SHORT).show();
        }
    }
}
