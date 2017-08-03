package com.example.ofek.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Classes.Contact;

public class AddContactActivity extends AppCompatActivity {
    EditText nameET,surnameET,phoneET;
    Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        nameET= (EditText) findViewById(R.id.nameET);
        surnameET= (EditText) findViewById(R.id.surnameET);
        phoneET= (EditText) findViewById(R.id.phoneET);
        addBtn=(Button) findViewById(R.id.addBtn);
        setAddBtnListener();
    }
    public void setAddBtnListener() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=nameET.getText().toString();
                String surname=surnameET.getText().toString();
                String phone=phoneET.getText().toString();
                Contact contact=new Contact(name,surname,phone);
                Intent intent=getIntent();
                intent.putExtra("Contact",contact);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
