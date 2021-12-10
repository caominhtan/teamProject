package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangNhap extends AppCompatActivity {

    EditText email,password;
    Button SignUp,SignIn,forgotPass,Cancel;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        maching();
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhap.this,DangKy.class);
                startActivity(intent);
            }
        });


        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String semail=email.getText().toString().trim();
                String spassword=password.getText().toString().trim();
                if (TextUtils.isEmpty(semail)){
                    Toast.makeText(getApplicationContext(), "Hãy Nhập Email", Toast.LENGTH_LONG).show();

                }
                if (TextUtils.isEmpty(spassword)){
                    Toast.makeText(getApplicationContext(), "Hãy Nhập Password", Toast.LENGTH_LONG).show();
                }
                if (spassword.length() < 6){
                    Toast.makeText(getApplicationContext(), "Chiều dài password < 6 kí tự ", Toast.LENGTH_LONG).show();
                    return;
                }

                auth.signInWithEmailAndPassword(semail,spassword).addOnCompleteListener(DangNhap.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DangNhap.this,TrangChu.class));
                            finish();
                        }

                    }
                });

            }
        });
    }

    private void maching() {
        email = findViewById(R.id.Dangnhap_InputEmail);
        password = findViewById(R.id.Dangnhap_et_InputPassword);
        SignUp = findViewById(R.id.Dangnhap_btn_signinDangky);
        SignIn = findViewById(R.id.Dangnhap_btn_signinDangnhap);
        forgotPass = findViewById(R.id.Dangnhap_btn_signinquenmatkhau);




    }
}