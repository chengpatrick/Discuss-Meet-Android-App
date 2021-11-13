package com.example.checkpoint3for5236;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder>{
    private ArrayList<Class> mClassList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ClassViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;

        public ClassViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public ClassAdapter(ArrayList<Class> exampleList) {
        mClassList = exampleList;
    }

    @Override
    public ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_item, parent, false);
        ClassViewHolder evh = new ClassViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ClassViewHolder holder, int position) {
        Class currentItem = mClassList.get(position);

        holder.mTextView1.setText(currentItem.getClassname());
    }

    @Override
    public int getItemCount() {
        return mClassList.size();
    }

}
