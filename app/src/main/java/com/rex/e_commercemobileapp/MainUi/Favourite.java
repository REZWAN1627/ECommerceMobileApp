package com.rex.e_commercemobileapp.MainUi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.rex.e_commercemobileapp.Adapter.FireStoreRecyclerAdapter.FavouriteAdapter;
import com.rex.e_commercemobileapp.Model.Favourite.FavouriteModel;
import com.rex.e_commercemobileapp.R;


public class Favourite extends Fragment {


   private RecyclerView recyclerView;
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference favouriteRef = db.collection("users").document(firebaseUser.getEmail()).collection("Favourite_List");

    private FavouriteAdapter adapter;

    private static final String TAG = "TAG";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        recyclerView = view.findViewById(R.id.favouriteRecyclerView);

        SetUpRecyclerView();


        return view;
    }

    private void SetUpRecyclerView() {
        Query query = favouriteRef.orderBy("Product_Price", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<FavouriteModel> options = new FirestoreRecyclerOptions.Builder<FavouriteModel>()
                .setQuery(query, FavouriteModel.class)
                .build();

        adapter = new FavouriteAdapter(options,getContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.deleteItem(viewHolder.getAdapterPosition());
                Toast.makeText(getContext(), "Item Removed From Favourite List!", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}