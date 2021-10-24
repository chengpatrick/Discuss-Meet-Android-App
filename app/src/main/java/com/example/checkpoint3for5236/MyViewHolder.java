package com.example.checkpoint3for5236;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView topic, sum;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        topic = itemView.findViewById(R.id.topic);
        sum = itemView.findViewById(R.id.sum);
    }
}
