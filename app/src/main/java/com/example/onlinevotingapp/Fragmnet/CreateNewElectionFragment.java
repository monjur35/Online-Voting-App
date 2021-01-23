package com.example.onlinevotingapp.Fragmnet;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.onlinevotingapp.Model.ElectionModel;
import com.example.onlinevotingapp.R;
import com.example.onlinevotingapp.ViewModel.ViewModel;
import com.example.onlinevotingapp.databinding.FragmentCreateNewElectionBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNewElectionFragment extends Fragment {
    FragmentCreateNewElectionBinding binding;
    private ViewModel viewModel;

    public CreateNewElectionFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentCreateNewElectionBinding.inflate(inflater);
        viewModel=new ViewModelProvider(getActivity()).get(ViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.dateId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDateFromUser();


            }
        });
        binding.startTimeId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                final int mHour = c.get(Calendar.HOUR_OF_DAY);
                final int mMinute = c.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                binding.startTimeId.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

            }
        });
        binding.endTimeId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                final int mHour = c.get(Calendar.HOUR_OF_DAY);
                final int mMinute = c.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                binding.endTimeId.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();



            }
        });
        binding.createBtnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String positionName=binding.positionNameId.getText().toString();
                final String date=binding.dateId.getText().toString();
                final String starTime=binding.startTimeId.getText().toString();
                final String endTime=binding.endTimeId.getText().toString();
                final String candidate1=binding.candidate1.getText().toString();
                final String candidate2=binding.candidate2.getText().toString();
                final String candidate3=binding.candidate3.getText().toString();

                if (positionName.isEmpty() || date.isEmpty() ||starTime.isEmpty()||endTime.isEmpty()||candidate1.isEmpty()||candidate2.isEmpty()||candidate3.isEmpty()){
                    Toast.makeText(getActivity(), "Fill all field", Toast.LENGTH_SHORT).show();
                }
                else {
                    final ElectionModel electionModel=new ElectionModel(null,positionName,date,starTime,endTime,candidate1,candidate2,candidate3,null,null,null);
                    viewModel.createNewElection(electionModel);
                    Navigation.findNavController(view).navigate(R.id.action_createNewElectionFragment_to_adminHomeFragment);

                }

            }
        });
    }

    private void selectDateFromUser() {
        final Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(),listener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();

    }
    private DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            final Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            final String formattedDate = simpleDateFormat.format(calendar.getTime());
            binding.dateId.setText(formattedDate);

        }
    };

}