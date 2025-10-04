package com.development.aashaworker.custom;

public class BeneficiaryDetails {
    private    String name, id, age, address, father, husband, month, notes, lastVisit;
    private    boolean isMarried, isPregnant, isComplication;
    private    String sex;

    public BeneficiaryDetails(String address, String age, String father, String husband, String id, boolean isComplication, boolean isMarried, boolean isPregnant, String month, String name, String notes, String sex, String lastVisit) {
        this.address = address;
        this.age = age;
        this.father = father;
        this.husband = husband;
        this.id = id;
        this.isComplication = isComplication;
        this.isMarried = isMarried;
        this.isPregnant = isPregnant;
        this.month = month;
        this.name = name;
        this.notes = notes;
        this.sex = sex;
        this.lastVisit = lastVisit;
    }

    public String getLastVisit() {
        return lastVisit;
    }

    public String getSex() {
        return sex;
    }

    public String getNotes() {
        return notes;
    }

    public String getName() {
        return name;
    }

    public String getMonth() {
        return month;
    }

    public boolean isPregnant() {
        return isPregnant;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public boolean isComplication() {
        return isComplication;
    }

    public String getId() {
        return id;
    }

    public String getHusband() {
        return husband;
    }

    public String getFather() {
        return father;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }
}
