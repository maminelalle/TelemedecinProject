package com.example.telemedecin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> implements Filterable {

    private Context context;
    private List<Doctor> doctorList;
    private List<Doctor> doctorListFull;

    public DoctorAdapter(Context context, List<Doctor> doctorList) {
        this.context = context;
        this.doctorList = new ArrayList<>(doctorList);
        this.doctorListFull = new ArrayList<>(doctorList);
    }

    @Override
    public DoctorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_doctor, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DoctorViewHolder holder, int position) {
        Doctor doctor = doctorList.get(position);
        holder.txtName.setText(doctor.getName());
        holder.txtSpeciality.setText(doctor.getSpeciality());
        holder.txtnumero.setText(doctor.getnumero());
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    @Override
    public Filter getFilter() {
        return doctorFilter;
    }

    private Filter doctorFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Doctor> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(doctorListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Doctor doctor : doctorListFull) {
                    if (doctor.getName().toLowerCase().contains(filterPattern) ||
                            doctor.getSpeciality().toLowerCase().contains(filterPattern)) {
                        filteredList.add(doctor);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            doctorList.clear();
            doctorList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public static class DoctorViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtSpeciality, txtnumero;

        public DoctorViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtDoctorName);
            txtSpeciality = itemView.findViewById(R.id.txtDoctorSpeciality);
            txtnumero = itemView.findViewById(R.id.txtDoctornumero);
        }
    }
}
