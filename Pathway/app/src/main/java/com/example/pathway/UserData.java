package com.example.pathway;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *  Userdata contains the user's long term goal, list of Cost, list of Income, List of Saving
 *
 *  Implemented basic setter, getter, and get total methods.
*/

public class UserData {

    private String fullName;
    private String longTermGoal;
    private ArrayList<CostSpecific> listOfCost;
    private ArrayList<CostSpecific> listOfIncome;
    private ArrayList<CostSpecific> listOfSavings;

    public UserData(){
        this.fullName = "";
        this.longTermGoal = "";
        this.listOfCost = new ArrayList<>();
        this.listOfIncome = new ArrayList<>();
        this.listOfSavings = new ArrayList<>();
    }
    public UserData(String fullName,String longTermGoal,
                    ArrayList<CostSpecific> listOfCost,ArrayList<CostSpecific> listOfIncome,ArrayList<CostSpecific> listOfSavings){
        this.fullName = fullName;
        this.longTermGoal = longTermGoal;
        this.listOfCost = listOfCost;
        this.listOfIncome = listOfIncome;
        this.listOfSavings = listOfSavings;
    }

    public String getFullName(){
        return fullName;
    }
    public void setLongTermGoal(String longTermGoal){
        this.longTermGoal = longTermGoal;
    }
    public String getLongTermGoal(){
        return longTermGoal;
    }
    public ArrayList<CostSpecific> getListOfCost(){
        return listOfCost;
    }
    public ArrayList<CostSpecific> getListOfIncome(){
        return listOfIncome;
    }
    public ArrayList<CostSpecific> getListOfSavings(){
        return listOfSavings;
    }
    public double getTotalCost(){
        double counter = 0;
        for(int i=0; i<listOfCost.size();i++){
            counter+=listOfCost.get(i).getCost();
        }
        return counter;
    }
    public double getTotalIncome(){
        double counter = 0;
        for(int i=0; i<listOfIncome.size();i++){
            counter+=listOfIncome.get(i).getCost();
        }
        return counter;
    }
    public double getTotalSavings(){
        double counter = 0;
        for(int i=0; i<listOfSavings.size();i++){
            counter+=listOfSavings.get(i).getCost();
        }
        return counter;
    }

    public void setListOfCost(ArrayList<CostSpecific> listOfCost){
        this.listOfCost = listOfCost;
    }
    public void setListOfIncome(ArrayList<CostSpecific> listOfIncome){
        this.listOfIncome = listOfIncome;
    }
    public void setListOfSavings(ArrayList<CostSpecific> listOfSavings){
        this.listOfSavings = listOfSavings;
    }
}
