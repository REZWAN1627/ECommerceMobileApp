package com.rex.e_commercemobileapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rex.e_commercemobileapp.Model.ClothsModel;
import com.rex.e_commercemobileapp.Product_Details;
import com.rex.e_commercemobileapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerView_ClothsAdapter extends RecyclerView.Adapter<RecyclerView_ClothsAdapter.RecyclerView_ClothsViewHolder> {
    private ArrayList<ClothsModel> clothsModels = new ArrayList<>();
    private Context context;
    private int left = 0, right = 1;
    private static final String TAG = "TAG";

    public RecyclerView_ClothsAdapter(ArrayList<ClothsModel> clothsModels, Context context) {
        this.clothsModels = clothsModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView_ClothsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new RecyclerView_ClothsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView_ClothsViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        Log.d(TAG, "onBindViewHolder: " + left + " " + right);


        Picasso.get().load(Uri.parse(clothsModels.get(left).getCloth_Image())).into(holder.Cloths_Image1);
        holder.Rating1.setText(clothsModels.get(left).getCloth_Rating());
        holder.Cloths_Name1.setText(clothsModels.get(left).getCloth_Name());
        holder.Cloths_Price1.setText("$" + clothsModels.get(left).getCloth_Price());

        Picasso.get().load(Uri.parse(clothsModels.get(right).getCloth_Image())).into(holder.Cloths_Image2);
        holder.Rating2.setText(clothsModels.get(right).getCloth_Rating());
        holder.Cloths_Name2.setText(clothsModels.get(right).getCloth_Name());
        holder.Cloths_Price2.setText("$" + clothsModels.get(right).getCloth_Price());


        holder.cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+position+" left "+left);
                if (position == 0){
                    context.startActivity(new Intent(context, Product_Details.class).
                            putExtra("ProductName", clothsModels.get(0).getCloth_Name()).
                            putExtra("ProductRating", clothsModels.get(0).getCloth_Rating()).
                            putExtra("ProductPrice", clothsModels.get(0).getCloth_Price()).
                            putExtra("ProductColor",clothsModels.get(0).getCloth_color()).
                            putExtra("ProductImage",clothsModels.get(0).getCloth_Image()).
                            putExtra("ProductNumber",clothsModels.get(0).getCloth_ID())
                            .putExtra("Tab","Cloth")
                    );
                }else if (position == 1){
                    context.startActivity(new Intent(context, Product_Details.class).
                            putExtra("ProductName", clothsModels.get(2).getCloth_Name()).
                            putExtra("ProductRating", clothsModels.get(2).getCloth_Rating()).
                            putExtra("ProductPrice", clothsModels.get(2).getCloth_Price()).
                            putExtra("ProductColor",clothsModels.get(2).getCloth_color()).
                            putExtra("ProductImage",clothsModels.get(2).getCloth_Image()).
                            putExtra("ProductNumber",clothsModels.get(2).getCloth_ID())
                            .putExtra("Tab","Cloth")
                    );
                }else if (position == 2){
                    context.startActivity(new Intent(context, Product_Details.class).
                            putExtra("ProductName", clothsModels.get(4).getCloth_Name()).
                            putExtra("ProductRating", clothsModels.get(4).getCloth_Rating()).
                            putExtra("ProductPrice", clothsModels.get(4).getCloth_Price()).
                            putExtra("ProductColor",clothsModels.get(4).getCloth_color()).
                            putExtra("ProductImage",clothsModels.get(4).getCloth_Image())
                            .putExtra("ProductNumber",clothsModels.get(4).getCloth_ID())
                            .putExtra("Tab","Cloth")
                    );
                }else if (position == 3){
                    context.startActivity(new Intent(context, Product_Details.class).
                            putExtra("ProductName", clothsModels.get(6).getCloth_Name()).
                            putExtra("ProductRating", clothsModels.get(6).getCloth_Rating()).
                            putExtra("ProductPrice", clothsModels.get(6).getCloth_Price()).
                            putExtra("ProductColor",clothsModels.get(6).getCloth_color()).
                            putExtra("ProductImage",clothsModels.get(6).getCloth_Image())
                            .putExtra("ProductNumber",clothsModels.get(6).getCloth_ID())
                            .putExtra("Tab","Cloth")
                    );
                }else {
                    context.startActivity(new Intent(context, Product_Details.class).
                            putExtra("ProductName", clothsModels.get(8).getCloth_Name()).
                            putExtra("ProductRating", clothsModels.get(8).getCloth_Rating()).
                            putExtra("ProductPrice", clothsModels.get(8).getCloth_Price()).
                            putExtra("ProductColor",clothsModels.get(8).getCloth_color()).
                            putExtra("ProductImage",clothsModels.get(8).getCloth_Image())
                            .putExtra("ProductNumber",clothsModels.get(8).getCloth_ID())
                            .putExtra("Tab","Cloth")
                    );
                }
            }
        });

        holder.cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == 0){
                    context.startActivity(new Intent(context, Product_Details.class).
                            putExtra("ProductName", clothsModels.get(1).getCloth_Name()).
                            putExtra("ProductRating", clothsModels.get(1).getCloth_Rating()).
                            putExtra("ProductPrice", clothsModels.get(1).getCloth_Price()).
                            putExtra("ProductColor",clothsModels.get(1).getCloth_color()).
                            putExtra("ProductImage",clothsModels.get(1).getCloth_Image())
                            .putExtra("ProductNumber",clothsModels.get(1).getCloth_ID())
                            .putExtra("Tab","Cloth")
                    );
                }else if (position == 1){
                    context.startActivity(new Intent(context, Product_Details.class).
                            putExtra("ProductName", clothsModels.get(3).getCloth_Name()).
                            putExtra("ProductRating", clothsModels.get(3).getCloth_Rating()).
                            putExtra("ProductPrice", clothsModels.get(3).getCloth_Price()).
                            putExtra("ProductColor",clothsModels.get(3).getCloth_color()).
                            putExtra("ProductImage",clothsModels.get(3).getCloth_Image())
                            .putExtra("ProductNumber",clothsModels.get(3).getCloth_ID())
                            .putExtra("Tab","Cloth")
                    );
                }else if (position == 2){
                    context.startActivity(new Intent(context, Product_Details.class).
                            putExtra("ProductName", clothsModels.get(5).getCloth_Name()).
                            putExtra("ProductRating", clothsModels.get(5).getCloth_Rating()).
                            putExtra("ProductPrice", clothsModels.get(5).getCloth_Price()).
                            putExtra("ProductColor",clothsModels.get(5).getCloth_color()).
                            putExtra("ProductImage",clothsModels.get(5).getCloth_Image())
                            .putExtra("ProductNumber",clothsModels.get(5).getCloth_ID())
                            .putExtra("Tab","Cloth")
                    );
                }else if (position == 3){
                    context.startActivity(new Intent(context, Product_Details.class).
                            putExtra("ProductName", clothsModels.get(7).getCloth_Name()).
                            putExtra("ProductRating", clothsModels.get(7).getCloth_Rating()).
                            putExtra("ProductPrice", clothsModels.get(7).getCloth_Price()).
                            putExtra("ProductColor",clothsModels.get(7).getCloth_color()).
                            putExtra("ProductImage",clothsModels.get(7).getCloth_Image())
                            .putExtra("ProductNumber",clothsModels.get(7).getCloth_ID())
                            .putExtra("Tab","Cloth")
                    );
                }else {
                    context.startActivity(new Intent(context, Product_Details.class).
                            putExtra("ProductName", clothsModels.get(9).getCloth_Name()).
                            putExtra("ProductRating", clothsModels.get(9).getCloth_Rating()).
                            putExtra("ProductPrice", clothsModels.get(9).getCloth_Price()).
                            putExtra("ProductColor",clothsModels.get(9).getCloth_color()).
                            putExtra("ProductImage",clothsModels.get(9).getCloth_Image())
                            .putExtra("ProductNumber",clothsModels.get(9).getCloth_ID())
                            .putExtra("Tab","Cloth")
                    );
                }

            }
        });

        left += 2;
        right += 2;

    }

    @Override
    public int getItemCount() {
        return (clothsModels.size() / 2);
    }

    public class RecyclerView_ClothsViewHolder extends RecyclerView.ViewHolder {
        ImageView Cloths_Image1, Cloths_Image2, Favourite1, Favourite2;
        TextView Rating1, Cloths_Name1, Rating2, Cloths_Name2, Cloths_Price1, Cloths_Price2;
        RelativeLayout cardView1, cardView2;

        public RecyclerView_ClothsViewHolder(@NonNull View itemView) {
            super(itemView);
            Cloths_Image1 = itemView.findViewById(R.id.Shoes_image);
            Cloths_Image2 = itemView.findViewById(R.id.Shoes_image1);
            Favourite1 = itemView.findViewById(R.id.Shoes_favourite);
            Favourite2 = itemView.findViewById(R.id.Shoes_favourite1);
            Rating1 = itemView.findViewById(R.id.Shoes_Rating);
            Rating2 = itemView.findViewById(R.id.Shoes_Rating1);
            Cloths_Name1 = itemView.findViewById(R.id.Shoes_Name);
            Cloths_Name2 = itemView.findViewById(R.id.Shoes_Name1);
            Cloths_Price1 = itemView.findViewById(R.id.Shoes_Price);
            Cloths_Price2 = itemView.findViewById(R.id.Shoes_Price1);

            cardView1 = itemView.findViewById(R.id.cardView);
            cardView2 = itemView.findViewById(R.id.cardView1);
        }
    }
}
