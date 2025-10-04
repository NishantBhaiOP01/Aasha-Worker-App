package com.development.aashaworker.custom;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.development.aashaworker.PersonDetailsActivity;
import com.development.aashaworker.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class WorkerAdapter extends BaseAdapter {
    private Context context;
    private List<BeneficiaryDetails> workers;

    public WorkerAdapter(Context context, List<BeneficiaryDetails> workers) {
        this.context = context;
        this.workers = workers;
    }

    @Override
    public int getCount() {
        return workers.size();
    }

    @Override
    public Object getItem(int position) {
        return workers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_worker, parent, false);
        }

        BeneficiaryDetails worker = workers.get(position);

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvSubtitle = convertView.findViewById(R.id.tvSubtitle);

        // Set data
        tvName.setText(worker.getName());
        if (worker.getLastVisit()!=null && !worker.getLastVisit().isEmpty()) tvSubtitle.setText("Last Seen : " + worker.getLastVisit());
        else tvSubtitle.setText("Last Seen : " + "Yesterday");



        // Handle click → open details
        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PersonDetailsActivity.class);
            intent.putExtra("id", worker.getId());
            context.startActivity(intent);
        });

        return convertView;
    }
}
