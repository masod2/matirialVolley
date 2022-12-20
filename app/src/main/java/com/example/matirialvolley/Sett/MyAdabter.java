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

public class MyAdabter extends RecyclerView.Adapter<MyAdabter.myHolder> {
    ArrayList<Datum> data = new ArrayList<>();
    Context context;


    @NonNull
    @Override
    public MyAdabter.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false)); // اضاقة الايتم للادابتر
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdabter.myHolder holder, int position) {

        //تعبئة بيانات اليوزر بالتصميم
        Glide.with(context).load(data.get(position).getPhoto()).into(holder.binding.image);
        holder.binding.id.setText(data.get(position).getId() + "");
        holder.binding.workname.setText("Servese tybe :" + data.get(position).getWork().getName());
        holder.binding.date.setText(data.get(position).getCreatedAt());
        Log.d("stateee", data.get(position).getDetailsAddress());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setlist(ArrayList<Datum> datumArrayList) {
        this.data = datumArrayList;
        notifyDataSetChanged();
    }

    public class myHolder extends RecyclerView.ViewHolder {
        ItemBinding binding; // عمل بايندنج للايتم

        public myHolder(@NonNull View itemView) {
            super(itemView);
        }


    }

}
