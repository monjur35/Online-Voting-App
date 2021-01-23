package com.example.onlinevotingapp.Fragmnet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.onlinevotingapp.Model.BallotBoxModel;
import com.example.onlinevotingapp.Model.ElectionModel;
import com.example.onlinevotingapp.R;
import com.example.onlinevotingapp.ViewModel.ViewModel;
import com.example.onlinevotingapp.databinding.FragmentVoteCenterBinding;

import java.util.List;


public class VoteCenterFragment extends Fragment {
    private FragmentVoteCenterBinding binding;
    private ViewModel viewModel;
    public  String votedCandidateName;
    private ElectionModel electionModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentVoteCenterBinding.inflate(inflater);
        viewModel= new ViewModelProvider(getActivity()).get(ViewModel.class);
        electionModel=new ElectionModel();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Bundle bundle=getArguments();
            final String elecId=bundle.getString("ElectionId");
            viewModel.fetchElectonById(elecId).observe(getViewLifecycleOwner(), new Observer<ElectionModel>() {
                @Override
                public void onChanged(ElectionModel electionModels) {
                    binding.positionNameInVotecenter.setText(electionModels.getPositionName());
                    binding.candidate1Radio.setText(electionModels.getCandidate1());
                    binding.candidate2Radio.setText(electionModels.getCandidate2());
                    binding.candidate3Radio.setText(electionModels.getCandidate3());
                    votedCandidateName=electionModels.getCandidate1();
                }
            });


        binding.radioGroupId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                final RadioButton rb=radioGroup.findViewById(i);
                votedCandidateName=rb.getText().toString();

            }
        });
        binding.voteBtnid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final BallotBoxModel ballotBoxModel=new BallotBoxModel(null,votedCandidateName,elecId);
                viewModel.giveVote(ballotBoxModel,electionModel);
                binding.voteBtnid.setVisibility(View.GONE);
                Navigation.findNavController(view).navigate(R.id.action_voteCenterFragment_to_homeFragment2);

            }
        });





    }
}