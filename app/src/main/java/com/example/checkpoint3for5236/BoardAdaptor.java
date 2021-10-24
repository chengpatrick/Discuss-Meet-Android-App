package com.example.checkpoint3for5236;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BoardAdaptor extends RecyclerView.Adapter<BoardViewHolder> {


    Context context;
    List<BoardItem> items;

    public BoardAdaptor(Context context, List<BoardItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BoardViewHolder(LayoutInflater.from(context).inflate(R.layout.discussion_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        holder.button.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
