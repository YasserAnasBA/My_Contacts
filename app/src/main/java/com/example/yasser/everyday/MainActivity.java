package com.example.yasser.everyday;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private BottomSheetBehavior bottomSheetBehavior;
    ListView list;
    TextView numtxt,enternum;
    ImageButton backspacebtn,removebtn,btncall,btnmessage;
    Db DB;
    CheckBox chk ;
    int item_id;
    static int count = 0;
    private FloatingActionButton fab , fab2 , fab3;
    Button btnstar,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnhash;
    private int REQUEST_CALL_PERMISSION = 1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btncall = findViewById(R.id.btncall);
        btnmessage = findViewById(R.id.btnmessage);
        backspacebtn = findViewById(R.id.imgbtn_backspace);
        removebtn = findViewById(R.id.imgbtn_remove);
        btnstar = findViewById(R.id.btnstar);
        btn1 = findViewById(R.id.btnone);
        btn2 = findViewById(R.id.btntwo);
        btn3 = findViewById(R.id.btnthree);
        btn4 = findViewById(R.id.btnfour);
        btn5 = findViewById(R.id.btnfive);
        btn6 = findViewById(R.id.btnsix);
        btn7 = findViewById(R.id.btnseven);
        btn8 = findViewById(R.id.btneight);
        btn9 = findViewById(R.id.btnnine);
        btn0 = findViewById(R.id.btnzero);
        btnhash = findViewById(R.id.btnhash);
        enternum = findViewById(R.id.enternumber_txt);

        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        requestcallpermission();
            }
                else
                {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    String s = enternum.getText().toString();
                    s = s.replace("#","%23");
                    callIntent.setData(Uri.parse("tel:"+s));
                    startActivity(callIntent);
                }

            }
        });

        btnmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , MessageActivity.class);
                intent.putExtra("number",enternum.getText().toString());
                Toast.makeText(MainActivity.this,""+enternum.getText().toString(),Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

        removebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum.setText("");
            }
        });
        backspacebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enternum.getText().length() == 0)
                {

                }
                else {
                    String num = enternum.getText().toString();
                    num = num.substring(0, num.length() - 1);
                    enternum.setText(num);
                }
            }
        });
        btnstar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum.append("*");
            }
        });
        btnhash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum.append("#");
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum.append("0");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum.append("1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum.append("2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum.append("3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum.append("4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum.append("5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum.append("6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum.append("7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum.append("8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum.append("9");
            }
        });




        View btm = findViewById(R.id.nestedscrollview);
        bottomSheetBehavior = BottomSheetBehavior.from(btm);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState){
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        bottomSheetBehavior.setPeekHeight(80);
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        numtxt = findViewById(R.id.enternumber_txt);
        numtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(3);
            }
        });

        DB = new Db(this);
        DB.getContacts();

        fab =findViewById(R.id.menu_item);
       fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(MainActivity.this, AddActivity.class));
           }
       });

        list = (ListView) findViewById(R.id.contact_list);

        ArrayList<Contact> contacts =  DB.getContacts();

        ContactAdapter contactAdapter = new ContactAdapter(this,R.layout.contact_layout,contacts);
        list.setAdapter(contactAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestcallpermission();
                }
                else
                {
                    Contact c = (Contact) parent.getItemAtPosition(position);
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+c.getNum()));
                    startActivity(callIntent);
                }

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,final int position, long id) {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Edit/Delete Contact")
                        .setMessage("Do You Want To Edit/Delete Contact")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Contact c = (Contact) parent.getItemAtPosition(position);
                                  Intent intent = new Intent(MainActivity.this,UpdateActivity.class);
                                  intent.putExtra("id",c.getId());
                                  startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create()
                        .show();

                return true;
                }
        });


    }

    public void requestcallpermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission Request")
                    .setMessage("This Permission is required")
                    .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this,"Permission is Granted",Toast.LENGTH_LONG).show();

                        }
                    })
                    .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            Toast.makeText(MainActivity.this,"Permission is Denied",Toast.LENGTH_LONG).show();
                        }
                    })
                    .create()
                    .show();

        }else
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            if(requestCode == REQUEST_CALL_PERMISSION)
            {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                        Toast.makeText(this,"Permission Granted",Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_LONG).show();
                }
            }
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Contact> contacts =  DB.getContacts();

        ContactAdapter contactAdapter = new ContactAdapter(this,R.layout.contact_layout,contacts);
        list.setAdapter(contactAdapter);
    }

    @Override
    public void onBackPressed() {

            if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED )
            {
                if(count == 0)
                {
                    Toast.makeText(MainActivity.this,"Press Again to exit",Toast.LENGTH_LONG).show();
                    count++;
                }
                else
                {
                    finish();
                    System.exit(0);
                }
            }
            else
            {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
    }
}
