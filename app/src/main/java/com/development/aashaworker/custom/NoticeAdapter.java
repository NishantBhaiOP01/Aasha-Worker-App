package com.development.aashaworker.custom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.development.aashaworker.R;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.Holder> {
    private final List<Integer> notices; // drawable IDs

    public NoticeAdapter(List<Integer> notices) {
        this.notices = notices;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_carousel_image, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.imageView.setImageResource(notices.get(position));
    }

    @Override
    public int getItemCount() {
        return notices.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
