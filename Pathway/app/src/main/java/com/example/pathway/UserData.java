package com.example.pathway;
import java.util.ArrayList;
import java.util.HashMap;


public class UserData {

    private String fullName;
    private String longTermGoal;
    private HashMap<String,CostSpecific> listOfCost;
    private HashMap<String,CostSpecific> listOfIncome;
    private HashMap<String,CostSpecific> listOfSavings;

    public UserData(String fullName,String longTermGoal,
                    HashMap<String,CostSpecific> listOfCost,HashMap<String,CostSpecific> listOfIncome,HashMap<String,CostSpecific> listOfSavings){
        this.fullName = fullName;
        this.longTermGoal = longTermGoal;
        this.listOfCost = listOfCost;
        this.listOfIncome = listOfIncome;
        this.listOfSavings = listOfSavings;
    }

    public String getFullName(){
        return fullName;
    }
    public String getLongTermGoal(){
        return  longTermGoal;
    }
    public HashMap<String,CostSpecific> getListOfCost(){
        return listOfCost;
    }
    public HashMap<String,CostSpecific> getListOfIncome(){
        return listOfIncome;
    }
    public HashMap<String,CostSpecific> getListOfSavings(){
        return listOfSavings;
    }
    public double totalCost(){
        double counter = 0;
        for(int i=0; i<listOfCost.size();i++){
            counter+=listOfCost.get(""+i).getCost();
        }
        return counter;
    }
    public double totalIncome(){
        double counter = 0;
        for(int i=0; i<listOfIncome.size();i++){
            counter+=listOfIncome.get(""+i).getCost();
        }
        return counter;
    }
    public double totalSavings(){
        double counter = 0;
        for(int i=0; i<listOfSavings.size();i++){
            counter+=listOfSavings.get(""+i).getCost();
        }
        return counter;
    }
}
