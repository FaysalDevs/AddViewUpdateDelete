package com.advance.updatedeletefromfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void add(View view) {
        Intent intent = new Intent(MainActivity.this,AddActivity.class);
        startActivity(intent);
    }

    public void view(View view) {
        Intent intent = new Intent(MainActivity.this,ViewActivity.class);
        startActivity(intent);
    }

    public void update(View view) {
        Intent intent = new Intent(MainActivity.this,UpdateActivity.class);
        startActivity(intent);
    }

    public void delete(View view) {
        Intent intent = new Intent(MainActivity.this,DeleteActivity.class);
        startActivity(intent);
    }
}
