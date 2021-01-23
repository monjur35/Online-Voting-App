package com.example.onlinevotingapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinevotingapp.Model.ElectionModel;
import com.example.onlinevotingapp.R;

import java.util.List;

public class AdminResultAdapter extends RecyclerView.Adapter<AdminResultAdapter.AdminResultViewHolder>{
    private List<ElectionModel> electionModels;
    private Context context;

    public AdminResultAdapter(List<ElectionModel> electionModels, Context context) {
        this.electionModels = electionModels;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView= LayoutInflater.from(context).inflate(R.layout.voting_result_row,parent,false);
        return new AdminResultViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminResultViewHolder holder, int position) {
        holder.positionNAme.setText(electionModels.get(position).getPositionName());
        holder.candidate1.setText(electionModels.get(position).getCandidate1());
        holder.candidate2.setText(electionModels.get(position).getCandidate2());
        holder.candidate3.setText(electionModels.get(position).getCandidate3());
        holder.candidate1Total.setText(electionModels.get(position).getCandidate1TotalVote());
        holder.candidate2Total.setText(electionModels.get(position).getCandidate2TotalVote());
        holder.candidate3Total.setText(electionModels.get(position).getCandidate3TotalVote());
        holder.totatVote.setText(String.valueOf(electionModels.size()));

    }

    @Override
    public int getItemCount() {
        return electionModels.size();
    }

    class AdminResultViewHolder extends RecyclerView.ViewHolder{
        TextView candidate1,candidate2,candidate3,positionNAme,totatVote,candidate1Total,candidate2Total,candidate3Total;

        public AdminResultViewHolder(@NonNull View itemView) {
            super(itemView);
            candidate1=itemView.findViewById(R.id.candidate1NameOnAdmin);
            candidate2=itemView.findViewById(R.id.candidate2NameinAdmin);
            candidate3=itemView.findViewById(R.id.candidate3NameOnAdmin);
            positionNAme=itemView.findViewById(R.id.positionNAmeOnAdmin);
            candidate1Total=itemView.findViewById(R.id.candidate1TotalVoteOnAdmin);
            candidate2Total=itemView.findViewById(R.id.candidate2totalinAdmin);
            candidate3Total=itemView.findViewById(R.id.candidate3TotalOnAdmin);

            totatVote=itemView.findViewById(R.id.totalCountedVote);
        }
    }
}
