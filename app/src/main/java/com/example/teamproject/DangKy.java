package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DangKy extends AppCompatActivity {

    EditText email,password,passwords;
    Button Signup,Signin;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        matching();
        auth = FirebaseAuth.getInstance();

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKy.this,DangNhap.class);
                startActivity(intent);
            }
        });


        auth = FirebaseAuth.getInstance();
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String semail = email.getText().toString().trim();
                String spassword = password.getText().toString().trim();
                if (TextUtils.isEmpty(semail)){
                    Toast.makeText(getApplicationContext(),"Hãy nhập email ",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(spassword)){
                    Toast.makeText(getApplicationContext(),"Hãy nhập password ",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (spassword.length() < 6){
                    Toast.makeText(getApplicationContext(),"password quá ngắn , mời nhập lại ",Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(semail,spassword).addOnCompleteListener(DangKy.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Tạo người dùng thất bại.",Toast.LENGTH_LONG).show();
                            Log.d("Lỗi Đăng Ký",task.getException().getMessage().toString());
                        } else {
                            Toast.makeText(getApplicationContext(),"Tạo người dùng thành công",Toast.LENGTH_LONG).show();


                            auth.signInWithEmailAndPassword(semail,spassword);
                            createUser(auth.getUid(),"",""   , semail  , "a"   ,  "a" ,"a");
                            startActivity(new Intent(DangKy.this,ThemThongtincanhan.class));
                            finish();
                        }
                    }
                });
            }
        });

    }



    private void matching() {

        email = findViewById(R.id.Dangky_et_InputEmail);
        password = findViewById(R.id.Dangky_et_InputPassword);
        passwords = findViewById(R.id.Dangky_et_InputNhaplaiPassword);
        Signup = findViewById(R.id.Dangky_btn_signupDangky);
        Signin = findViewById(R.id.Dangky_btn_signupDangnhap);

    }


    private void createUser(String userID,String cmnd,String address, String email, String gender,String mobile,String name){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Thongtincanhan");
        myRef.child(userID).child("email").setValue(email);
        myRef.child(userID).child("gender").setValue(gender);
        myRef.child(userID).child("mobile").setValue(mobile);
        myRef.child(userID).child("name").setValue(name);
        myRef.child(userID).child("address").setValue(address);
        myRef.child(userID).child("cmnd").setValue(cmnd);
    }


}