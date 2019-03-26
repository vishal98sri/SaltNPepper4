package com.example.vishal.saltnpepper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginaccess extends AppCompatActivity {

    private Button btncon;
    private EditText phone;
    private EditText passwd;
    String str2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginaccess);
        phone=findViewById(R.id.txtphone);
        Intent intent = getIntent();

        Bundle b = getIntent().getExtras();

         str2 = b.getString("numb");

        phone.setText(str2);
        phone.setEnabled(false);

        passwd=findViewById(R.id.txtpasswd);

        passwd.addTextChangedListener(new TextWatcher() {

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

    private void processButtonByTextLength() {

        String inputText = passwd.getText().toString();
        btncon=findViewById(R.id.btnpassword);
        if(inputText.length() >=8)
        {
            btncon.setText("LOGIN");
            btncon.setEnabled(true);
        }else
        {
            btncon.setText("ENTER PASSWORD");
            btncon.setEnabled(false);
        }
    }

    public void validate(View view) {

        if(passwd.getText().toString().equals("vishal11"))
        {
            Intent i=new Intent(this,location.class);
            Bundle bundle=new Bundle();
            bundle.putString("numb",str2);
            i.putExtras(bundle);
            startActivity(i);
        }
    }

}
