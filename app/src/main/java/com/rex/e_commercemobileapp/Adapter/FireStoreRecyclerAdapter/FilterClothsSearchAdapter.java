package com.rex.e_commercemobileapp.Adapter.FireStoreRecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.rex.e_commercemobileapp.Model.ClothsModel;
import com.rex.e_commercemobileapp.Product_Details;
import com.rex.e_commercemobileapp.R;
import com.squareup.picasso.Picasso;

public class FilterClothsSearchAdapter extends FirestoreRecyclerAdapter<ClothsModel,FilterClothsSearchAdapter.FilterClothsSearchViewHolder>{

    private Context context;
    public FilterClothsSearchAdapter(@NonNull FirestoreRecyclerOptions<ClothsModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull FilterClothsSearchViewHolder holder, int position, @NonNull ClothsModel model) {
        Picasso.get().load(Uri.parse(model.getCloth_Image())).into(holder.Shoes_Image1);
        holder.Rating1.setText(model.getCloth_Rating());
        holder.Shoes_Name1.setText(model.getCloth_Name());
        holder.Shoes_Price1.setText("$" + model.getCloth_Price());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Product_Details.class).

                        putExtra("ProductName", model.getCloth_Name()).
                        putExtra("ProductRating", model.getCloth_Rating()).
                        putExtra("ProductPrice", model.getCloth_Price()).
                        putExtra("ProductColor",model.getCloth_color()).
                        putExtra("ProductImage",model.getCloth_Image()).
                        putExtra("ProductNumber",model.getCloth_ID())
                        .putExtra("Tab","Cloth")

                );
            }
        });

    }

    @NonNull
    @Override
    public FilterClothsSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_favouritesitem, parent, false);
        return new FilterClothsSearchViewHolder(view);
    }

    public class FilterClothsSearchViewHolder extends RecyclerView.ViewHolder {
        ImageView Shoes_Image1, Favourite1;
        TextView Rating1, Shoes_Name1, Shoes_Price1;
        RelativeLayout cardView;
        public FilterClothsSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            Shoes_Image1 = itemView.findViewById(R.id.FSShoes_image);
            Favourite1 = itemView.findViewById(R.id.FSShoes_favourite);
            Rating1 = itemView.findViewById(R.id.FSShoes_Rating);
            Shoes_Name1 = itemView.findViewById(R.id.FSShoes_Name);
            Shoes_Price1 = itemView.findViewById(R.id.FSShoes_Price);
            cardView = itemView.findViewById(R.id.FScardView);
        }
    }
}
