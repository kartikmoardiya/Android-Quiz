package com.example.quizapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.Models.Questions;
import com.example.quizapp.R;

import java.util.ArrayList;

public class QueBankAdapter extends RecyclerView.Adapter<QueBankAdapter.ViewHolder>{

    ArrayList<Questions> list = new ArrayList<>();

    public QueBankAdapter(ArrayList<Questions> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_que_bank, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Questions item = list.get(position);
        int queNumber = position + 1;
        holder.txtQue.setText(queNumber+". "+item.getQue());
        holder.txtAns.setText("Ans. "+item.getAnswer());
        holder.txtLevel.setText("Level: "+item.getLevel());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtQue, txtAns, txtLevel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQue = itemView.findViewById(R.id.txtQue);
            txtAns = itemView.findViewById(R.id.txtAns);
            txtLevel = itemView.findViewById(R.id.txtLevel);
        }
    }
}
