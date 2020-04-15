package com.example.yasser.everyday;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;

import javax.xml.transform.Result;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddActivity extends AppCompatActivity {
    EditText addname , addphone ;
    CircleImageView imgbtn;
    Button addbtn;
    byte[] dbimage;
    Db DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        setContentView(R.layout.activity_add);
        addname = findViewById(R.id.name_txtedt);
        addphone = findViewById(R.id.phone_txtedt);
        addbtn = findViewById(R.id.create_btn);
        imgbtn = findViewById(R.id.image_imgbtn);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gallery();
            }
        });

        DB = new Db(this);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = addname.getText().toString();
                int phone = Integer.parseInt(addphone.getText().toString());
                Contact c = new Contact(name,phone,dbimage);
                DB.addContacts(c);
                Toast.makeText(AddActivity.this,"Contact Saved",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
    public void Gallery(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
      if(resultCode == RESULT_OK && requestCode ==100 ){
          Uri uri = data.getData();
          try {
              InputStream inputStream = getContentResolver().openInputStream(uri);
              Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
              imgbtn.setImageBitmap(decodeStream);
              dbimage = getByte(decodeStream);
          }catch (Exception e)
          {
              Log.e("e",e.getMessage());
          }
      }
    }

    public static byte[] getByte(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream);
        return  stream.toByteArray();
    }
}
