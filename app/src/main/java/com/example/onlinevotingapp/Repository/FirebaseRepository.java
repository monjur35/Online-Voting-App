package com.example.onlinevotingapp.Repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinevotingapp.Fragmnet.VoteCenterFragment;
import com.example.onlinevotingapp.Model.BallotBoxModel;
import com.example.onlinevotingapp.Model.ElectionModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseRepository {
    private FirebaseFirestore db;
    private final String ELECTION_COLLECTION="Election";
    private final String BALLOT_COLLECTION="Ballot Box";
    private String candidateNAme;
    private String voteForIndividual;

    public FirebaseRepository( ) {
        db=FirebaseFirestore.getInstance();
        Log.e("TAG", "FirebaseRepository:candidateName "+candidateNAme );

        //
    }

    public void addNewElection(ElectionModel electionModel){
        final DocumentReference documentReference=db.collection(ELECTION_COLLECTION).document();
        electionModel.setElectionID(documentReference.getId());
        electionModel.setCandidate1TotalVote(voteForIndividual);
        documentReference.set(electionModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.e("TAG", "onComplete: new election announced ");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("TAG", "onFailure: "+e.getLocalizedMessage() );

            }
        });
    }

    public MutableLiveData<List<ElectionModel>>getAllElection(){
        MutableLiveData<List<ElectionModel>>electionModelLiveData=new MutableLiveData<>();
        db.collection(ELECTION_COLLECTION).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error!=null){
                    return;
                }
                electionModelLiveData.postValue(value.toObjects(ElectionModel.class));
            }
        });
        return electionModelLiveData;
    }

    public MutableLiveData<ElectionModel>getElectionByID(String id){
        MutableLiveData<ElectionModel>electionModelLiveData=new MutableLiveData<>();
        db.collection(ELECTION_COLLECTION).document(id).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    return;
                }
                electionModelLiveData.postValue(value.toObject(ElectionModel.class));
            }
        });
        return electionModelLiveData;
    }

    public void giveVote(BallotBoxModel ballotBoxModel ,ElectionModel electionModel){
        candidateNAme=ballotBoxModel.getVotedCandidate();
        final DocumentReference documentReference=db.collection(BALLOT_COLLECTION).document();
        ballotBoxModel.setBallotPaperId(documentReference.getId());
        documentReference.set(ballotBoxModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.e("TAG", "vote success ");
                getVoteAmount(ballotBoxModel,electionModel);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("TAG", "onFailure: givingVote  :"+e.getLocalizedMessage() );

            }
        });
    }

    public void getVoteAmount(BallotBoxModel ballotBoxModel,ElectionModel electionModel){

        Query documentReference1=db.collection(BALLOT_COLLECTION).whereEqualTo("votedCandidate",candidateNAme);
        documentReference1.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.e("TAG", "" +candidateNAme+" getVote amount : "+task.getResult().size() );
                final String elecID=ballotBoxModel.getElectonID();

                voteForIndividual=String.valueOf(task.getResult().size());

                Map<String,Object> map1=new HashMap<>();
                map1.put("candidate1TotalVote",voteForIndividual);
                map1.put("candidate2TotalVote",voteForIndividual);
                map1.put("candidate3TotalVote",voteForIndividual);

                final DocumentReference documentReference=db.collection(ELECTION_COLLECTION).document(elecID);
                documentReference.update(map1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {




                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });

        Query documentReference2=db.collection(BALLOT_COLLECTION).whereEqualTo("votedCandidate",candidateNAme);




    }
}
