package com.example.checkpoint3for5236;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DiscussionAdapter extends RecyclerView.Adapter<DiscussionAdapter.DiscussionViewHolder>{
    private ArrayList<Discussion> mDiscussionList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class DiscussionViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        //public Button button;

        public DiscussionViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textView);
//            button = itemView.findViewById(R.id.buttonView);

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

    public DiscussionAdapter(ArrayList<Discussion> exampleList) {
        mDiscussionList = exampleList;
    }

    @Override
    public DiscussionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_item, parent, false);
        DiscussionViewHolder evh = new DiscussionViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(DiscussionViewHolder holder, int position) {
        Discussion currentItem = mDiscussionList.get(position);

        holder.mTextView1.setText(currentItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return mDiscussionList.size();
    }

}
