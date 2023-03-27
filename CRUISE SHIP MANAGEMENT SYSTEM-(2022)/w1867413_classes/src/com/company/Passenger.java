package com.company;

public class Passenger {

    private String NameOfThePassenger;
    private static final int TotalCostOfAPassenger= 20000;

    public Passenger(){
    }

    public Passenger(String fName, String Sname){
        this.NameOfThePassenger = fName + " " + Sname;

    }
    public String getTheNameOfCustomer() {
        return NameOfThePassenger;
    }

    public int getExpenses() {
        return TotalCostOfAPassenger;
    }
    public void setPassengerName(String name) {
        this.NameOfThePassenger = NameOfThePassenger;
    }
}