package com.example.telemedecin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RendezVousAdapter extends RecyclerView.Adapter<RendezVousAdapter.ViewHolder> {
    private List<RendezVous> rendezVousList;

    public RendezVousAdapter(List<RendezVous> rendezVousList) {
        this.rendezVousList = rendezVousList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rendezvous, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RendezVous rendezVous = rendezVousList.get(position);
        holder.date.setText(rendezVous.getDate());
        holder.heure.setText(rendezVous.getHeure());
        holder.motif.setText(rendezVous.getMotif());
    }

    @Override
    public int getItemCount() {
        return rendezVousList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date, heure, motif;

        public ViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.textDate);
            heure = view.findViewById(R.id.textHeure);
            motif = view.findViewById(R.id.textMotif);
        }
    }
}