package com.example.demo_proj;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class boxItemAdapter  extends RecyclerView.Adapter<boxItemAdapter.ViewHolder> {

    public static boxItemAdapter instance;
    int itemList;
    Context context;
    public Map<String, String> map = new HashMap<>();

    public boxItemAdapter(int data, Context context) {
        this.itemList = data;
        this.context = context;
        instance = this;
    }

    @NonNull
    @Override
    public boxItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_checkboxes, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull boxItemAdapter.ViewHolder holder, int position) {

        holder.ll_box_items.addView(addCheckBox(position));
    }

    private View addCheckBox(final int position) {
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(context);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        for(int i =0; i < position; i++) {
            CheckBox cb = new CheckBox(context);
            cb.setText(""+(i+1));
            linearLayout.addView(cb);

            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    final boolean newValue = cb.isChecked();
//                    mCheckedItems.put(position, newValue);
//                    map.put("Row"+position+" "+"C"+cb.getText() , "("+newValue+")");
                    map.put(position + cb.getText().toString() , "("+newValue+")");
                }
            });
        }

        horizontalScrollView.addView(linearLayout);
        return horizontalScrollView;
    }

    @Override
    public int getItemCount() {
        return (itemList+1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout ll_box_items;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ll_box_items = itemView.findViewById(R.id.ll_box_items);
        }
    }
}
