package com.example.ofek.contacts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Classes.Contact;
import Classes.Helper;

public class MainActivity extends AppCompatActivity {
    SharedPreferences settings;
    ImageButton addBtn;
    ListView contactsLV;
    static ArrayList<String> contactsNames=new ArrayList<>();
    static ArrayList<Contact> contacts=new ArrayList<>();
    static ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings=getPreferences(MODE_PRIVATE);
        addSavedContacts();
        removePreferences();
        setAddBtn();
        setContactsLV();
    }

    private void removePreferences() {
        SharedPreferences.Editor editor=settings.edit();
        for (int i=0;i<settings.getInt("SIZE",0);i++){
            editor.remove(""+i);
            editor.commit();
        }
    }


    public void setContactsLV() {
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contactsNames);
        contactsLV= (ListView) findViewById(R.id.contactsLV);
        contactsLV.setAdapter(adapter);
        contactsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(MainActivity.this,ShowContact.class);
                String text=((TextView)view).getText().toString();
                String name=text.substring(0,text.indexOf(' '));
                String surname=text.substring(text.indexOf(' ')+1,text.length());
                intent.putExtra("Contact",findContactByName(name,surname));
                startActivity(intent);
            }
        });
    }

    private void addSavedContacts(){
        for (int i=0;i<settings.getInt("SIZE",0);i++){
            Contact contact=Helper.extractUserFromString(settings.getString(""+i,""));
            contacts.add(contact);
            contactsNames.add(Helper.getTitleName(contact));
        }

    }
    private void setAddBtn() {
        addBtn= (ImageButton) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),AddContactActivity.class);
                startActivityForResult(intent,ADD_CONTACT_RESULT);
            }
        });
    }
    public static int ADD_CONTACT_RESULT=11234;
    public static int CONTACT_EDITED_RESULT=1111;
    public static int RESULT_EDITED=1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==ADD_CONTACT_RESULT&&resultCode==RESULT_OK){
            Contact contact = (Contact) data.getExtras().get("Contact");
            contacts.add(contact);
            contactsNames.add(Helper.getTitleName(contact));
            adapter.notifyDataSetChanged();
        }
        if (requestCode==CONTACT_EDITED_RESULT&&resultCode==RESULT_EDITED){
            adapter.notifyDataSetChanged();
        }
    }
    private Contact findContactByName(String name,String surname){
        for (Contact contact:contacts){
            if (name.equals(contact.getName())&&surname.equals(contact.getSurname()))
                return contact;
        }
        return null;
    }
    public static void updateContact(Contact before,Contact after){
        for(Contact contact:contacts){
            if (contact.equals(before)){
                contact.setName(after.getName());
                contact.setSurname(after.getSurname());
                contact.setPhone(after.getPhone());
                break;
            }
        }
        for (int i=0;i<contactsNames.size();i++){
            if (contactsNames.get(i).equals(Helper.getTitleName(before))){
                contactsNames.set(i,Helper.getTitleName(after));
                adapter.notifyDataSetChanged();
                return;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        removePreferences();
        SharedPreferences.Editor editor=settings.edit();
        int i=0;
        for (Contact contact:contacts){
            editor.putString(""+i,Helper.generateSaveableString(contact));
            editor.commit();
            i++;
        }
        editor.putInt("SIZE",i);
        editor.commit();
        super.onDestroy();

    }
}
