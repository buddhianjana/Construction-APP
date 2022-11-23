package com.example.myapplication.Adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DataBase.Attendance;
import com.example.myapplication.R;

import java.util.ArrayList;

public class CalculateSalary extends RecyclerView.Adapter<CalculateSalary.constructionAdapter>{
    Context context;
    ArrayList<Attendance> addSites = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    public CalculateSalary(Context context, int singledataforcalculate, ArrayList<Attendance> addSites, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.addSites = addSites;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public constructionAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singledataforcalculate, null);
        return new CalculateSalary.constructionAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull constructionAdapter holder, int position) {
        final Attendance addSite = addSites.get(position);
        holder.name.setText(addSite.getName());
        holder.days.setText(addSite.getDays());
    }

    @Override
    public int getItemCount() {
        return addSites.size();
    }

    public class constructionAdapter extends RecyclerView.ViewHolder {
        TextView name,days;

        public constructionAdapter(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.mName);
            days = itemView.findViewById(R.id.Day);

        }
    }
}
