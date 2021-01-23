package com.example.onlinevotingapp.Fragmnet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlinevotingapp.Adapter.AdminResultAdapter;
import com.example.onlinevotingapp.Model.ElectionModel;
import com.example.onlinevotingapp.R;
import com.example.onlinevotingapp.ViewModel.ViewModel;
import com.example.onlinevotingapp.databinding.FragmentAdminHomeBinding;

import java.util.List;

public class AdminHomeFragment extends Fragment {
    FragmentAdminHomeBinding binding;
    private ViewModel viewModel;


    public AdminHomeFragment() {
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentAdminHomeBinding.inflate(inflater);
        viewModel=new ViewModelProvider(getActivity()).get(ViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.fetchAllElecton().observe(getViewLifecycleOwner(), new Observer<List<ElectionModel>>() {
            @Override
            public void onChanged(List<ElectionModel> electionModels) {
                final AdminResultAdapter adapter=new AdminResultAdapter(electionModels,getActivity());
                final LinearLayoutManager llm=new LinearLayoutManager(getActivity());
                binding.resultAdminRv.setLayoutManager(llm);
                binding.resultAdminRv.setAdapter(adapter);
            }
        });



        binding.fbId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_adminHomeFragment_to_createNewElectionFragment);

            }
        });


    }
}