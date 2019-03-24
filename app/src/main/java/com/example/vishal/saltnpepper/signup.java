package com.example.vishal.saltnpepper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signup extends AppCompatActivity {

    EditText phone;
    EditText email;
    EditText name;
    EditText pass;
    Button b;
    ProgressBar pb;
    ImageView i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        phone=findViewById(R.id.txtnumb);
        Intent intent = getIntent();

        Bundle b = getIntent().getExtras();

        String str2 = b.getString("numb");

        phone.setText(str2);
        phone.setEnabled(false);

        email=findViewById(R.id.txtemail);
        name=findViewById(R.id.txtname);
        pass=findViewById(R.id.txtpass);

        email.addTextChangedListener(new TextWatcher() {

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
                processButtonByemail();
            }
        });

        name.addTextChangedListener(new TextWatcher() {

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
                processButtonByname();
            }
        });

        pass.addTextChangedListener(new TextWatcher() {

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
                processButtonBypass();
            }
        });
    }

    private void processButtonBypass() {
        String inputText = pass.getText().toString();
        String inputText2 = name.getText().toString();
        String inputText3 = email.getText().toString();
        b=findViewById(R.id.btnsign);
        i=findViewById(R.id.imgtick4);
        if(inputText.length()>=8)
        {
            i.setVisibility(View.VISIBLE);
        }

        if(inputText.length()<8)
        {
            i.setVisibility(View.INVISIBLE);
        }

        if(inputText.length()>=8&&!inputText2.isEmpty()&&isEmailValid(inputText3))
        {
            b.setText("SIGN UP");

            b.setEnabled(true);
        }
        else if(!isEmailValid(inputText3))
        {
            b.setText("ENTER EMAIL");
            b.setEnabled(false);
        }
        else if(isEmailValid(inputText3)&&inputText2.isEmpty())
        {
            b.setText("ENTER NAME");
            b.setEnabled(false);
        }
        else if(inputText.length()<8&&!inputText2.isEmpty()&&isEmailValid(inputText3))
        {
            b.setText("ENTER PASSWORD");
            b.setEnabled(false);
        }
    }

    private void processButtonByname() {
        String inputText = name.getText().toString();
        String inputText2 = email.getText().toString();
        String inputText3 = pass.getText().toString();
        b=findViewById(R.id.btnsign);
        i=findViewById(R.id.imgtick3);
        if(inputText.isEmpty())
        {
            i.setVisibility(View.INVISIBLE);
        }

        if(!inputText.isEmpty())
        {
            i.setVisibility(View.VISIBLE);
        }
        if(inputText.isEmpty()&&isEmailValid(inputText2))
        {
            b.setText("ENTER NAME");
            b.setEnabled(false);
        }
        else if(!inputText.isEmpty()&&inputText3.length()<8)
        {
            b.setText("ENTER PASSWORD");
            b.setEnabled(false);
        }
        else if(!inputText.isEmpty()&&inputText3.length()>=8&&isEmailValid(inputText2))
        {
            b.setText("SIGN UP");
            b.setEnabled(true);
        }
    }

    private void processButtonByemail() {

        String inputText = email.getText().toString();
        b=findViewById(R.id.btnsign);
        i=findViewById(R.id.imgtick2);
        if(isEmailValid(inputText))
        {
            b.setText("ENTER NAME");
            i.setVisibility(View.VISIBLE);

        }else
        {
            b.setText("ENTER EMAIL");
            i.setVisibility(View.INVISIBLE);
            b.setEnabled(false);
        }
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void signing(View view) {

        pb=findViewById(R.id.progressBar2);
        pb.setVisibility(View.VISIBLE);
        Toast.makeText(getApplicationContext(),"Account created",Toast.LENGTH_LONG).show();
    }
}
