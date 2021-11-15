package com.example.checkpoint3for5236;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder>{
    private ArrayList<Meeting> mMeetingList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class MeetingViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        //public Button button;

        public MeetingViewHolder(View itemView, final OnItemClickListener listener) {
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

    public MeetingAdapter(ArrayList<Meeting> exampleList) {
        mMeetingList = exampleList;
    }

    @Override
    public MeetingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_item, parent, false);
        MeetingViewHolder evh = new MeetingViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(MeetingViewHolder holder, int position) {
        Meeting currentItem = mMeetingList.get(position);

        holder.mTextView1.setText(currentItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return mMeetingList.size();
    }

}
