package com.rex.e_commercemobileapp.Authenticate;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.rex.e_commercemobileapp.MainActivity;
import com.rex.e_commercemobileapp.R;
import com.squareup.picasso.Picasso;


import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class SignIn extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private static final String TAG = "TAG";
    private String Password;

    private TextView textView;
    private EditText password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        textView = findViewById(R.id.NameUser);
        password = findViewById(R.id.passwordUser);



      GetPassword();
    }

    private void GetPassword() {

        db.collection("users").document(firebaseUser.getEmail())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                Password = (String) documentSnapshot.get("Password");

                textView.setText(documentSnapshot.get("FirstName")+" "+documentSnapshot.get("LastName"));


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: "+e.getMessage());
                Toast.makeText(SignIn.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void SignIn(View view) {
        if (password.getText().toString().isEmpty()){
            password.setError("Field Empty");
            return;
        }else {
            if (password.getText().toString().equals(Password)){
                Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignIn.this, MainActivity.class));
                finish();
            }else {
                Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show();
                password.setError("Wrong Password");
            }
        }

    }

    public void CreateAccount(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(SignIn.this,Signup_RegistrationPage.class));
        finish();
    }

    public void ForgottenPass(View view) {
        new AlertDialog.Builder(this)
                .setTitle(" Password Recovery")
                .setMessage(Password)

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SignIn.this, "Don't Show to anyone!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(R.drawable.ic_baseline_info_24)
                .show();
    }


}