package com.rex.e_commercemobileapp.MainUi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rex.e_commercemobileapp.Filter_Search;
import com.rex.e_commercemobileapp.MainActivity;
import com.rex.e_commercemobileapp.R;


public class Main extends Fragment {



    private TextView Indicator1,Indicator2,Shoes,Cloth;
    private  AlertDialog.Builder dialogBuilder;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
        Indicator1 = view.findViewById(R.id.Indicator1);
        Indicator2 = view.findViewById(R.id.Indicator2);
        Shoes = view.findViewById(R.id.ShoesTv);
        Cloth = view.findViewById(R.id.ClothTv);


        Indicator1.setVisibility(View.VISIBLE);
        Shoes.setTextColor(getResources().getColor(R.color.black));
        Cloth.setTextColor(getResources().getColor(R.color.TextColor));
        Indicator2.setVisibility(View.GONE);

        getFragmentManager().beginTransaction().replace(R.id.ProductCointainer, new Shoes()).commit();

        Shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.ProductCointainer, new Shoes()).commit();
                Shoes.setTextColor(getResources().getColor(R.color.black));
                Cloth.setTextColor(getResources().getColor(R.color.TextColor));
                Indicator1.setVisibility(View.VISIBLE);
                Indicator2.setVisibility(View.GONE);
            }
        });

        Cloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shoes.setTextColor(getResources().getColor(R.color.TextColor));
                Cloth.setTextColor(getResources().getColor(R.color.black));
                Indicator1.setVisibility(View.GONE);
                Indicator2.setVisibility(View.VISIBLE);
                getFragmentManager().beginTransaction().replace(R.id.ProductCointainer,new Clothings()).commit();
            }
        });


        view.findViewById(R.id.FilterSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogBuilder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_filter, null);
                dialogBuilder.setView(dialogView);

                RelativeLayout f1, f2;

                f1 = dialogView.findViewById(R.id.DialogFilter1);
                f2 = dialogView.findViewById(R.id.DialogFilter2);

                AlertDialog alertDialog = dialogBuilder.create();

                f1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        f1.setBackground(getActivity().getResources().getDrawable(R.drawable.color_firstcolor));
                        getActivity().startActivity(new Intent(getContext(), Filter_Search.class).putExtra("Order","Dec"));
                        alertDialog.dismiss();
                    }
                });

                f2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        f2.setBackground(getActivity().getResources().getDrawable(R.drawable.color_firstcolor));
                        getActivity().startActivity(new Intent(getContext(), Filter_Search.class).putExtra("Order","Aec"));
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();

            }
        });

        view.findViewById(R.id.searchName).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Under Develop", Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }



}