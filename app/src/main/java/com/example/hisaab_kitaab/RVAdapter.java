package com.example.hisaab_kitaab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.CollationElementIterator;
import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder>{

    private ArrayList<KhataModel> khataModelArrayList;
    private Context context;

    public RVAdapter(ArrayList<KhataModel> khataModelArrayList, Context context){
        this.khataModelArrayList = khataModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listtile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        KhataModel model = khataModelArrayList.get(position);
        holder.nameTV.setText(model.getRecipient());
        holder.dateTV.setText(model.getDate());
        holder.amountTV.setText(model.getAmount());
        holder.typeTV.setText(model.getType());
        
    }

    @Override
    public int getItemCount() {
        return khataModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTV,dateTV,amountTV,typeTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV=itemView.findViewById(R.id.name);
            dateTV=itemView.findViewById(R.id.date);
            amountTV=itemView.findViewById(R.id.amount);
            typeTV=itemView.findViewById(R.id.type);

        }
    }
}
