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
import com.example.myapplication.DataBase.Supervisor;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ViewSupervisor extends RecyclerView.Adapter<ViewSupervisor.supervisorAdapter>{
    Context context;
    ArrayList<Supervisor> supervisiors = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    public ViewSupervisor(Context context, int singeldataviewsupervisor, ArrayList<Supervisor> supervisiors, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.supervisiors = supervisiors;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public supervisorAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singeldataviewsupervisor, null);
        return new ViewSupervisor.supervisorAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull supervisorAdapter holder, int position) {
        final Supervisor supervisior = supervisiors.get(position);
        holder.name.setText(supervisior.getName());
        holder.email.setText(supervisior.getEmail());
        holder.password.setText(supervisior.getPassword());
        holder.cpassword.setText(supervisior.getConfirm_Password());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            DB db = new DB(context);
            @Override
            public void onClick(View v) {
                sqLiteDatabase =db.getReadableDatabase();
                long delete=sqLiteDatabase.delete("Supervisor","id="+supervisior.getId(),null);
                if (delete != -1) {
                    Toast.makeText(context,"Deleted Successfully",Toast.LENGTH_SHORT).show();
                    supervisiors.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return supervisiors.size();
    }

    public class supervisorAdapter extends RecyclerView.ViewHolder {
        TextView name, email, password, cpassword;
        Button delete;
        public supervisorAdapter(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.supervisorName);
            email = itemView.findViewById(R.id.supervisorEmail);
            password = itemView.findViewById(R.id.supervisorPassword);
            cpassword = itemView.findViewById(R.id.supervisorcpassword);
            delete = itemView.findViewById(R.id.Delete);
        }
    }
}
