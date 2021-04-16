package com.rex.e_commercemobileapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rex.e_commercemobileapp.Authenticate.SignIn;
import com.rex.e_commercemobileapp.Authenticate.Signup_RegistrationPage;
import com.rex.e_commercemobileapp.MainUi.Favourite;
import com.rex.e_commercemobileapp.MainUi.History;
import com.rex.e_commercemobileapp.MainUi.Main;
import com.rex.e_commercemobileapp.MainUi.Profile_Edit;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private TextView NavName, NavEmail;
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.navigationDrawer);
        NavName = findViewById(R.id.navuserName);
        NavEmail = findViewById(R.id.NavEmail);

        NavEmail.setText(firebaseUser.getEmail());

        GetName();



        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, new Main()).commit();

    }

    private void GetName() {
        db.collection("users").document(firebaseUser.getEmail())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                NavName.setText(documentSnapshot.get("FirstName")+" "+documentSnapshot.get("LastName"));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: "+e.getMessage());
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void NavProfile(View view) {
        drawerLayout.closeDrawer(GravityCompat.END);
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.FragmentContainer, new Profile_Edit()).commit();
    }

    public void NavOffer(View view) {
        Toast.makeText(this, "You don't have any offers", Toast.LENGTH_SHORT).show();
    }

    public void NavHistory(View view) {
        drawerLayout.closeDrawer(GravityCompat.END);
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.FragmentContainer, new History()).commit();
    }

    public void NavSignOut(View view) {
        startActivity(new Intent(MainActivity.this, Signup_RegistrationPage.class));
    }

    public void NavigationDrawer(View view) {
        drawerLayout.openDrawer(GravityCompat.END);
    }

    public void NavAbout(View view) {

        drawerLayout.closeDrawer(GravityCompat.END);

        new AlertDialog.Builder(this)
                .setTitle(" About Apps")
                .setMessage("E-Commerce Mobile App\nDeveloper Name: Rezwan Kabir\nId: 171-15-9271\nDepartment: CSE\nVersion: 1.0")

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Thank You!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(R.drawable.ic_baseline_info_24)
                .show();

    }


    public void NavHome(View view) {
        drawerLayout.closeDrawer(GravityCompat.END);
        if (getSupportFragmentManager().getBackStackEntryCount() == 0){
            return;
        }else {
            getSupportFragmentManager().popBackStack();
        }

    }

    public void NavFavourite(View view) {
        drawerLayout.closeDrawer(GravityCompat.END);
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.FragmentContainer, new Favourite()).commit();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        GetName();
    }


    @Override
    protected void onStart() {
        super.onStart();
        GetName();
    }

    @Override
    protected void onStop() {
        super.onStop();
        GetName();
    }
}