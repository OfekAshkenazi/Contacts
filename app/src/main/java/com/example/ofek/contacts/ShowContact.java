package com.example.ofek.contacts;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import Classes.Contact;

public class ShowContact extends AppCompatActivity {
    TextView nameTV, surnameTV, phoneTV;
    EditText nameET, surnameET, phoneET;
    Button doneBtn, editBtn;
    Contact contactOriginal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);
        setViews();
        contactOriginal = (Contact) getIntent().getExtras().get("Contact");
        showContact(contactOriginal);
        ButtonsListeners();
    }

    private void ButtonsListeners() {
        phoneTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getVisibility() == View.VISIBLE) {
                    AlertDialog dialog = new AlertDialog.Builder(ShowContact.this).create();
                    dialog.setTitle("Choose Action");
                    dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Call", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:" + phoneTV.getText().toString()));
                            startActivity(intent);

                        }
                    });
                    dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Message", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent=new Intent(Intent.ACTION_SENDTO);
                            intent.setData(Uri.parse("sms:"+phoneTV.getText().toString()));
                            startActivity(intent);
                        }
                    });
                    dialog.show();
                }
            }
        });
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameET.setVisibility(View.GONE);
                surnameET.setVisibility(View.GONE);
                phoneET.setVisibility(View.GONE);
                Contact contact=new Contact(nameET.getText().toString(),surnameET.getText().toString(),phoneET.getText().toString());
                MainActivity.updateContact(contactOriginal,contact);
                nameTV.setVisibility(View.VISIBLE);
                surnameTV.setVisibility(View.VISIBLE);
                phoneTV.setVisibility(View.VISIBLE);
                showContact(contact);
                view.setVisibility(View.GONE);
                editBtn.setVisibility(View.VISIBLE);
            }
        });
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameTV.setVisibility(View.GONE);
                surnameTV.setVisibility(View.GONE);
                phoneTV.setVisibility(View.GONE);
                editContact();
                nameET.setVisibility(View.VISIBLE);
                surnameET.setVisibility(View.VISIBLE);
                phoneET.setVisibility(View.VISIBLE);
                view.setVisibility(View.GONE);
                doneBtn.setVisibility(View.VISIBLE);
            }
        });
    }
    private void showContact(Contact contact){
        nameTV.setText(contact.getName());
        surnameTV.setText(contact.getSurname());
        phoneTV.setText(contact.getPhone());
    }
    private void editContact(){
        nameET.setText(nameTV.getText().toString());
        surnameET.setText(surnameTV.getText().toString());
        phoneET.setText(phoneTV.getText().toString());
    }
    private void setViews(){
        nameET= (EditText) findViewById(R.id.nameET1);
        surnameET= (EditText) findViewById(R.id.surnameET1);
        phoneET= (EditText) findViewById(R.id.phoneET1);
        nameTV= (TextView) findViewById(R.id.nameTV);
        surnameTV= (TextView) findViewById(R.id.surnameTV);
        phoneTV= (TextView) findViewById(R.id.phoneTV);
        doneBtn=(Button) findViewById(R.id.doneBtn);
        editBtn=(Button) findViewById(R.id.editBtn);
    }
}
