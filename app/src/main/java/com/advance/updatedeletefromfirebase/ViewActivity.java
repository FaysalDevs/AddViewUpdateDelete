package com.advance.updatedeletefromfirebase;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    ListView listView;
    DatabaseReference databaseReference;
    List<ProductData> productDataList;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);


        listView = findViewById(R.id.product_list);
        progressDialog  = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait Loading data...");

        productDataList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Product");

        progressDialog.show();

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productDataList.clear();
                for (DataSnapshot ProductSnapshot : dataSnapshot.getChildren()){

                    ProductData productData = ProductSnapshot.getValue(ProductData.class);
                    productDataList.add(productData);
                }
                ProductAdapter adapter = new ProductAdapter(ViewActivity.this,productDataList);
                listView.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(ViewActivity.this, "Check your internet connection...", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}
