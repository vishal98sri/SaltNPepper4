package com.example.vishal.saltnpepper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class delivloc extends AppCompatActivity {

    EditText loc;
    String str2,str1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivloc);

        loc=findViewById(R.id.editText);
        Intent intent = getIntent();

        Bundle b = getIntent().getExtras();

      str2 = b.getString("location");


        str1 = b.getString("numb");

        loc.setText(str2);
        loc.setEnabled(false);


    }

    public void addmore(View view) {

        Intent i1=new Intent(this,moredetail.class);
        Bundle bundle=new Bundle();
        bundle.putString("location",str2);
        bundle.putString("numb",str1);
        i1.putExtras(bundle);
        startActivity(i1);
    }


    public void conf(View view) {

        Intent i1=new Intent(this,dashboard.class);
        Bundle bundle=new Bundle();
        bundle.putString("location",str2);
        bundle.putString("numb",str1);
        i1.putExtras(bundle);
        startActivity(i1);
    }
}
