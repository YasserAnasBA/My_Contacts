package com.example.yasser.everyday;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class Db extends SQLiteOpenHelper{

    public Db(Context context) {
        super(context, "MyContacts.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String sql = "create table contacts(id integer primary key,name varchar(30),phone integer,image blob)";
    Log.d("create",sql);
    db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "drop table contacts";
        db.execSQL(sql);
        onCreate(db);

    }

    public void addContacts(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues v = new ContentValues();

        v.put("name",contact.getName());
        v.put("phone",contact.getNum());
        v.put("image",contact.getImage());

        db.insert("contacts",null,v);
    }

    public ArrayList<Contact> getContacts(){
        ArrayList<Contact> arr = new ArrayList<>();

        String sql = "select * from contacts";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst())
        {
            do{
                int id_contact = cursor.getInt(0);
                String name = cursor.getString(1);
                int phone = cursor.getInt(2);
                byte[] image = cursor.getBlob(3);

                Contact c = new Contact(id_contact,name, phone,image);
                arr.add(c);
            }while(cursor.moveToNext());
        }

        return arr;
    }
    public Contact getContactById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from contacts where id="+id+"";
        Cursor cursor = db.rawQuery(sql,null);
        Contact c = null;
        if(cursor.moveToFirst()) {
            int id_contact = cursor.getInt(0);
            String name = cursor.getString(1);
            int phone = cursor.getInt(2);
            byte[] image = cursor.getBlob(3);

            c = new Contact(id_contact,name, phone,image);
        }
        return c;
    }

    public void updateContact(Contact contact)
    {
       SQLiteDatabase DB = this.getWritableDatabase();
       ContentValues v = new ContentValues();
       v.put("name",contact.getName());
       v.put("phone",contact.getNum());
       v.put("image",contact.getImage());
       DB.update("contacts",v,"id=?",new String[]{String.valueOf(contact.getId())} );
    }

    public void DeleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("contacts","id=?",new String[]{String.valueOf(id)});

    }
}
