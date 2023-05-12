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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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
                                ArrayList<CostSpecific> costList = new ArrayList<>();
                                ArrayList<CostSpecific> incomeList = new ArrayList<>();
                                ArrayList<CostSpecific> savingList = new ArrayList<>();
                                databaseReferenceUserData.setValue(new UserData(username,"",costList,incomeList,savingList));
                                Toast.makeText(getContext(), "Account created successfully", Toast.LENGTH_SHORT).show();
                                // Navigate to the login page
                                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
                                navController.navigate(R.id.action_Register_to_Login);
                            } else {
                                Toast.makeText(getContext(), "Failed to create account", Toast.LENGTH_SHORT).show();
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
