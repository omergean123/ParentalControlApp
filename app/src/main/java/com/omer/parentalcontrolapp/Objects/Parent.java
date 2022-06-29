package com.omer.parentalcontrolapp.Objects;

import java.util.ArrayList;

public class Parent extends User {
    public ArrayList<Child> myChildren;

    public Parent() {
        super();
    }
//
//    public Parent(String uid, String email, String password) {
//        super(uid, email, password);
//    }

    public boolean addUserToArray(Child newChild){
        if(this.myChildren.add(newChild)){
            return true;
        }
        else return false;
    }
    public Child getChildByIndex(int index){
        return this.myChildren.get(index);
    }
    public boolean checkIfExist(String name){
        for (int i=0; i<myChildren.size();i++){
            if(name.equals(this.myChildren.get(i))) {
                return true; //already synchronized
            }
        }
        return false;//legal child
    }
}
