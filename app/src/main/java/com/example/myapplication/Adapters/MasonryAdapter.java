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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DataBase.DB;
import com.example.myapplication.DataBase.Masonry;
import com.example.myapplication.R;
import com.example.myapplication.addmasonryworkers;

import java.util.ArrayList;

public class MasonryAdapter extends RecyclerView.Adapter<MasonryAdapter.masonryAdapter>{

    Context context;
    ArrayList<Masonry> masonries = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    public MasonryAdapter(Context context, int singledataformasonry, ArrayList<Masonry> masonries, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.masonries = masonries;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public masonryAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singledataformasonry, null);
        return new masonryAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull masonryAdapter holder, int position) {
        final Masonry masonry = masonries.get(position);
        holder.name.setText(masonry.getName());
        holder.email.setText(masonry.getEmail());
        holder.nic.setText(masonry.getNic());
        holder.phone.setText(masonry.getMobileNo());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id",masonry.getId());
                bundle.putString("name", masonry.getName());
                bundle.putString("email", masonry.getEmail());
                bundle.putString("nic", masonry.getNic());
                bundle.putString("mobile", masonry.getMobileNo());
                Intent intent = new Intent(context, addmasonryworkers.class);
                intent.putExtra("userdata", bundle);
                context.startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            DB db = new DB(context);
            @Override
            public void onClick(View v) {
                sqLiteDatabase =db.getReadableDatabase();
                long delete=sqLiteDatabase.delete("Masonry","id="+masonry.getId(),null);
                if (delete != -1) {
                    Toast.makeText(context,"Deleted Successfully",Toast.LENGTH_SHORT).show();
                    masonries.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return masonries.size();
    }

    public class masonryAdapter extends RecyclerView.ViewHolder {
        TextView name, email, nic, phone;
        Button edit, delete;
        public masonryAdapter(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.masonryName);
            email = itemView.findViewById(R.id.masonryEmail);
            nic = itemView.findViewById(R.id.masonryNIC);
            phone = itemView.findViewById(R.id.masonryPhone);
            edit = itemView.findViewById(R.id.Edit);
            delete = itemView.findViewById(R.id.Delete);
        }
    }
}
