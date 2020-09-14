package com.example.teste_android.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.teste_android.Model.Masks;
import com.example.teste_android.R;



public class MainActivity extends AppCompatActivity {

    EditText name, email, phone;
    Button send;
    private AwesomeValidation isvalidregister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name=findViewById(R.id.text2);
        email=findViewById(R.id.text4);
        phone=findViewById(R.id.text6);
        phone.addTextChangedListener(Masks.insert(Masks.PHONE_MASK,phone));
        send=findViewById(R.id.button7);
        sendButton();
        isvalidregister = new AwesomeValidation(ValidationStyle.BASIC);
        isValidEmail();


    }
    private void isValidEmail() {
        isvalidregister.addValidation(this, R.id.text4, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        isvalidregister.addValidation(this, R.id.text2, RegexTemplate.NOT_EMPTY, R.string.name_empty);
        isvalidregister.addValidation(this, R.id.text6, RegexTemplate.NOT_EMPTY, R.string.phone_empty);
    }
    public void sendButton() {
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitform();
            }
        });
    }



    private void submitform(){
            if (isvalidregister.validate()){

            Intent i = new Intent(MainActivity.this, InvestmentsActivity.class);
            startActivity(i);

        }
    }
}