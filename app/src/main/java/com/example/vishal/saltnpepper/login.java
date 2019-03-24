package com.example.vishal.saltnpepper;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class login extends AppCompatActivity {

    private EditText phone;
    private Button btncon;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pb=findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
        phone=findViewById(R.id.txtphone);
        phone.addTextChangedListener(new TextWatcher() {

            // Before EditText text change.
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // This method is invoked after user input text in EditText.
            @Override
            public void afterTextChanged(Editable editable) {
                processButtonByTextLength();
            }
        });
    }

    private void processButtonByTextLength()
    {
        String inputText = phone.getText().toString();
        btncon=findViewById(R.id.btnpassword);
        if(inputText.length() ==10)
        {
            btncon.setText("CONTINUE");
            btncon.setEnabled(true);
        }else
        {
            btncon.setText("ENTER PHONE NUMBER");
            btncon.setEnabled(false);
        }
    }

    public void loginocreate(View view) {
        String inputText = phone.getText().toString();
        pb=findViewById(R.id.progressBar);
        phone.onEditorAction(EditorInfo.IME_ACTION_DONE);
        if(inputText.equals("8220345015"))
        {
            final Intent i1=new Intent(this,loginaccess.class);
            Bundle bundle=new Bundle();
            bundle.putString("numb",inputText);
            i1.putExtras(bundle);
            pb.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    pb.setVisibility(View.INVISIBLE);
                    startActivity(i1);
                }
            }, 3000);

        }

        else
        {
            final Intent i1=new Intent(this,signup.class);
            Bundle bundle=new Bundle();
            bundle.putString("numb",inputText);
            i1.putExtras(bundle);
            pb.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    startActivity(i1);
                }
            }, 3000);


        }

    }

    public void succes(View view) {


    }


    public void validate(View view) {
    }
}
