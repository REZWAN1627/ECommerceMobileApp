package com.rex.e_commercemobileapp.MainUi.FragmentPager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.rex.e_commercemobileapp.Adapter.FireStoreRecyclerAdapter.FilterShoesSearchAdapter;
import com.rex.e_commercemobileapp.Model.ShoesModel;
import com.rex.e_commercemobileapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShoesFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoesFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;


    public ShoesFrag() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ShoesFrag newInstance(String param1) {
        ShoesFrag fragment = new ShoesFrag();
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
    private CollectionReference historyRef = db.collection("Product_Shoes");
    private FilterShoesSearchAdapter adapter;
    private static final String TAG = "TAg";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shoes2, container, false);
        recyclerView = view.findViewById(R.id.fragShoesRe);

        SetUpRecyclerView();

        return view;
    }

    private void SetUpRecyclerView() {

        Log.d(TAG, "SetUpRecyclerView: "+mParam1);

        if (mParam1.equals("Dec")){
            Query query = historyRef.orderBy("Shoe_Price", Query.Direction.DESCENDING);
            FirestoreRecyclerOptions<ShoesModel> options = new FirestoreRecyclerOptions.Builder<ShoesModel>()
                    .setQuery(query, ShoesModel.class)
                    .build();

            adapter = new FilterShoesSearchAdapter(options, getContext());

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
            return;

        }

            Query query = historyRef.orderBy("Shoe_Price", Query.Direction.ASCENDING);
            FirestoreRecyclerOptions<ShoesModel> options = new FirestoreRecyclerOptions.Builder<ShoesModel>()
                    .setQuery(query, ShoesModel.class)
                    .build();

            adapter = new FilterShoesSearchAdapter(options, getContext());

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