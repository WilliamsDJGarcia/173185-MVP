package com.example.mvp;
// MODELO USER

public class User {
    private String Name = "";

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getName(){
        return this.Name;
    }

    @Override
    public String toString() {
        return Name;
    }
}
