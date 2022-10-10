package com.prime.accounts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,password,confirm;
    Button signup,login;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm);
        signup = findViewById(R.id.btnsignup);
        login = findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String retype = confirm.getText().toString();

                if(user.equals("") || pass.equals("") || retype.equals("")){
                    Toast.makeText(MainActivity.this,"Fillout all the fields",Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.equals(retype)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser== false){
                            Boolean insert = DB.inserData(user,pass);
                            if(insert == true){
                                Toast.makeText(MainActivity.this,"account created successfully",Toast.LENGTH_SHORT).show();
                                Intent intent =new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this,"Failed.Please try again",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this,"User already exists.Please sign in",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this,"Passwords do not match",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}