package com.example.pathway;

public class CostSpecific {
    private String costName;
    private double cost;
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
}
