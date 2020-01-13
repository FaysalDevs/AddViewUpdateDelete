package com.advance.updatedeletefromfirebase;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends ArrayAdapter {

    private Activity context;
    private List<ProductData> productDataList;


    public ProductAdapter(Activity context,List<ProductData> productData) {
        super(context, R.layout.list_layout,productData);
        this.context=context;
        this.productDataList = productData;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView textProductName = listViewItem.findViewById(R.id.productnameID);
        TextView textProductStock = listViewItem.findViewById(R.id.productastockId);

        ProductData productData = productDataList.get(position);

        textProductName.setText(productData.getpName());
        textProductStock.setText(productData.getpQuantity());

        return listViewItem;
    }
}
