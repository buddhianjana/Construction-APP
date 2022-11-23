package com.example.myapplication.Adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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

import java.util.ArrayList;

public class ViewMasonry extends RecyclerView.Adapter<ViewMasonry.masonryAdapter>{
    Context context;
    ArrayList<Masonry> masonries = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    public ViewMasonry(Context context, int singeldataviewmasonry, ArrayList<Masonry> masonries, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.masonries = masonries;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public masonryAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singeldataviewmasonry, null);
        return new ViewMasonry.masonryAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull masonryAdapter holder, int position) {
        final Masonry masonry = masonries.get(position);
        holder.name.setText(masonry.getName());
        holder.email.setText(masonry.getEmail());
        holder.nic.setText(masonry.getNic());
        holder.phone.setText(masonry.getMobileNo());

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
        Button delete;
        public masonryAdapter(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.masonryName);
            email = itemView.findViewById(R.id.masonryEmail);
            nic = itemView.findViewById(R.id.masonryNIC);
            phone = itemView.findViewById(R.id.masonryPhone);
            delete = itemView.findViewById(R.id.Delete);
        }
    }
}
