package com.example.countries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private ArrayList<MainModel> arrayList;

    public MainAdapter(Context context, ArrayList<MainModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
        Glide.with(context).load(arrayList.get(position).getFlagUrl()).apply(options).into(holder.flagView);
        holder.countryName.setText(arrayList.get(position).getCountryName());
        try {
            StringBuilder capitals = new StringBuilder();
            if (arrayList.get(position).getCapital().length() == 1) {
                holder.capitalName.setText(arrayList.get(position).getCapital().getString(0));
            } else {
                for (int i = 0; i < arrayList.get(position).getCapital().length(); i++) {
                    if (i == arrayList.get(position).getCapital().length() - 1) {
                        capitals.append(arrayList.get(position).getCapital().get(i));
                    } else {
                        capitals.append(arrayList.get(position).getCapital().get(i)).append(",");
                    }
                }
                holder.capitalName.setText(capitals);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.region.getEditText().setText(arrayList.get(position).getRegion());
        holder.subRegion.getEditText().setText(arrayList.get(position).getSubRegion());
        try {
            StringBuilder borders = new StringBuilder();
            if (arrayList.get(position).getBorders() == null) {
                holder.borders.getEditText().setText("No borders");
            } else {
                for (int i = 0; i < arrayList.get(position).getBorders().length(); i++) {
                    if (i == arrayList.get(position).getBorders().length() - 1) {
                        borders.append(arrayList.get(position).getBorders().get(i));
                    } else {
                        borders.append(arrayList.get(position).getBorders().get(i)).append(",");
                    }
                }
                holder.borders.getEditText().setText(borders);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONObject languageObject = arrayList.get(position).getLanguages();
            Iterator<String> keys = languageObject.keys();
            StringBuilder languages = new StringBuilder();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                String value = languageObject.getString(key);
                languages.append(value).append(",");
            }
            holder.languages.getEditText().setText(languages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.population.getEditText().setText(arrayList.get(position).getPopulation());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView flagView;
        private TextView countryName, capitalName;
        private TextInputLayout region, subRegion, population, borders, languages;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flagView = itemView.findViewById(R.id.imageview);
            countryName = itemView.findViewById(R.id.country_name);
            capitalName = itemView.findViewById(R.id.capital);
            region = itemView.findViewById(R.id.Region);
            subRegion = itemView.findViewById(R.id.Subregion);
            population = itemView.findViewById(R.id.Population);
            borders = itemView.findViewById(R.id.Borders);
            languages = itemView.findViewById(R.id.Languages);
        }
    }
}
