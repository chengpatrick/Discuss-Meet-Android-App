package com.example.checkpoint3for5236;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BoardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    Button button;
    private final Context context;

    public BoardViewHolder(@NonNull View itemView) {
        super(itemView);

        button = itemView.findViewById(R.id.buttonView);
        context = itemView.getContext();
    }

    @Override
    public void onClick(View v) {
        final Intent intent;
        intent =  new Intent(context, DiscussionBoardActivity.class);
        context.startActivity(intent);
    }
}
