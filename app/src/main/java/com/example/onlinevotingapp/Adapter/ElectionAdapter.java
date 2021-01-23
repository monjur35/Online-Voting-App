package com.example.onlinevotingapp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinevotingapp.Model.ElectionModel;
import com.example.onlinevotingapp.R;

import java.util.List;

public class ElectionAdapter extends RecyclerView.Adapter<ElectionAdapter.ElectionViewHolder> {
    private List<ElectionModel> electionModelList;
    private Context context;

    public ElectionAdapter(List<ElectionModel> electionModelList, Context context) {
        this.electionModelList = electionModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ElectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView= LayoutInflater.from(context).inflate(R.layout.election_row,parent,false);
        Log.e("TAG", "onCreateViewHolder: " );
        return new ElectionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ElectionViewHolder holder, int position) {
        Log.e("TAG", "onBindViewHolder: ");
        holder.positionName.setText(electionModelList.get(position).getPositionName());
        holder.date.setText(electionModelList.get(position).getFormattedDate());
        holder.startTime.setText(electionModelList.get(position).getStartTime());
        holder.endTime.setText(electionModelList.get(position).getEndTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Bundle bundle=new Bundle();
                bundle.putString("ElectionId",electionModelList.get(position).getElectionID());
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_voteCenterFragment,bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.e("TAG", "getItemCount: "+electionModelList.size() );
        return electionModelList.size();
    }

    class ElectionViewHolder extends RecyclerView.ViewHolder{
        TextView positionName,date,startTime,endTime;

        public ElectionViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.e("TAG", "ElectionViewHolder: " );
            positionName=itemView.findViewById(R.id.positionNAme);
            date=itemView.findViewById(R.id.dateTv);
            startTime=itemView.findViewById(R.id.startTimeTV);
            endTime=itemView.findViewById(R.id.endTimeTV);
        }
    }
}
