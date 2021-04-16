package com.rex.e_commercemobileapp.Authenticate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rex.e_commercemobileapp.MainActivity;
import com.rex.e_commercemobileapp.R;

import java.util.HashMap;
import java.util.Map;

public class UserIdentity extends AppCompatActivity {
    public static final int GALLERY_REQUEST_CODE = 5;
    private String email;
    private TextView UserEmail;
    private EditText firstName, lastName, userPassword;
    private FirebaseFirestore db;
    private Map<String, Object> user = new HashMap<>();
    private static final String TAG = "TAG";
    private FirebaseAuth mAuth;
    private Task<Void> documentReference;
    private FirebaseUser firebaseUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_identity);
        email = getIntent().getStringExtra("Email");
        db = FirebaseFirestore.getInstance();
        UserEmail = findViewById(R.id.UserEmail);
        firstName = findViewById(R.id.UserFirstName);
        lastName = findViewById(R.id.UserLastName);
        userPassword = findViewById(R.id.UserPassword);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        UserEmail.setText(email);
    }

    public void Next(View view) {
        if (firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty() || userPassword.getText().toString().isEmpty()) {
            firstName.setError("Field Empty");
            lastName.setError("Field Empty");
            userPassword.setError("Field Empty");
            return;
        } else {
            user.put("FirstName", firstName.getText().toString());
            user.put("LastName", lastName.getText().toString());
            user.put("Password", userPassword.getText().toString());

            addDataBase();
        }
    }

    private void addDataBase() {
        documentReference = db.collection("users").document(firebaseUser.getEmail())
                .set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(UserIdentity.this, "Stored Successfully", Toast.LENGTH_SHORT).show();
                        firstName.setText("");
                        lastName.setText("");
                        userPassword.setText("");
                        startActivity(new Intent(UserIdentity.this,MainActivity.class));
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: "+e.getMessage());
                        Toast.makeText(UserIdentity.this, "network Error!", Toast.LENGTH_SHORT).show();

                    }
                });



    }


}