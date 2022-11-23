package com.example.myapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Attendancetype;
import com.example.myapplication.DataBase.Masonry;
import com.example.myapplication.R;

import java.util.ArrayList;

public class MarkAttendance  extends RecyclerView.Adapter<MarkAttendance.masonryAdapter>{
    Context context;
    ArrayList<Masonry> masonries = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    public MarkAttendance(Context context, int singledataforattendance, ArrayList<Masonry> masonries, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.masonries = masonries;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public masonryAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singledataforattendance, null);
        return new MarkAttendance.masonryAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull masonryAdapter holder, int position) {
        final Masonry masonry = masonries.get(position);
        holder.name.setText(masonry.getName());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("name", masonry.getName());
                Intent intent = new Intent(context, Attendancetype.class);
                intent.putExtra("userdata", bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return masonries.size();
    }

    public class masonryAdapter extends RecyclerView.ViewHolder {
        TextView name;
        Button edit;
        public masonryAdapter(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.masonryName);

            edit = itemView.findViewById(R.id.Edit);
        }
    }
}
