package com.omer.parentalcontrolapp.Objects;

public class Meal {
    private String mealTime;
    private String mealDetails;
    public Meal(String mealDetails,String mealTime){
        this.mealTime = mealTime;
        this.mealDetails = mealDetails;
    }

    public String getMealTime() {
        return mealTime;
    }

    public void setMealTime(String mealTime) {
        this.mealTime = mealTime;
    }

    public String getMealDetails() {
        return mealDetails;
    }

    public void setMealDetails(String mealDetails) {
        this.mealDetails = mealDetails;
    }
}