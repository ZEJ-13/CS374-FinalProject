package com.example.pathway;
import java.util.ArrayList;
import java.util.HashMap;

public class UserData {

    private String fullName;
    private String longTermGoal;
    private HashMap<String,Double> listOfCost;
    private HashMap<String,Double> listOfIncome;
    private HashMap<String,Double> listOfSavings;

    public UserData(String fullName,String longTermGoal,
                    HashMap<String,Double> listOfCost,HashMap<String,Double> listOfIncome,HashMap<String,Double> listOfSavings){
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
    public HashMap<String,Double> getListOfCost(){
        return listOfCost;
    }
    public HashMap<String,Double> getListOfIncome(){
        return listOfIncome;
    }
    public HashMap<String,Double> getListOfSavings(){
        return listOfSavings;
    }
    public double totalCost(){
        double counter = 0;
        for(int i=0; i<listOfCost.size();i++){
            counter+=listOfCost.get(""+i);
        }
        return counter;
    }
    public double totalIncome(){
        double counter = 0;
        for(int i=0; i<listOfIncome.size();i++){
            counter+=listOfIncome.get(""+i);
        }
        return counter;
    }
    public double totalSavings(){
        double counter = 0;
        for(int i=0; i<listOfSavings.size();i++){
            counter+=listOfSavings.get(""+i);
        }
        return counter;
    }
}
