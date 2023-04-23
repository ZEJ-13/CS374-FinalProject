package com.example.pathway;
import java.util.ArrayList;

public class UserData {
    private String ID;
    private String fullName;
    private String dateOfBirth;
    private String longTermGoal;
    private ArrayList<Double> listOfCost;
    private ArrayList<Double> listOfIncome;
    private ArrayList<Double> listOfSavings;

    public UserData(String ID, String fullName,String dateOfBirth,String longTermGoal,
                    ArrayList<Double> listOfCost,ArrayList<Double> listOfIncome,ArrayList<Double> listOfSavings ){
        this.ID = ID;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.longTermGoal = longTermGoal;
        this.listOfCost = listOfCost;
        this.listOfIncome = listOfIncome;
        this.listOfSavings = listOfSavings;
    }
    public String getID(){
        return ID;
    }
    public String getFullName(){
        return fullName;
    }
    public String getLongTermGoal(){
        return  longTermGoal;
    }
    public ArrayList<Double> getListOfCost(){
        return listOfCost;
    }
    public ArrayList<Double> getListOfIncome(){
        return listOfIncome;
    }
    public ArrayList<Double> getListOfSavings(){
        return listOfSavings;
    }
    public double totalCost(){
        double counter = 0;
        for(int i=0; i<listOfCost.size();i++){
            counter+=listOfCost.get(i);
        }
        return counter;
    }
    public double totalIncome(){
        double counter = 0;
        for(int i=0; i<listOfIncome.size();i++){
            counter+=listOfIncome.get(i);
        }
        return counter;
    }
    public double totalSavings(){
        double counter = 0;
        for(int i=0; i<listOfSavings.size();i++){
            counter+=listOfSavings.get(i);
        }
        return counter;
    }
}
