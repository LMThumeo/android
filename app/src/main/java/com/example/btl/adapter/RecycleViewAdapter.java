package com.example.btl.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.btl.R;
import com.example.btl.model.StudyClass;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder> {

    private List<StudyClass> list;
    private ItemListener itemListener;

    public RecycleViewAdapter() {
        this.list = new ArrayList<>();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setList(List<StudyClass> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public StudyClass getItem(int pos) {
        return list.get(pos);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.today_class_item, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        StudyClass item = list.get(position);
        holder.subject.setText(item.getSubject());
        holder.group.setText(item.getGroup());
        holder.room.setText(item.getRoom());
        holder.timeStart.setText(item.getTimeStart());
        holder.timeEnd.setText(item.getTimeEnd());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView subject, group, room, timeStart, timeEnd;

        public HomeViewHolder(@NonNull View view) {
            super(view);
            subject = view.findViewById(R.id.tvSubject);
            group = view.findViewById(R.id.tvGroup);
            room = view.findViewById(R.id.tvRoom);
            timeStart = view.findViewById(R.id.tvTimeStart);
            timeEnd = view.findViewById(R.id.tvTimeEnd);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemListener != null) {
                itemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemListener {
        void onItemClick(View view, int pos);
    }
}