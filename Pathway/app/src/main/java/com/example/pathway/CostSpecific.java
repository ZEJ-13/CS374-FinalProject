package com.example.pathway;

public class CostSpecific {
    private String costName;
    private double cost;

    public CostSpecific(){
        this.costName = "";
        this.cost = 0;
    }
    public CostSpecific(String costName, double cost){
        this.costName = costName;
        this.cost = cost;
    }
    public String getCostName(){
        return costName;
    }
    public double getCost(){
        return cost;
    }
    public void setCostName(String costName){
        this.costName = costName;
    }
    public void setCost(double cost){
        this.cost = cost;
    }
    public boolean equals(CostSpecific input){
        return this.cost == input.cost && this.costName.equals(input.costName);
    }
    public boolean equals(String costName, double cost){
        return this.cost == cost && this.costName.equals(costName);
    }
}
