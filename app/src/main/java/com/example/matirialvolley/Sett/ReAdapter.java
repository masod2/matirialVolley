package com.example.matirialvolley.Sett;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.matirialvolley.R;
import com.example.matirialvolley.databinding.ItemBinding;

import java.util.ArrayList;

public class ReAdapter extends RecyclerView.Adapter<ReAdapter.RecHolder> {
    ArrayList<Datum> data;


    public ReAdapter(ArrayList<Datum> datum ) {
        this.data = datum;
        notifyDataSetChanged();
     }

    @NonNull
    @Override
    public RecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false); // اضافة التصميم
        return new RecHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecHolder holder, int position) {
        Datum datum = data.get(position);
         //
        holder.bind(datum);
    }
//حجم التكرا عدد العناصر
    @Override
    public int getItemCount() {
        return data.size();
    }

    static public class RecHolder extends RecyclerView.ViewHolder {
        ItemBinding binding;
        Context context;

        public RecHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemBinding.bind(itemView);
            // احضار الكونتكس
            context = itemView.getContext();
        }


        //تعبئة بيانات اليوزر بالتصميم
        void bind(Datum datum ) {
            Glide.with(context).load(datum.getPhotoOrderHome().getPhoto()).into(binding.image);
            binding.id.setText(datum.getId()+"");
            binding.workname.setText("Servese tybe :"+datum.getWork().getName());
            binding.date.setText(datum.getCreatedAt());
            binding.date.setText(datum.getCreatedAt());
            Log.d("stateee",datum.getWork().getName());

        }
    }
}
