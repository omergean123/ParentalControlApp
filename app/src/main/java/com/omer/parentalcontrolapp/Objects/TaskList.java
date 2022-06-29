package com.omer.parentalcontrolapp.Objects;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> Tasks;
    public boolean addTaskToTaskList(Task newTask){
        if(this.Tasks.add(newTask)){
            return true;
        }
        else return false;
    }
    public void removeTaskByIndex(int index){ // maybe need to change to boolean
        Tasks.remove(index);
    }
}
