package com.ram.drivingtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {

    EditText et_UserName, et_Password;
    Button btn_signin, btn_signup;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_UserName = (EditText) findViewById(R.id.editEmail);
        et_Password = (EditText) findViewById(R.id.editPassword);
        btn_signin = (Button) findViewById(R.id.btn_signin);
        btn_signup = (Button) findViewById(R.id.btn_signup);

        mFirebaseAuth = FirebaseAuth.getInstance();

        MyAdView.SetAD((AdView) findViewById(R.id.adView));

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Login_Activity.this, Registration_Activity.class);
                startActivity(in);
            }
        });

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_UserName.getText().toString();
                String password = et_Password.getText().toString();
                email = email.trim();
                password = password.trim();

                mFirebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_Activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Login_Activity.this);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        MyAdView.SetAD((AdView) findViewById(R.id.adView));
        super.onResume();
    }
}
