package org.rldevelopement.back;

public class Class {
    private static int studentsNumber = 0;
    public int ClassId;
    private String name;
    private String matter;
    private String date;
    private int duration;

    public Class(int id, String matter, String date, int duration) {
        this.ClassId = id;
        this.name = name;
        this.matter = matter;
        this.date = date;
        this.duration = duration;
    }

    public String getMatter() {
        return matter;
    }

    public String getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
    }

    public int getClassId() {
        return ClassId;
    }

    public String getName() {
        return name;
    }

}
