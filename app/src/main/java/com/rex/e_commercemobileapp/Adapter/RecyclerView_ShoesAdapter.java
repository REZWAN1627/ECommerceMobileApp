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

import com.rex.e_commercemobileapp.Model.ShoesModel;
import com.rex.e_commercemobileapp.Product_Details;
import com.rex.e_commercemobileapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerView_ShoesAdapter extends RecyclerView.Adapter<RecyclerView_ShoesAdapter.RecyclerViewViewHolder> {

    private ArrayList<ShoesModel> shoesModels = new ArrayList<>();
    private static final String TAG = "TAG";
    private Context context;
    private int left = 0;
    private int right = 1;

    public RecyclerView_ShoesAdapter(ArrayList<ShoesModel> shoesModels, Context context) {
        this.shoesModels = shoesModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new RecyclerViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder holder, int position) {


        Log.d(TAG, "onBindViewHolder: " + position);
        Log.d(TAG, "onBindViewHolder: " + left + " " + right);


        Picasso.get().load(Uri.parse(shoesModels.get(left).getShoe_Image())).into(holder.Shoes_Image1);
        holder.Rating1.setText(shoesModels.get(left).getShoe_Rating());
        holder.Shoes_Name1.setText(shoesModels.get(left).getShoe_Name());
        holder.Shoes_Price1.setText("$" + shoesModels.get(left).getShoe_Price());


        Picasso.get().load(Uri.parse(shoesModels.get(right).getShoe_Image())).into(holder.Shoes_Image2);
        holder.Rating2.setText(shoesModels.get(right).getShoe_Rating());
        holder.Shoes_Name2.setText(shoesModels.get(right).getShoe_Name());
        holder.Shoes_Price2.setText("$" + shoesModels.get(right).getShoe_Price());




        holder.cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+position+" left "+left);
               if (position == 0){
                   context.startActivity(new Intent(context, Product_Details.class).
                           putExtra("ProductName", shoesModels.get(0).getShoe_Name()).
                           putExtra("ProductRating", shoesModels.get(0).getShoe_Rating()).
                           putExtra("ProductPrice", shoesModels.get(0).getShoe_Price()).
                           putExtra("ProductColor",shoesModels.get(0).getShoe_Color()).
                           putExtra("ProductImage",shoesModels.get(0).getShoe_Image()).
                           putExtra("ProductNumber",shoesModels.get(0).getShoe_ID())
                           .putExtra("Tab","Shoes")
                   );
               }else if (position == 1){
                   context.startActivity(new Intent(context, Product_Details.class).
                           putExtra("ProductName", shoesModels.get(2).getShoe_Name()).
                           putExtra("ProductRating", shoesModels.get(2).getShoe_Rating()).
                           putExtra("ProductPrice", shoesModels.get(2).getShoe_Price()).
                           putExtra("ProductColor",shoesModels.get(2).getShoe_Color()).
                           putExtra("ProductImage",shoesModels.get(2).getShoe_Image()).
                           putExtra("ProductNumber",shoesModels.get(2).getShoe_ID())
                           .putExtra("Tab","Shoes")
                   );
               }else if (position == 2){
                   context.startActivity(new Intent(context, Product_Details.class).
                           putExtra("ProductName", shoesModels.get(4).getShoe_Name()).
                           putExtra("ProductRating", shoesModels.get(4).getShoe_Rating()).
                           putExtra("ProductPrice", shoesModels.get(4).getShoe_Price()).
                           putExtra("ProductColor",shoesModels.get(4).getShoe_Color()).
                           putExtra("ProductImage",shoesModels.get(4).getShoe_Image()).
                           putExtra("ProductNumber",shoesModels.get(4).getShoe_ID())
                           .putExtra("Tab","Shoes")
                   );
               }else if (position == 3){
                   context.startActivity(new Intent(context, Product_Details.class).
                           putExtra("ProductName", shoesModels.get(6).getShoe_Name()).
                           putExtra("ProductRating", shoesModels.get(6).getShoe_Rating()).
                           putExtra("ProductPrice", shoesModels.get(6).getShoe_Price()).
                           putExtra("ProductColor",shoesModels.get(6).getShoe_Color()).
                           putExtra("ProductImage",shoesModels.get(6).getShoe_Image()).
                           putExtra("ProductNumber",shoesModels.get(6).getShoe_ID())
                           .putExtra("Tab","Shoes")
                   );
               }else {
                   context.startActivity(new Intent(context, Product_Details.class).
                           putExtra("ProductName", shoesModels.get(8).getShoe_Name()).
                           putExtra("ProductRating", shoesModels.get(8).getShoe_Rating()).
                           putExtra("ProductPrice", shoesModels.get(8).getShoe_Price()).
                           putExtra("ProductColor",shoesModels.get(8).getShoe_Color()).
                           putExtra("ProductImage",shoesModels.get(8).getShoe_Image()).
                           putExtra("ProductNumber",shoesModels.get(8).getShoe_ID())
                           .putExtra("Tab","Shoes")
                   );
               }
            }
        });

        holder.cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == 0){
                    context.startActivity(new Intent(context, Product_Details.class).
                            putExtra("ProductName", shoesModels.get(1).getShoe_Name()).
                            putExtra("ProductRating", shoesModels.get(1).getShoe_Rating()).
                            putExtra("ProductPrice", shoesModels.get(1).getShoe_Price()).
                            putExtra("ProductColor",shoesModels.get(1).getShoe_Color()).
                            putExtra("ProductImage",shoesModels.get(1).getShoe_Image()).
                            putExtra("ProductNumber",shoesModels.get(1).getShoe_ID())
                            .putExtra("Tab","Shoes")
                    );
                }else if (position == 1){
                    context.startActivity(new Intent(context, Product_Details.class).
                            putExtra("ProductName", shoesModels.get(3).getShoe_Name()).
                            putExtra("ProductRating", shoesModels.get(3).getShoe_Rating()).
                            putExtra("ProductPrice", shoesModels.get(3).getShoe_Price()).
                            putExtra("ProductColor",shoesModels.get(3).getShoe_Color()).
                            putExtra("ProductImage",shoesModels.get(3).getShoe_Image()).
                            putExtra("ProductNumber",shoesModels.get(9).getShoe_ID())
                            .putExtra("Tab","Shoes")
                    );
                }else if (position == 2){
                    context.startActivity(new Intent(context, Product_Details.class).
                            putExtra("ProductName", shoesModels.get(5).getShoe_Name()).
                            putExtra("ProductRating", shoesModels.get(5).getShoe_Rating()).
                            putExtra("ProductPrice", shoesModels.get(5).getShoe_Price()).
                            putExtra("ProductColor",shoesModels.get(5).getShoe_Color()).
                            putExtra("ProductImage",shoesModels.get(5).getShoe_Image()).
                            putExtra("ProductNumber",shoesModels.get(5).getShoe_ID())
                            .putExtra("Tab","Shoes")
                    );
                }else if (position == 3){
                    context.startActivity(new Intent(context, Product_Details.class).
                            putExtra("ProductName", shoesModels.get(7).getShoe_Name()).
                            putExtra("ProductRating", shoesModels.get(7).getShoe_Rating()).
                            putExtra("ProductPrice", shoesModels.get(7).getShoe_Price()).
                            putExtra("ProductColor",shoesModels.get(7).getShoe_Color()).
                            putExtra("ProductImage",shoesModels.get(7).getShoe_Image()).
                            putExtra("ProductNumber",shoesModels.get(7).getShoe_ID())
                            .putExtra("Tab","Shoes")
                    );
                }else {
                    context.startActivity(new Intent(context, Product_Details.class).
                            putExtra("ProductName", shoesModels.get(9).getShoe_Name()).
                            putExtra("ProductRating", shoesModels.get(9).getShoe_Rating()).
                            putExtra("ProductPrice", shoesModels.get(9).getShoe_Price()).
                            putExtra("ProductColor",shoesModels.get(9).getShoe_Color()).
                            putExtra("ProductImage",shoesModels.get(9).getShoe_Image()).
                            putExtra("ProductNumber",shoesModels.get(9).getShoe_ID())
                            .putExtra("Tab","Shoes")
                    );
                }

            }
        });

        left += 2;
        right += 2;

    }

    @Override
    public int getItemCount() {
        return (shoesModels.size() / 2);
    }

    public class RecyclerViewViewHolder extends RecyclerView.ViewHolder {

        ImageView Shoes_Image1, Shoes_Image2, Favourite1, Favourite2;
        TextView Rating1, Shoes_Name1, Rating2, Shoes_Name2, Shoes_Price1, Shoes_Price2;
        RelativeLayout cardView1, cardView2;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);

            Shoes_Image1 = itemView.findViewById(R.id.Shoes_image);
            Shoes_Image2 = itemView.findViewById(R.id.Shoes_image1);
            Favourite1 = itemView.findViewById(R.id.Shoes_favourite);
            Favourite2 = itemView.findViewById(R.id.Shoes_favourite1);
            Rating1 = itemView.findViewById(R.id.Shoes_Rating);
            Rating2 = itemView.findViewById(R.id.Shoes_Rating1);
            Shoes_Name1 = itemView.findViewById(R.id.Shoes_Name);
            Shoes_Name2 = itemView.findViewById(R.id.Shoes_Name1);
            Shoes_Price1 = itemView.findViewById(R.id.Shoes_Price);
            Shoes_Price2 = itemView.findViewById(R.id.Shoes_Price1);

            cardView1 = itemView.findViewById(R.id.cardView);
            cardView2 = itemView.findViewById(R.id.cardView1);
        }
    }
}
