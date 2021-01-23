package com.example.onlinevotingapp.Fragmnet;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlinevotingapp.Adapter.ElectionAdapter;
import com.example.onlinevotingapp.Model.ElectionModel;
import com.example.onlinevotingapp.R;
import com.example.onlinevotingapp.ViewModel.ViewModel;
import com.example.onlinevotingapp.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;


public class HomeFragment extends Fragment {
    private ViewModel viewModel;
    private FirebaseAuth auth;
    FragmentHomeBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        binding= FragmentHomeBinding.inflate(inflater);
        viewModel= new ViewModelProvider(getActivity()).get(ViewModel.class);
        auth=FirebaseAuth.getInstance();
        return binding.getRoot();
    }



    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_admin,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.admin){
            adminLogInDialog();
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.fetchAllElecton().observe(getViewLifecycleOwner(), new Observer<List<ElectionModel>>() {
            @Override
            public void onChanged(List<ElectionModel> electionModels) {

                if (electionModels.size()==0){
                    Toast.makeText(getContext(), "No Upcoming Election", Toast.LENGTH_SHORT).show();
                }
                else {
                    final ElectionAdapter electionAdapter=new ElectionAdapter(electionModels,getActivity());
                    Log.e("TAG", "else : "+electionModels.size());
                    final LinearLayoutManager llm=new LinearLayoutManager(getContext());
                    binding.UpcomingEventRv.setLayoutManager(llm);
                    binding.UpcomingEventRv.setAdapter(electionAdapter);
                }
            }
        });


    }




    private void adminLogInDialog() {
        final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Enter admin email & password");
        final View layout=LayoutInflater.from(getContext()).inflate(R.layout.admin_login_dialog,null,true);
        builder.setView(layout);
        AlertDialog dialog=builder.create();
        dialog.show();
        final Button loginbtn=layout.findViewById(R.id.loginAdmin);
        final EditText emailET=layout.findViewById(R.id.mailEditText);
        final EditText passET=layout.findViewById(R.id.passEditText);



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email=emailET.getText().toString().trim();
                final String pass=passET.getText().toString().trim();
                if (email.isEmpty() || pass.isEmpty()){
                    Toast.makeText(getContext(), "fill all field", Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "onClick: "+email );
                }
                else {
                    adminLogIn(email,pass);
                    dialog.dismiss();


                }

            }
        });





    }

    public void adminLogIn(String email,String pass){
        auth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_adminHomeFragment);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "You are not admin", Toast.LENGTH_LONG).show();

            }
        });


    }


}