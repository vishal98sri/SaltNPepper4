package com.example.vishal.saltnpepper;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GPS extends AppCompatActivity {

    private EditText area;
    private Button btncon;
    ProgressBar pb;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        area=(EditText) findViewById(R.id.addr);
        btncon=(Button)findViewById(R.id.button4);
        pb=(ProgressBar)findViewById(R.id.progressBar3);
        tv=(TextView)findViewById(R.id.textView23);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
               pb.setVisibility(View.INVISIBLE);
               tv.setText("");
               area.setText("Ettimadai,Amrita Vishwa Vidyapeetham,Coimbatore");
               btncon.setEnabled(true);
            }
        }, 3000);


    }

    public void dash(View view) {

        Intent i=new Intent(this,dashboard.class);
        Bundle bundle=new Bundle();
        bundle.putString("location","Ettimadai");
        i.putExtras(bundle);
        startActivity(i);
    }
}
