package com.advance.updatedeletefromfirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleteActivity extends AppCompatActivity {

    LinearLayout showItem;
    TextView showName,showQuantity;
    Button btnSearch,btnDelete;
    EditText searchText;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        searchText = findViewById(R.id.searchText);
        showItem = findViewById(R.id.showItem);
        showName = findViewById(R.id.showName);
        showQuantity = findViewById(R.id.showQuantity);
        btnSearch = findViewById(R.id.btnSearch);
        btnDelete = findViewById(R.id.btnDelete);
        databaseReference = FirebaseDatabase.getInstance().getReference("Product");

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchItem();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem();
            }
        });
    }
    private void deleteItem() {
        String exitPname = showName.getText().toString();
        databaseReference.orderByChild("pName").equalTo(exitPname).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    ds.getRef().removeValue();
                    showName.setText("");
                    showQuantity.setText("");
                    showItem.setVisibility(View.GONE);
                    searchText.setText("");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    private void searchItem() {
        String sText = searchText.getText().toString();
        databaseReference.orderByChild("pName").equalTo(sText).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    AddModel value = ds.getValue(AddModel.class);
                    showName.setText(value.getpName().toString());
                    showQuantity.setText(value.getpQuantity().toString());
                    showItem.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

}
