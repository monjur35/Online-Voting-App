package com.example.onlinevotingapp.ViewModel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.example.onlinevotingapp.Fragmnet.HomeFragment;
import com.example.onlinevotingapp.Model.BallotBoxModel;
import com.example.onlinevotingapp.Model.ElectionModel;
import com.example.onlinevotingapp.Repository.FirebaseRepository;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private FirebaseRepository repository;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository=new FirebaseRepository();

    }
    public void createNewElection(ElectionModel electionModel){
        repository.addNewElection(electionModel);
    }
    public MutableLiveData<List<ElectionModel>> fetchAllElecton(){
        return repository.getAllElection();
    }
    public MutableLiveData<ElectionModel>fetchElectonById(String id){
        return repository.getElectionByID(id);
    }

    public void giveVote(BallotBoxModel ballotBoxModel,ElectionModel electionModel){
        repository.giveVote(ballotBoxModel,electionModel);
    }

}
