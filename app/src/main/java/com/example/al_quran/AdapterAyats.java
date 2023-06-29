package com.example.al_quran;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.al_quran.Models.ArtiModel.TranslationsItem;
import com.example.al_quran.Models.AyatModel.VersesItem;

import java.util.List;

public class AdapterAyats extends RecyclerView.Adapter<AdapterAyats.AyatViewHolder> {
    private List<VersesItem> results;
    private List<TranslationsItem> result1;

    public AdapterAyats(List<VersesItem> results, List<TranslationsItem> result1) {
        this.results = results;
        this.result1 = result1;
    }

    @NonNull
    @Override
    public AyatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AyatViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.ayat, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AyatViewHolder holder, int position) {
        VersesItem result = results.get(position);
        TranslationsItem results1 = result1.get(position);

        holder.textViewAyat.setText(result.getTextUthmani());
        holder.textViewArti.setText(results1.getText());
    }
    @Override
    public int getItemCount() {
        return results.size();
    }

    public class AyatViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAyat, textViewArti;
        public AyatViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAyat = itemView.findViewById(R.id.tvAyat);
            textViewArti = itemView.findViewById(R.id.tvTerjemahanAyat);
        }
    }

    public void setData(List<VersesItem> data, List<TranslationsItem> data1){
        results.clear();
        results.addAll(data);

        result1.clear();
        result1.addAll(data1);
        notifyDataSetChanged();
    }


}
