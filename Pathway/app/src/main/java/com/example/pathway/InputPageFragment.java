package com.example.pathway;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.pathway.databinding.InputPageFragmentBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Creates TableLayout programmatically to support dynamic changes
 * ScrollView and TableLayout formatted in input_page_fragment.xml
 *
 * Upon data change(delete or add), clearView() is called. Removing all child from every table rows and
 * recreate View using display()
 *
 * display()'s structure:
 * create first row of the first list (List of Cost)
 * create body of first list
 * create input row
 *
 * create first row of the second list (List of Income)
 * create body of second list
 * create input row
 *
 * create first row of the third list (List of Saving)
 * create body of third list
 * create input row
 *
 * Row creation has numList in its constructor to differentiate among lists.
 *
 *
 */
public class InputPageFragment extends Fragment {
    private InputPageFragmentBinding binding;
    private Context myContext;

    DatabaseReference myRef;
    FirebaseUser user;
    TableLayout myTable;

    TableRow firstList;
    TableRow firstInput;
    EditText firstCostNameInput;
    EditText firstCostInput;

    TableRow secondList;
    TableRow secondInput;
    EditText secondCostNameInput;
    EditText secondCostInput;


    TableRow thirdList;
    TableRow thirdInput;
    EditText thirdCostNameInput;
    EditText thirdCostInput;


