package com.example.myapplication.Adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DataBase.AddSite;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ConstructionAdapter extends RecyclerView.Adapter<ConstructionAdapter.constructionAdapter>{
    Context context;
    ArrayList<AddSite> addSites = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    public ConstructionAdapter(Context context, int singledataforconstruction, ArrayList<AddSite> addSites, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.addSites = addSites;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public constructionAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singledataforconstruction, null);
        return new constructionAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull constructionAdapter holder, int position) {
        final AddSite addSite = addSites.get(position);
        holder.sitename.setText(addSite.getSitename());
        holder.sitelocation.setText(addSite.getSitelocation());
    }

    @Override
    public int getItemCount() {
        return addSites.size();
    }

    public class constructionAdapter extends RecyclerView.ViewHolder {
        TextView sitename, sitelocation;
        public constructionAdapter(@NonNull View itemView) {
            super(itemView);
            sitename = itemView.findViewById(R.id.siteName);
            sitelocation = itemView.findViewById(R.id.siteLocation);
        }
    }
}
