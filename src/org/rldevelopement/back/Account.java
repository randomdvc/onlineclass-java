package org.rldevelopement.back;

public class Account {

    public String fname;
    public String lname;
    public String email;
    public String type;
    public String cycle;
    public int level;
    public String Class;
    public int absence;

    public Account(String fname, String lname, String email, String type, String cycle, int level, String Class, int absence) {

        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.type = type;
        this.cycle = cycle;
        this.level = level;
        this.Class = Class;
        this.absence = absence;

        //System.out.println(fname + " " + lname + " " + email + " " + type + " " + cycle + " " + level + " " + Class + " " + absence);

    }

    public String getFirstName() {
        return fname;
    }

    public String getLastname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public String getCycle() {
        return cycle;
    }

    public int getLevel() {
        return level;
    }

    public String getClassName() {
        return Class;
    }

    public int getAbsence() {
        return absence;
    }
}
