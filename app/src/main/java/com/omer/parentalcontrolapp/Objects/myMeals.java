package com.omer.parentalcontrolapp.Objects;

import java.util.ArrayList;

public class myMeals {
    private ArrayList<Meal> meals;
    public boolean addTaskToTaskList(Meal meal){
        if(this.meals.add(meal)){
            return true;
        }
        else return false;
    }
    public void removeTaskByIndex(int index){ // maybe need to change to boolean
        meals.remove(index);
    }
}
