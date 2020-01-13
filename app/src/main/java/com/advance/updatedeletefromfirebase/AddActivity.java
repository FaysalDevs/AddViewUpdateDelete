package com.advance.updatedeletefromfirebase;

import android.app.ProgressDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;

public class AddActivity extends AppCompatActivity {

    EditText pName,pQuantity;
    Button btnAdd;
    ProgressBar progressBar;

    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        pName = findViewById(R.id.pName);
        pQuantity = findViewById(R.id.pQuantity);
        btnAdd = findViewById(R.id.btnAdd);

        databaseReference = FirebaseDatabase.getInstance().getReference("Product");

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(AddActivity.this);
                progressDialog.setMessage("upload data...");
                progressDialog.show();

                String pro_name = pName.getText().toString().trim();
                String pro_Quantity = pQuantity.getText().toString().trim();

                if (!TextUtils.isEmpty(pro_name)
                        && !TextUtils.isEmpty(pro_Quantity)){

                    AddModel sendData = new AddModel(pro_name,pro_Quantity);

                    databaseReference.child(pro_name).setValue(sendData).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(AddActivity.this, "Succsessfully Added...", Toast.LENGTH_SHORT).show();


                                pName.setText("");
                                pQuantity.setText("");
                                progressDialog.dismiss();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                }else {
                    Toast.makeText(AddActivity.this, "Error.....", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }
}
