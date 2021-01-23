package com.example.onlinevotingapp.Fragmnet;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinevotingapp.R;
import com.example.onlinevotingapp.databinding.FragmentLoginBinding;

import java.util.concurrent.Executor;


public class loginFragment extends Fragment {
    private FragmentLoginBinding binding;

    TextView msg_text;

    public loginFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentLoginBinding.inflate(inflater);
        setHasOptionsMenu(true);
        return binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        msg_text=binding.textMsg;

        BiometricManager biometricManager= BiometricManager.from(getContext());
        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                msg_text.setText("use fingerprint");
                binding.loginbtn.setVisibility(View.VISIBLE);
                break;

            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                msg_text.setText("This device don't have a fingerprint sensor");
                binding.loginbtn.setVisibility(View.GONE);
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                msg_text.setText("Currently unavailable");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                msg_text.setText("Register please");
                binding.loginbtn.setVisibility(View.GONE);
                break;

        }
        Executor executor= ContextCompat.getMainExecutor(getContext());
        BiometricPrompt biometricPrompt=new BiometricPrompt(getActivity(), executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment);
                Toast.makeText(getContext(), "Log in successful", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

       BiometricPrompt.PromptInfo promptInfo= new BiometricPrompt.PromptInfo.Builder()
               .setTitle("Log in")
               .setDescription("Use your Fingerprint")
               .setNegativeButtonText("cancel")
               .build();


       binding.loginbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               biometricPrompt.authenticate(promptInfo);
           }
       });






    }
}