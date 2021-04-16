package com.rex.e_commercemobileapp.MainUi;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.rex.e_commercemobileapp.Adapter.RecyclerView_ClothsAdapter;
import com.rex.e_commercemobileapp.Adapter.RecyclerView_ShoesAdapter;
import com.rex.e_commercemobileapp.Model.ClothsModel;
import com.rex.e_commercemobileapp.Model.ShoesModel;
import com.rex.e_commercemobileapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Clothings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Clothings extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Clothings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Clothings.
     */
    // TODO: Rename and change types and number of parameters
    public static Clothings newInstance(String param1, String param2) {
        Clothings fragment = new Clothings();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private View view;
    private RecyclerView recyclerView;
    private ArrayList<ClothsModel> clothsModels = new ArrayList<>();
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private RecyclerView_ClothsAdapter recyclerView_clothsAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_clothings, container, false);

        recyclerView = view.findViewById(R.id.RecyclerViewCloth);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        firebaseFirestore.collection("Product_Cloth").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentChange documentChange: value.getDocumentChanges()){
                    ClothsModel clothsModel = documentChange.getDocument().toObject(ClothsModel.class);
                    clothsModels.add(clothsModel);
                    recyclerView_clothsAdapter.notifyDataSetChanged();
                }

            }
        });

        recyclerView_clothsAdapter = new RecyclerView_ClothsAdapter(clothsModels,getContext());
        recyclerView.setAdapter(recyclerView_clothsAdapter);



        return view;
    }
}