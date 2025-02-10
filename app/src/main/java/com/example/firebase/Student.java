package com.example.firebase;

public class Student {
    private String ename,ehostel,etype;
    private String name;
    private String room;
    private String hostel;

    public Student(String name, String room, String hostel,String ename,String ehostel, String etype) {
        this.name = name;
        this.room = room;
        this.hostel = hostel;
        this.ename = ename;
        this.ehostel = ehostel;
        this.etype = etype;
    }


    public String getEName() {
        return ename;
    }
    public String geteHostel() {return ehostel; }
   public String geteType() {
        return etype;
    }

    public String getName() {
        return name;
    }

    public String getRoom() {
        return room;
    }

    public String getHostel() {
        return hostel;
    }
}
