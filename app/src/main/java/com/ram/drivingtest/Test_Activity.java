package com.ram.drivingtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.ram.drivingtest.Adapter.QuestionAdapter;
import com.ram.drivingtest.Model.QuestionModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ramesh on 11-05-2017.
 */

public class Test_Activity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;
    private ImageView mImageView;
    private StorageReference mStoregeReference;
    FirebaseStorage storage = FirebaseStorage.getInstance();

    TextView txt_question;
    ImageView img_image;

    ArrayList<QuestionModel> list_question;

    QuestionAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        MyAdView.SetAD((AdView) findViewById(R.id.adView));

        // Initialize Firebase Auth and Database Reference
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mStoregeReference = FirebaseStorage.getInstance().getReference();

        if (mFirebaseUser == null) {
            // Not logged in, launch the Log In activity
            loadLogInView();
        } else {
            mUserId = mFirebaseUser.getUid();
            /*QuestionModel obj = new QuestionModel("What is LLR?", "http://image10.bizrate-images.com/resize?sq=60&uid=2216744464");
            mDatabase.child("english").push().setValue(obj);*/

            txt_question = (TextView) findViewById(R.id.txt_question);
            img_image = (ImageView) findViewById(R.id.img_image);

            mDatabase.child("english").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    list_question = new ArrayList<QuestionModel>();
                    QuestionModel obj3 = new QuestionModel();
                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                        String name = (String) messageSnapshot.child("question").getValue();
                        String message = (String) messageSnapshot.child("url").getValue();
                       /* obj3.setQuestion(name);
                        obj3.setUrl(message);
                        list_question.add(obj3);*/

                        txt_question.setText(name);
                        Picasso.with(Test_Activity.this).load(message).fit().centerCrop().into(img_image);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            mAdapter = new QuestionAdapter(this, list_question);


        }

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
        if (id == R.id.action_logout) {
            mFirebaseAuth.signOut();
            loadLogInView();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadLogInView() {
        Intent intent = new Intent(this, Login_Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
