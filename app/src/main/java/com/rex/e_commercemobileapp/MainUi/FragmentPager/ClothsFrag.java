package com.rex.e_commercemobileapp.MainUi.FragmentPager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.rex.e_commercemobileapp.Adapter.FireStoreRecyclerAdapter.FilterClothsSearchAdapter;
import com.rex.e_commercemobileapp.Adapter.FireStoreRecyclerAdapter.FilterShoesSearchAdapter;
import com.rex.e_commercemobileapp.Model.ClothsModel;
import com.rex.e_commercemobileapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClothsFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClothsFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClothsFrag() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ClothsFrag newInstance(String param1) {
        ClothsFrag fragment = new ClothsFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

        }
    }

    private RecyclerView recyclerView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference historyRef = db.collection("Product_Cloth");
    private FilterClothsSearchAdapter adapter;
    private static final String TAG = "TAG";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cloths, container, false);

        recyclerView = view.findViewById(R.id.fikterRecyclrrr);

        SetUpRecyclerView();

        return view;
    }

    private void SetUpRecyclerView() {

        if (mParam1.equals("Dec")){
            Query query = historyRef.orderBy("Cloth_Price", Query.Direction.DESCENDING);
            FirestoreRecyclerOptions<ClothsModel> options = new FirestoreRecyclerOptions.Builder<ClothsModel>()
                    .setQuery(query, ClothsModel.class)
                    .build();

            adapter = new FilterClothsSearchAdapter(options, getContext());

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
            return;

        }

        Query query = historyRef.orderBy("Cloth_Price", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<ClothsModel> options = new FirestoreRecyclerOptions.Builder<ClothsModel>()
                .setQuery(query, ClothsModel.class)
                .build();

        adapter = new FilterClothsSearchAdapter(options, getContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
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