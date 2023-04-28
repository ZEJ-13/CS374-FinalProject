package com.example.pathway;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.pathway.databinding.RegisterPageFragmentBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisterPageFragment extends Fragment {

    private RegisterPageFragmentBinding binding;
    private EditText editTextEmail, editTextUsername, editTextDOB, editTextPassword, editTextConfirmPassword;
    private Button buttonCreateAccount;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = RegisterPageFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextUsername = view.findViewById(R.id.editTextUsername);
        editTextDOB = view.findViewById(R.id.editTextDOB);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        editTextConfirmPassword = view.findViewById(R.id.editTextConfirmPassword);

        buttonCreateAccount = view.findViewById(R.id.buttonCreateAccount);
        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String username = editTextUsername.getText().toString().trim();
                String dob = editTextDOB.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String confirmPassword = editTextConfirmPassword.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(getContext(), "Please enter username", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(dob)) {
                    Toast.makeText(getContext(), "Please enter date of birth", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "Please enter password", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(confirmPassword)) {
                    Toast.makeText(getContext(), "Please re-enter password", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser user = firebaseAuth.getCurrentUser();

                                DatabaseReference databaseReferenceUsers = firebaseDatabase.getReference().child("users").child(user.getUid());
                                DatabaseReference databaseReferenceUserData = firebaseDatabase.getReference().child("userData").child(user.getUid());
                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("username", username);
                                hashMap.put("dob", dob);
                                databaseReferenceUsers.setValue(hashMap);
                                HashMap<String,Double> costList = new HashMap<String,Double>();
                                costList.put(""+0,2.5);
                                HashMap<String,Double> incomeList = new HashMap<String,Double>();
                                incomeList.put(""+0,5.2);
                                HashMap<String,Double> savingList = new HashMap<String,Double>();
                                savingList.put(""+0,10.1);
                                databaseReferenceUserData.setValue(new UserData("","",costList,incomeList,savingList));

                                Toast.makeText(getContext(), "Account created successfully", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getContext(), "Account creation failed", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
