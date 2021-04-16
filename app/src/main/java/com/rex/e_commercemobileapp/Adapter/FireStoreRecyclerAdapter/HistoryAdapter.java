package com.rex.e_commercemobileapp.Adapter.FireStoreRecyclerAdapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.rex.e_commercemobileapp.Model.HistoryModel;
import com.rex.e_commercemobileapp.R;
import com.squareup.picasso.Picasso;

public class HistoryAdapter extends FirestoreRecyclerAdapter<HistoryModel, HistoryAdapter.HistoryViewHolder> {


    public HistoryAdapter(@NonNull FirestoreRecyclerOptions<HistoryModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull HistoryViewHolder holder, int position, @NonNull HistoryModel model) {
        Picasso.get().load(Uri.parse(model.getProduct_Image())).into(holder.imageView);
        holder.Name.setText(model.getProduct_Name());
        holder.Price.setText("Price: $"+model.getProduct_Price());
        holder.Size.setText(model.getProduct_Size());
        holder.Color.setText(model.getProduct_Color());
        holder.Time.setText(model.getProduct_Time());
        holder.Date.setText(model.getProduct_Date());

    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_historyitem,
                parent,false);
        return new HistoryViewHolder(view);
    }

    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder{

        TextView Name,Price,Size,Color,Time,Date;
        ImageView imageView;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.PurchesName);
            Price = itemView.findViewById(R.id.PurchesPrice);
            Size = itemView.findViewById(R.id.PurchesSize);
            Color = itemView.findViewById(R.id.PurchesColor);
            Time = itemView.findViewById(R.id.PurchesTime);
            Date = itemView.findViewById(R.id.PurchesDate);
            imageView = itemView.findViewById(R.id.PurchesImage);
        }
    }
}
