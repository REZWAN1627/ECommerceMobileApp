package com.rex.e_commercemobileapp.MainUi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.rex.e_commercemobileapp.Model.ShoesModel;
import com.rex.e_commercemobileapp.R;

import java.util.HashMap;
import java.util.Map;


public class Profile_Edit extends Fragment {



    private TextView textView1, textView2;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private Map<String,Object> updateName = new HashMap<>();
    private static final String TAG = "TAG";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile__edit, container, false);
        textView1 = view.findViewById(R.id.FirstName);
        textView2 = view.findViewById(R.id.LastName);

        firebaseFirestore.collection("users").document(firebaseUser.getEmail()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                textView1.setText(value.get("FirstName").toString());
                textView2.setText(value.get("LastName").toString());
            }
        });

        view.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView1.getText().toString().isEmpty() || textView2.getText().toString().isEmpty()){
                    textView2.setError("Empty");
                    textView1.setError("Empty");
                    return;
                }
                updateName.put("LastName",textView2.getText().toString());
                updateName.put("FirstName",textView1.getText().toString());
                Task<Void> db = FirebaseFirestore.getInstance().collection("users").document(firebaseUser.getEmail())
                        .update(updateName).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getContext(), "Updated!", Toast.LENGTH_SHORT).show();
                                getActivity().getSupportFragmentManager().popBackStack();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "Error!", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "onFailure: "+e.getMessage());

                            }
                        });
            }
        });


        return view;
    }
}