    DisplayMetrics metrics ;
    String userID;
    float scale;
    int dipConvert;
    final int textSize = 18;
    int leftPadding;
    int topPadding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = InputPageFragmentBinding.inflate(inflater,container,false);
        return binding.getRoot();}



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        if(myContext==null){
            myContext=getContext();
        }
        myTable = (TableLayout) getView().findViewById(R.id.table_view);
        myRef = database.getReference("userData");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        scale = myContext.getResources().getDisplayMetrics().density;
        metrics = myContext.getResources().getDisplayMetrics();
        dipConvert = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, metrics);
        leftPadding = (int) (10 * scale + 0.5f);
        topPadding = (int) (20 * scale + 0.5f);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserData currentUser = (UserData) snapshot.child(userID).getValue(UserData.class);
                display(currentUser);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("InputPageFragment", "Failed to read value.", error.toException());
            }
        });
    }



    public void display(UserData currentUser){
        firstList = new TableRow(myContext);

        createFirstLineTableRow(firstList,1);
        createTableRowBody(currentUser.getListOfCost(),currentUser,1);
        firstInput = new TableRow(myContext);
        //firstInput.setPadding(0,2,0,0);

        firstCostNameInput = new EditText(myContext);
        firstCostNameInput.setPadding(leftPadding,0,0,0);
        firstCostNameInput.setInputType(InputType.TYPE_CLASS_TEXT);
        firstCostNameInput.setHint("Enter name of cost...");
        firstCostNameInput.setWidth(dipConvert);
        firstCostNameInput.setBackgroundResource(R.drawable.back);
        firstCostNameInput.setTextSize(textSize);
        firstInput.addView(firstCostNameInput);

        firstCostInput = new EditText(myContext);
        firstCostInput.setPadding(leftPadding,0,0,0);
        firstCostInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        firstCostInput.setHint("Enter cost...");
        firstCostInput.setWidth(dipConvert);
        firstCostInput.setBackgroundResource(R.drawable.back);
        firstCostInput.setTextSize(textSize);
        firstInput.addView(firstCostInput);


        ImageView firstButton = new ImageView(myContext);
        firstButton.setBackgroundResource(R.drawable.add_button);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String costName = firstCostNameInput.getText().toString();
                String cost = firstCostInput.getText().toString();
                if(costName.equals("") || cost.equals("")){
                    //tell user to make complete input
                }
                else{
                    try {
                        CostSpecific temp = new CostSpecific(costName,Double.parseDouble(cost));
                        currentUser.getListOfCost().add(temp);
                        myRef.child(userID).setValue(currentUser);
                        clearView();
                    }catch(Exception e){
                        //tell user to make proper input
                    }
                }

            }
        });
        firstInput.addView(firstButton);
        myTable.addView(firstInput);

        secondList = new TableRow(myContext);
        createFirstLineTableRow(secondList,2);
        createTableRowBody(currentUser.getListOfIncome(),currentUser,2);

        secondInput = new TableRow(myContext);
        //secondInput.setPadding(0,2,0,0);

        secondCostNameInput = new EditText(myContext);
        secondCostNameInput.setPadding(leftPadding,0,0,0);
        secondCostNameInput.setInputType(InputType.TYPE_CLASS_TEXT);
        secondCostNameInput.setHint("Enter name of cost...");
        secondCostNameInput.setWidth(dipConvert);
        secondCostNameInput.setBackgroundResource(R.drawable.back);
        secondCostNameInput.setTextSize(textSize);
        secondInput.addView(secondCostNameInput);

        secondCostInput = new EditText(myContext);
        secondCostInput.setPadding(leftPadding,0,0,0);
        secondCostInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        secondCostInput.setHint("Enter cost...");
        secondCostInput.setWidth(dipConvert);
        secondCostInput.setBackgroundResource(R.drawable.back);
        secondCostInput.setTextSize(textSize);
        secondInput.addView(secondCostInput);


        ImageView secondButton = new ImageView(myContext);
        secondButton.setBackgroundResource(R.drawable.add_button);
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String costName = secondCostNameInput.getText().toString();
                String cost = secondCostInput.getText().toString();
                if(costName.equals("") || cost.equals("")){
                    //tell user to make complete input
                }
                else{
                    try {
                        CostSpecific temp = new CostSpecific(costName,Double.parseDouble(cost));
                        currentUser.getListOfIncome().add(temp);
                        myRef.child(userID).setValue(currentUser);
                        clearView();
                    }catch(Exception e){
                        //tell user to make proper input
                    }
                }

            }
        });
        secondInput.addView(secondButton);
        myTable.addView(secondInput);

        thirdList = new TableRow(myContext);
        createFirstLineTableRow(thirdList,3);
        createTableRowBody(currentUser.getListOfSavings(),currentUser,3);

        thirdInput = new TableRow(myContext);
        //thirdInput.setPadding(0,2,0,0);

        thirdCostNameInput = new EditText(myContext);
        thirdCostNameInput.setPadding(leftPadding,0,0,0);
        thirdCostNameInput.setInputType(InputType.TYPE_CLASS_TEXT);
        thirdCostNameInput.setHint("Enter name of cost...");
        thirdCostNameInput.setWidth(dipConvert);
        thirdCostNameInput.setBackgroundResource(R.drawable.back);
        thirdCostNameInput.setTextSize(textSize);
        thirdInput.addView(thirdCostNameInput);

        thirdCostInput = new EditText(myContext);
        thirdCostInput.setPadding(leftPadding,0,0,0);
        thirdCostInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        thirdCostInput.setHint("Enter cost...");
        thirdCostInput.setWidth(dipConvert);
        thirdCostInput.setBackgroundResource(R.drawable.back);
        thirdCostInput.setTextSize(textSize);
        thirdInput.addView(thirdCostInput);
        ImageView thirdButton = new ImageView(myContext);
        thirdButton.setBackgroundResource(R.drawable.add_button);
        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String costName = thirdCostNameInput.getText().toString();
                String cost = thirdCostInput.getText().toString();
                if(costName.equals("") || cost.equals("")){
                    //tell user to make complete input
                }
                else{
                    try {
                        CostSpecific temp = new CostSpecific(costName,Double.parseDouble(cost));
                        currentUser.getListOfSavings().add(temp);
                        myRef.child(userID).setValue(currentUser);
                        clearView();
                    }catch(Exception e){
                        //tell user to make proper input
                    }
                }

            }
        });
        thirdInput.addView(thirdButton);
        myTable.addView(thirdInput);

    }
    public void createFirstLineTableRow(TableRow myRow,int listNum){
        String text="";
        int localTopPadding=topPadding;
        int localLeftPadding=leftPadding;
        if(listNum==1){
            text = "List of Cost";
            localTopPadding =(int)(40*scale+0.5f) ;
        }
        else if(listNum==2){
            text = "List of Income";
        }
        else if(listNum==3){
            text = "List of Saving";
        }
        myRow.setPadding(0,topPadding,0,0);

        TextView listName = new TextView(myContext);
        listName.setText(text);
        listName.setPadding(leftPadding,0,0,0);
        listName.setWidth(dipConvert);
        listName.setBackgroundResource(R.drawable.back);
        listName.setTextSize(textSize);
        myRow.addView(listName);

        TextView listCost = new TextView(myContext);
        listCost.setText("");
        listCost.setWidth(dipConvert);
        listCost.setBackgroundResource(R.drawable.back);
        listCost.setTextSize(textSize);
        myRow.addView(listCost);
        myTable.addView(myRow);
    }
    public void createTableRowBody(ArrayList<CostSpecific> currentList, UserData currentUser, int numList){
        double totalCost = 0;
        if(numList==1){
            totalCost = currentUser.getTotalCost();
        }
        else if (numList == 2){
            totalCost = currentUser.getTotalIncome();
        }
        else if(numList == 3){
            totalCost = currentUser.getTotalSavings();
        }
        for(int i=0; i<currentList.size();i++){
            TableRow newRow = new TableRow(myContext);
            //newRow.setPadding(0,2,0,0);

            double currentCost=currentList.get(i).getCost();
            String currentCostName = currentList.get(i).getCostName();

            TextView costName = new TextView(myContext);
            costName.setText(currentCostName);
            costName.setPadding(leftPadding,0,0,0);
            costName.setWidth(dipConvert);
            costName.setBackgroundResource(R.drawable.back);
            costName.setTextSize(textSize);
            newRow.addView(costName);

            TextView cost = new TextView(myContext);

            cost.setText("$"+currentList.get(i).getCost()+" ("+String.format("%.1f",currentCost
                    /totalCost*100)+ "%)");
            cost.setPadding(leftPadding,0,0,0);
            cost.setWidth(dipConvert);
            cost.setBackgroundResource(R.drawable.back);
            cost.setTextSize(textSize);
            newRow.addView(cost);

            ImageView deleteButton = new ImageView(myContext);
            deleteButton.setBackgroundResource(R.drawable.delete_button);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleListRowDelete(currentCostName, currentCost, currentList, currentUser,numList);
                    clearView();
                }
            });
            newRow.addView(deleteButton);
            myTable.addView(newRow);
        }
    }
    public void handleListRowDelete(String costName, double cost, ArrayList<CostSpecific> currentList, UserData currentUser, int listNum){
        ArrayList<CostSpecific> result = new ArrayList<>();
        ArrayList<CostSpecific> original = currentList;
        int counter = 0;
        int deleteCounter= 1;
        for(int i=0; i<original.size();i++){
            if(original.get(i).equals(costName, cost) && deleteCounter == 1 ){
                deleteCounter--;
            }
            else {
                result.add(original.get(i));
                counter++;
            }
        }
        if(listNum==1)
            currentUser.setListOfCost(result);
        else if (listNum == 2)
            currentUser.setListOfIncome(result);
        else if (listNum == 3)
            currentUser.setListOfSavings(result);
        myRef.child(userID).setValue(currentUser);
    }
    public void clearView(){
        myTable.removeAllViews();
        //firstList.removeAllViews();
        secondList.removeAllViews();
        thirdList.removeAllViews();
        firstInput.removeAllViews();
        secondInput.removeAllViews();
        thirdInput.removeAllViews();
    }
    public void onDestroyView() {
        super.onDestroyView();
    }
}
