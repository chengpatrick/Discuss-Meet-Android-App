package com.example.checkpoint3for5236;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BoardViewHolder extends RecyclerView.ViewHolder {

    Button button;

    public BoardViewHolder(@NonNull View itemView) {
        super(itemView);

        button = itemView.findViewById(R.id.buttonView);
    }
}
