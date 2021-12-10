package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
public class QuenMatKhau extends AppCompatActivity {

    EditText email;
    Button send, cancel;
    TextView error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);

        matching();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String semail = email.getText().toString().trim();
                if (TextUtils.isEmpty(semail)){
                    Toast.makeText(getApplicationContext(),"Nhập email quên mật khẩu", Toast.LENGTH_LONG).show();
                    return;
                }
                auth.sendPasswordResetEmail(semail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(QuenMatKhau.this, "Nhập email  quên mật khẩu",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(QuenMatKhau.this,"Quên mật khẩu thất bại",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    private void matching() {
        email = (EditText) findViewById(R.id.forget_etEmail);
        send = (Button) findViewById(R.id.QMK_btnDongy);
        cancel = (Button) findViewById(R.id.forget_btnCancel);
        error = (TextView) findViewById(R.id.forget_tvError);
    }
}