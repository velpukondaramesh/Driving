package com.ram.drivingtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Ramesh on 10-05-2017.
 */

public class Registration_Activity extends AppCompatActivity {

    EditText et_Name, et_Mail, et_Password;
    Button btn_signup;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        MyAdView.SetAD((AdView) findViewById(R.id.adView));

        et_Name = (EditText) findViewById(R.id.editName);
        et_Mail = (EditText) findViewById(R.id.editEmail);
        et_Password = (EditText) findViewById(R.id.editPassword);
        btn_signup = (Button) findViewById(R.id.btn_signup);

        mFirebaseAuth = FirebaseAuth.getInstance();

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = et_Name.getText().toString();
                String email = et_Mail.getText().toString();
                String password = et_Password.getText().toString();
                email = email.trim();
                password = password.trim();

                mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Registration_Activity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(Registration_Activity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Registration_Activity.this);
                            builder.setMessage(task.getException().getMessage())
                                    .setTitle(R.string.login_error_title)
                                    .setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                });
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
