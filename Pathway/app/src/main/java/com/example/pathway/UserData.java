package com.example.pathway;
import java.util.ArrayList;
import java.util.HashMap;
/**
*   Userdata contains the user's long term goal, list of Cost, list of Income, List of savings
 *  Using hashmap to similate list as firebase doesn't support list
 *  Hashmap's key is a String that will be parsed as a integer later(as firebase doesn't support
 *  non-string keys), value is a CostSpecific object that holds String costName and double cost.
*/

public class UserData {

    private String fullName;
    private String longTermGoal;
    private HashMap<String,CostSpecific> listOfCost;
    private HashMap<String,CostSpecific> listOfIncome;
    private HashMap<String,CostSpecific> listOfSavings;

    public UserData(){
        this.fullName = "";
        this.longTermGoal = "";
        this.listOfCost = new HashMap<>();
        this.listOfIncome = new HashMap<>();
        this.listOfSavings = new HashMap<>();
    }
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
        return longTermGoal;
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
    public double getTotalCost(){
        double counter = 0;
        for(int i=0; i<listOfCost.size();i++){
            counter+=listOfCost.get(i+"key").getCost();
        }
        return counter;
    }
    public double getTotalIncome(){
        double counter = 0;
        for(int i=0; i<listOfIncome.size();i++){
            counter+=listOfIncome.get(i+"key").getCost();
        }
        return counter;
    }
    public double getTotalSavings(){
        double counter = 0;
        for(int i=0; i<listOfSavings.size();i++){
            counter+=listOfSavings.get(i+"key").getCost();
        }
        return counter;
    }
    public void setLongTermGoal(String longTermGoal){
        this.longTermGoal = longTermGoal;
    }
    public void setListOfCost(HashMap<String,CostSpecific> listOfCost){
        this.listOfCost = listOfCost;
    }
    public void setListOfIncome(HashMap<String,CostSpecific> listOfIncome){
        this.listOfIncome = listOfIncome;
    }
    public void setListOfSavings(HashMap<String,CostSpecific> listOfSavings){
        this.listOfSavings = listOfSavings;
    }
}
