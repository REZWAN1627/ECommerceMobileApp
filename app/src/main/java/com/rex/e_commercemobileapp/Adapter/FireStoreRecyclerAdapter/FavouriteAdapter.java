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
import com.rex.e_commercemobileapp.Model.Favourite.FavouriteModel;
import com.rex.e_commercemobileapp.Product_Details;
import com.rex.e_commercemobileapp.R;
import com.squareup.picasso.Picasso;

public class FavouriteAdapter extends FirestoreRecyclerAdapter<FavouriteModel, FavouriteAdapter.FavouriteShoesViewHolder> {
    private Context context;

    public FavouriteAdapter(@NonNull FirestoreRecyclerOptions<FavouriteModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull FavouriteShoesViewHolder holder, int position, @NonNull FavouriteModel model) {

        Picasso.get().load(Uri.parse(model.getProduct_Image())).into(holder.Shoes_Image1);
        holder.Rating1.setText(model.getProduct_Rating());
        holder.Shoes_Name1.setText(model.getProduct_Name());
        holder.Shoes_Price1.setText("$" + model.getProduct_Price());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Product_Details.class).

                        putExtra("ProductName", model.getProduct_Name()).
                                putExtra("ProductRating", model.getProduct_Rating()).
                                putExtra("ProductPrice", model.getProduct_Price()).
                                putExtra("ProductColor",model.getProduct_Color()).
                                putExtra("ProductImage",model.getProduct_Image()).
                                putExtra("ProductNumber",model.getProduct_ID())
                                .putExtra("Tab","fab")

                );
            }
        });



    }

    @NonNull
    @Override
    public FavouriteShoesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_favouritesitem, parent, false);
        return new FavouriteShoesViewHolder(view);
    }
    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    public class FavouriteShoesViewHolder extends RecyclerView.ViewHolder {
        ImageView Shoes_Image1, Favourite1;
        TextView Rating1, Shoes_Name1, Shoes_Price1;
        RelativeLayout cardView;

        public FavouriteShoesViewHolder(@NonNull View itemView) {
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
