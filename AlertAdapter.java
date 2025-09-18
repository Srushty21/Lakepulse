package com.example.lakepulse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.AlertViewHolder> {
    private List<Alert> alertList;

    public AlertAdapter(List<Alert> alertList) {
        this.alertList = alertList;
    }

    @NonNull
    @Override
    public AlertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alert_item, parent, false);
        return new AlertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlertViewHolder holder, int position) {
        Alert alert = alertList.get(position);
        holder.alertMessage.setText(alert.getMessage());
        holder.alertTimestamp.setText(alert.getTimestamp());
    }

    @Override
    public int getItemCount() {
        return alertList.size();
    }

    public static class AlertViewHolder extends RecyclerView.ViewHolder {
        TextView alertMessage, alertTimestamp;

        public AlertViewHolder(@NonNull View itemView) {
            super(itemView);
            alertMessage = itemView.findViewById(R.id.alertMessage);
            alertTimestamp = itemView.findViewById(R.id.alertTimestamp);
        }
    }
}
