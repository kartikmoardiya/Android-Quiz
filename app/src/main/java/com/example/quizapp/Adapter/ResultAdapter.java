package com.example.quizapp.Adapter;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.Models.Questions;
import com.example.quizapp.R;

import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewHolder>{
    ArrayList<Pair<Questions,String>> list = new ArrayList<>();

    public ResultAdapter(ArrayList<Pair<Questions, String>> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_recyclerview,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pair<Questions,String> data = list.get(position);
        int queNumber = position + 1;
        if(data.first.getAnswer().equalsIgnoreCase(data.second))
        {
            holder.imgCorrectOrIncorrect.setImageResource(R.drawable.check);
            holder.txtCorrectAns.setVisibility(View.GONE);
            holder.txtQue.setText(queNumber+". "+data.first.getQue());
            holder.txtUserAns.setText("Your Answer. "+data.second);
        }else{
            holder.imgCorrectOrIncorrect.setImageResource(R.drawable.cross);
            holder.txtCorrectAns.setText("Correct Answer. "+data.first.getAnswer());
            holder.txtQue.setText(queNumber+". "+data.first.getQue());
            holder.txtUserAns.setText("Your Answer. "+data.second);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imgCorrectOrIncorrect;
        TextView txtQue, txtCorrectAns, txtUserAns;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCorrectOrIncorrect = itemView.findViewById(R.id.imgCorrectOrIncorrect);
            txtQue = itemView.findViewById(R.id.txtQue);
            txtCorrectAns = itemView.findViewById(R.id.txtCorrectAns);
            txtUserAns = itemView.findViewById(R.id.txtUserAns);
        }
    }
}
