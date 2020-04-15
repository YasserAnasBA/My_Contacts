package com.example.yasser.everyday;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateActivity extends AppCompatActivity {
    CircleImageView imgbtn;
    ImageView dlt;
    byte[] dbimage;
    Db db;
    EditText edtname,edtphone;
    Button confirmbtn;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        dlt = findViewById(R.id.reddelete_img);
        imgbtn = findViewById(R.id.updateimg_btn);
       edtname = findViewById(R.id.editname_txtedt);
       edtphone = findViewById(R.id.editphone_txtedt);
       confirmbtn = findViewById(R.id.update_btn);

        id = getIntent().getIntExtra("id",0);
        db = new Db(this);
        Contact c =  db.getContactById(id);

        dlt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               showAlert();
           }
       });

       imgbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Gallery();
           }
       });

       confirmbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String name = edtname.getText().toString();
               int num = Integer.parseInt(edtphone.getText().toString());
               Contact c = new Contact(id,name,num,dbimage);
               db.updateContact(c);
               Toast.makeText(UpdateActivity.this,"Contact Updated",Toast.LENGTH_SHORT).show();
               finish();

           }
       });

       edtname.setText(c.getName());
       edtphone.setText(String.valueOf(c.getNum()));
       byte[] b = c.getImage();
       Bitmap bitmap = BitmapFactory.decodeByteArray(b , 0, b.length);
       imgbtn.setImageBitmap(bitmap);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.delete_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item_delete:


            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlert() {

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("Conformation ")
                .setMessage("are yoo sure ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(UpdateActivity.this,"Contact deleted",Toast.LENGTH_SHORT).show();
                        db.DeleteContact(id);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = dlg.create();
        dialog.show();


    }

    public void Gallery(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,100);
    }
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
