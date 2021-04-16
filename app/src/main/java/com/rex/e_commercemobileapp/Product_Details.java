package com.rex.e_commercemobileapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Product_Details extends AppCompatActivity {

    private String ProductName, ProductRating, ProductPrice, ProductColor, ProductImage, ProductID, Tab,FootNumber = null;
    private TextView productName, productRating, productPrice, productColor,Foot1,Foot2,Foot3,Foot4;
    private ImageView productImage, FirstProduct, SecondProduct, PI1, PI2, PI3, fab,DialogImage;
    private CardView cardView;
    private Task<ListResult> storageReference;
    private static final String TAG = "TAG";
    private ArrayList<Uri> ImagesList = new ArrayList<>();
    private RelativeLayout secondLayout, FirstLayout,foot1,foot2,foot3,foot4;
    private boolean Flag = false, cloth = false, Time = false, order = false;

    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private  AlertDialog.Builder dialogBuilder;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__details);

        productName = findViewById(R.id.ProductName);
        productRating = findViewById(R.id.ProductRating);
        productPrice = findViewById(R.id.ProductPrice);
        productColor = findViewById(R.id.ProductColor);
        productImage = findViewById(R.id.ProductImage);
        SecondProduct = findViewById(R.id.secondColorImage);
        secondLayout = findViewById(R.id.SecondLayout);
        FirstLayout = findViewById(R.id.FirstLayout);
        FirstProduct = findViewById(R.id.initialPic);
        Foot1 = findViewById(R.id.foot1);
        Foot2 = findViewById(R.id.foot2);
        Foot3 = findViewById(R.id.foot3);
        Foot4 = findViewById(R.id.foot4);
        foot1 = findViewById(R.id.FootSize1);
        foot2 = findViewById(R.id.FootSize2);
        foot3 = findViewById(R.id.FootSize3);
        foot4 = findViewById(R.id.FootSize4);


        Log.d(TAG, "onCreate: " + firebaseUser.getUid());


        PI1 = findViewById(R.id.PI1);
        PI2 = findViewById(R.id.PI2);
        PI3 = findViewById(R.id.PI3);
        fab = findViewById(R.id.Fab);
        cardView = findViewById(R.id.cardFAb);


        ProductName = getIntent().getStringExtra("ProductName");
        ProductRating = getIntent().getStringExtra("ProductRating");
        ProductPrice = getIntent().getStringExtra("ProductPrice");
        ProductColor = getIntent().getStringExtra("ProductColor");
        ProductImage = getIntent().getStringExtra("ProductImage");
        ProductID = getIntent().getStringExtra("ProductNumber");
        Tab = getIntent().getStringExtra("Tab");

        if (Tab.equals("Cloth")){
            cloth = true;

            Foot1.setText("M");
            Foot2.setText("L");
            Foot3.setText("XL");
            Foot4.setText("XXL");

        }else if (Tab.equals("fab")){

            Log.d(TAG, "onCreate: "+ProductID.charAt(0));
            if (ProductID.charAt(0) == 'C'|| Tab.equals("Cloth")){
                cloth = true;

                Foot1.setText("M");
                Foot2.setText("L");
                Foot3.setText("XL");
                Foot4.setText("XXL");

            }
            fab.setImageResource(R.drawable.ic_baseline_favorite_24);

        }


        productName.setText(ProductName);
        productRating.setText(ProductRating);
        productPrice.setText("$" + ProductPrice);
        productColor.setText(ProductColor + " Colors");


        if (ProductID == null) {
            Picasso.get().load(Uri.parse(ProductImage)).into(productImage);
            Picasso.get().load(Uri.parse(ProductImage)).into(FirstProduct);
            Picasso.get().load(Uri.parse(ProductImage)).into(PI1);
            Picasso.get().load(Uri.parse(ProductImage)).into(PI2);
            Picasso.get().load(Uri.parse(ProductImage)).into(PI3);
            SecondProduct.setImageResource(R.drawable.noclor);
            cloth = true;
            Foot1.setText("M");
            Foot2.setText("L");
            Foot3.setText("XL");
            Foot4.setText("XXL");

        } else {
            Picasso.get().load(Uri.parse(ProductImage)).into(productImage);
            Picasso.get().load(Uri.parse(ProductImage)).into(FirstProduct);
            Picasso.get().load(Uri.parse(ProductImage)).into(PI1);
            Picasso.get().load(Uri.parse(ProductImage)).into(PI2);
            Picasso.get().load(Uri.parse(ProductImage)).into(PI3);
            dataBase();
        }


    }

    private void dataBase() {
        storageReference = FirebaseStorage.getInstance().getReference().child("Colors")
                .child("Shoes")
                .child(ProductID)
                .listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {


                        for (StorageReference fileRef : listResult.getItems()) {
                            fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    ImagesList.add(uri);
                                    Log.d(TAG, "onSuccess: working");
                                    Time = true;
                                    Picasso.get().load(ImagesList.get(0)).into(SecondProduct);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.getMessage());

                                }
                            });
                        }
                        //

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.getMessage());
                        Toast.makeText(Product_Details.this, "Error!", Toast.LENGTH_SHORT).show();

                    }
                });


    }

    public void Back(View view) {

        startActivity(new Intent(Product_Details.this,MainActivity.class));
        finish();
    }

    public void FavouriteClicked(View view) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Map<String, Object> user = new HashMap<>();
        fab.setImageResource(R.drawable.ic_baseline_favorite_24);
        if (ProductID != null) {

                user.put("Product_ID", ProductID);
                user.put("Product_Image", ProductImage);
                user.put("Product_Name", ProductName);
                user.put("Product_Price", ProductPrice);
                user.put("Product_Rating", ProductRating);
                user.put("Product_Color", "None");

                Task<Void> documentReference = db.collection("users").document(firebaseUser.getEmail())
                        .collection("Favourite_List").document(ProductName)
                        .set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Product_Details.this, "Added To Favourite List", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onFailure: "+e.getMessage());
                                Toast.makeText(Product_Details.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        });



            return;
        }

    }

    public void SecondColor(View view) {

        FirstLayout.setBackground(getResources().getDrawable(R.drawable.color_secondcolor));
        secondLayout.setBackground(getResources().getDrawable(R.drawable.color_firstcolor));
        PI1.setBackground(getResources().getDrawable(R.drawable.color_secondcolor));
        PI2.setBackground(getResources().getDrawable(R.drawable.color_secondcolor));
        PI3.setBackground(getResources().getDrawable(R.drawable.color_secondcolor));
        if (cloth) {

            Toast.makeText(this, "The Product Has Only 1 Color", Toast.LENGTH_SHORT).show();
            FirstLayout.setBackground(getResources().getDrawable(R.drawable.color_firstcolor));
            return;

        } else if (Time) {
            Picasso.get().load(ImagesList.get(0)).into(productImage);
            Picasso.get().load(ImagesList.get(0)).into(SecondProduct);
            Picasso.get().load(ImagesList.get(0)).into(PI1);
            Picasso.get().load(ImagesList.get(1)).into(PI2);
            Picasso.get().load(ImagesList.get(2)).into(PI3);


            order = true;

            Flag = true;
        } else {
            Toast.makeText(this, "Please Wait", Toast.LENGTH_SHORT).show();
        }


    }

    public void FirstColor(View view) {
        order = false;
        Flag = false;
        Picasso.get().load(Uri.parse(ProductImage)).into(productImage);
        Picasso.get().load(Uri.parse(ProductImage)).into(FirstProduct);
        Picasso.get().load(Uri.parse(ProductImage)).into(PI1);
        Picasso.get().load(Uri.parse(ProductImage)).into(PI2);
        Picasso.get().load(Uri.parse(ProductImage)).into(PI3);

        FirstLayout.setBackground(getResources().getDrawable(R.drawable.color_firstcolor));
        secondLayout.setBackground(getResources().getDrawable(R.drawable.color_secondcolor));
        PI1.setBackground(getResources().getDrawable(R.drawable.color_secondcolor));
        PI2.setBackground(getResources().getDrawable(R.drawable.color_secondcolor));
        PI3.setBackground(getResources().getDrawable(R.drawable.color_secondcolor));

    }

    public void PI1(View view) {
        PI1.setBackground(getResources().getDrawable(R.drawable.color_firstcolor));
        PI2.setBackground(getResources().getDrawable(R.drawable.color_secondcolor));
        PI3.setBackground(getResources().getDrawable(R.drawable.color_secondcolor));
        if (Flag) {
            Picasso.get().load(ImagesList.get(0)).into(productImage);
        } else {
            Picasso.get().load(Uri.parse(ProductImage)).into(productImage);
        }
    }

    public void PI2(View view) {
        PI1.setBackground(getResources().getDrawable(R.drawable.color_secondcolor));
        PI2.setBackground(getResources().getDrawable(R.drawable.color_firstcolor));
        PI3.setBackground(getResources().getDrawable(R.drawable.color_secondcolor));
        if (Flag) {
            Picasso.get().load(ImagesList.get(1)).into(productImage);

        } else {
            Picasso.get().load(Uri.parse(ProductImage)).into(productImage);
        }
    }

    public void PI3(View view) {
        PI1.setBackground(getResources().getDrawable(R.drawable.color_secondcolor));
        PI2.setBackground(getResources().getDrawable(R.drawable.color_secondcolor));
        PI3.setBackground(getResources().getDrawable(R.drawable.color_firstcolor));
        if (Flag) {
            Picasso.get().load(ImagesList.get(2)).into(productImage);
        } else {
            Picasso.get().load(Uri.parse(ProductImage)).into(productImage);
        }
    }

    public void Purchased(View view) {

        if (FootNumber!=null){

            dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.cartdialog, null);
            dialogBuilder.setView(dialogView);

             DialogImage = dialogView.findViewById(R.id.dialogImage);
            TextView Name, Price, Size, Color, Time,Date;
            Name = dialogView.findViewById(R.id.dialogName);
            Price = dialogView.findViewById(R.id.dialogPrice);
            Size = dialogView.findViewById(R.id.dialogSize);
            Color = dialogView.findViewById(R.id.dialogColor);
            Time = dialogView.findViewById(R.id.dialogTimer);
            Button Purchased = dialogView.findViewById(R.id.dialogPurchased);
            Date = dialogView.findViewById(R.id.dialogDate);
             alertDialog = dialogBuilder.create();

            Name.setText("Name: "+ProductName);
            Price.setText(ProductPrice);
            Size.setText("Size: "+FootNumber);
            Time.setText("Time: "+new SimpleDateFormat("hh:mm a").format(new Date()));
            Date.setText("Date: "+new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
            if (order){
                Color.setText("Color: Alternative");
                Picasso.get().load(ImagesList.get(0)).into(DialogImage);

            }else {
                Color.setText("Color: Regular");
                Picasso.get().load(Uri.parse(ProductImage)).into(DialogImage);
            }


            Purchased.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (order){

                        updateDatabase(Name.getText().toString(),Price.getText().toString(),Size.getText().toString(),
                                Color.getText().toString(),Time.getText().toString(),Date.getText().toString(),ImagesList.get(0).toString());
                    }else {

                        updateDatabase(Name.getText().toString(),Price.getText().toString(),Size.getText().toString(),
                                Color.getText().toString(),Time.getText().toString(),Date.getText().toString(),ProductImage);
                    }

                    getSecondDialog();




                }



            });

            alertDialog.show();

        }else {
            Toast.makeText(this, "Size Not Selected", Toast.LENGTH_SHORT).show();
        }



    }

    private void getSecondDialog() {
        AlertDialog.Builder  dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View DialogView = inflater.inflate(R.layout.dialog_payment, null);
        dialogBuilder.setView(DialogView);

        LinearLayout bkash , nagad;
        bkash =DialogView.findViewById(R.id.bkash);
        nagad = DialogView.findViewById(R.id.nagad);
        AlertDialog Dialog = dialogBuilder.create();

        bkash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Product_Details.this, "Product Payed In bKash ", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Product_Details.this, "Purchased", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();

                    }
                },2000);
                Dialog.dismiss();
            }
        });

        nagad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Product_Details.this, "Product Payed In Nagad ", Toast.LENGTH_SHORT).show();
                Dialog.dismiss();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Product_Details.this, "Purchased", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();

                    }
                },2000);
            }
        });


        Dialog.show();
    }


    private void updateDatabase(String Name, String Price, String Size, String Color,String Time, String date, String imageView) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Map<String, Object> user = new HashMap<>();
        user.put("Product_Name", Name);
        user.put("Product_Price", Price);
        user.put("Product_Size", Size);
        user.put("Product_Color", Color);
        user.put("Product_Date", date);
        user.put("Product_Time", Time);
        user.put("Product_Image", imageView);
        Task<Void> documentReference = db.collection("users").document(firebaseUser.getEmail()).
                collection("Purchased").document(Name)
                .set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: Uploading");
                        Toast.makeText(Product_Details.this, "Success", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: "+e.getMessage());
                        Toast.makeText(Product_Details.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void FootSize1(View view) {

        foot1.setBackground(getResources().getDrawable(R.drawable.color_firstcolor));
        foot2.setBackground(getResources().getDrawable(R.drawable.stroke_sizes));
        foot3.setBackground(getResources().getDrawable(R.drawable.stroke_sizes));
        foot4.setBackground(getResources().getDrawable(R.drawable.stroke_sizes));
        FootNumber = Foot1.getText().toString();

    }

    public void FootSize2(View view) {
        foot1.setBackground(getResources().getDrawable(R.drawable.stroke_sizes));
        foot2.setBackground(getResources().getDrawable(R.drawable.color_firstcolor));
        foot3.setBackground(getResources().getDrawable(R.drawable.stroke_sizes));
        foot4.setBackground(getResources().getDrawable(R.drawable.stroke_sizes));
        FootNumber = Foot2.getText().toString();
    }

    public void FootSize3(View view) {
        foot1.setBackground(getResources().getDrawable(R.drawable.stroke_sizes));
        foot2.setBackground(getResources().getDrawable(R.drawable.stroke_sizes));
        foot3.setBackground(getResources().getDrawable(R.drawable.color_firstcolor));
        foot4.setBackground(getResources().getDrawable(R.drawable.stroke_sizes));
        FootNumber = Foot3.getText().toString();
    }

    public void FootSize4(View view) {
        foot1.setBackground(getResources().getDrawable(R.drawable.stroke_sizes));
        foot2.setBackground(getResources().getDrawable(R.drawable.stroke_sizes));
        foot3.setBackground(getResources().getDrawable(R.drawable.stroke_sizes));
        foot4.setBackground(getResources().getDrawable(R.drawable.color_firstcolor));
        FootNumber = Foot4.getText().toString();
    }


}