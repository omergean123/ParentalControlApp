package com.omer.parentalcontrolapp.Objects;

import java.util.ArrayList;

public class Child extends User{
    public ArrayList<Parent> myParents;

    public Child() {
        super();
    }
//
//    public Child(String uid, String email, String password) {
//        super(uid, email, password);
//    }

    public boolean addUserToArray(Parent newParent){
        if(this.myParents.add(newParent)){
            return true;
        }
        else return false;
    }
//    public Parent getParentByIndex(int index){
//        return this.myParents.get(index);
//    }
    public boolean checkIfExist(String name){
        for (int i=0; i<myParents.size();i++){
            if(name.equals(this.myParents.get(i))) {
                return true; //already synchronized
            }
        }
        return false;//legal parent
    }
}
