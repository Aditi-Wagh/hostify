package com.example.firebase;

public class Mess {
    private String breaklunch,dinner,lun;


    public Mess(String Break, String Lunch, String Dinner) {
        this.breaklunch = Break;
        this.lun = Lunch;
        this.dinner = Dinner;

    }


    public String getBreakFast() {
        return breaklunch;
    }
    public String getLunch() {return lun; }
    public String getDinner() {
        return dinner;
    }
}
