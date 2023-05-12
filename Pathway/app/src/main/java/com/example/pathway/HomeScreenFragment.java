package com.example.pathway;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.pathway.databinding.HomeScreenFragmentBinding;
import com.example.pathway.databinding.InputPageFragmentBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeScreenFragment extends Fragment {
    private HomeScreenFragmentBinding binding;
    TextView welcomeText;
    TextView goalText;
    TextView firstListCost;
    TextView secondListCost;
    TextView thirdListCost;

    Button detailsPageButton;

    DatabaseReference myRef;
    String userId;
    FirebaseUser user;
    UserData currentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomeScreenFragmentBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        welcomeText = (TextView) getView().findViewById(R.id.welcomeText);
        //goalText = (TextView) getView().findViewById(R.id.goalText);
        firstListCost = (TextView) getView().findViewById(R.id.firstListCosts);
        secondListCost = (TextView) getView().findViewById(R.id.secondListCosts);
        thirdListCost = (TextView) getView().findViewById(R.id.thirdListCosts);
        detailsPageButton = (Button) getView().findViewById(R.id.detailsButton);

        detailsPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(HomeScreenFragment.this)
                        .navigate(R.id.action_Home_to_input);
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("userData");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentUser = (UserData) snapshot.child(userId).getValue(UserData.class);
                display();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("InputPageFragment", "Failed to read value.", error.toException());
            }
        });
    }


    public void display(){
        welcomeText.setText("Welcome, " + currentUser.getFullName() +"!");
        //goalText.setText("Goal: " + currentUser.getLongTermGoal());
        firstListCost.setText("$" + currentUser.getTotalCost());
        secondListCost.setText("$" + currentUser.getTotalIncome());
        thirdListCost.setText("$" + currentUser.getTotalSavings());
    }

}