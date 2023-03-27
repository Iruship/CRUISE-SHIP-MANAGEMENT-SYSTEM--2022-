package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cabin {
    private final int NumberOfCabin;

    private String accessibility;

    private ArrayList<Passenger> passengersToCabin = new ArrayList<Passenger>();
    public Cabin(int NumberOfCabin, String accessibility){
        this.NumberOfCabin = NumberOfCabin;
        this.accessibility = accessibility;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    public String checkingAccessibility() {
        return accessibility;
    }


    public ArrayList<Passenger> InsertThePassengerToCabin() {
        return passengersToCabin;
    }

    public void setPassengersInCabin(int passengerCount) {
        ArrayList <Passenger> passengersToCabin = new ArrayList<>(passengerCount);

        this.passengersToCabin = passengersToCabin;
    }


    public void insertCustomer(ArrayList<Passenger> passengerArrayList) {
        this.passengersToCabin = passengerArrayList;
        this.setAccessibility("o");
    }



    public static void viewAllTheCabins(Cabin[] cabinsListRef) {
        for (int i = 0; i < cabinsListRef.length; i++) {
            if (!cabinsListRef[i].checkingAccessibility().equals("e")) {
                System.out.println("Cabin  number" + cabinsListRef[i].NumberOfCabin + " has been occupied.");
            } else if (cabinsListRef[i].checkingAccessibility().equals("e")) {
                System.out.println("Cabin number " + cabinsListRef[i].NumberOfCabin+ " is empty now");
            }
        }
    }



    public static void displayAllTheEmptyCabins(Cabin[] cabinsListRef){
        for (int i = 0; i < cabinsListRef.length; i++ ) {
            if (cabinsListRef[i].checkingAccessibility().equals("e")) {
                System.out.println("Cabin number" + cabinsListRef[i].NumberOfCabin+ " is empty");
            }
        }
    }


    public void deleteCustomer(){
        this.passengersToCabin.clear(); //Deleting the customer
        this.setAccessibility("e");

        System.out.println("This passenger was deleted from the system.");
    }


    public static void findingTheCabin(Cabin[] cabinsRef, String NameOfCustomer){
        Boolean IsCabinFound = false;


        for(int i = 0; i < cabinsRef.length; i++) {
            ArrayList <Passenger> ListofPassengers = cabinsRef[i].InsertThePassengerToCabin();
            //To find along the list of passengers
            for(int j = 0; j < ListofPassengers.size(); j++){
                if (ListofPassengers.get(j).getTheNameOfCustomer().equalsIgnoreCase(NameOfCustomer)) {
                    System.out.println(NameOfCustomer + " is in cabin number => " + i);
                    IsCabinFound = true;
                }
            }
        }

        if(IsCabinFound == false){
            System.out.println("Oh! The relavant Passenger was  not found! Please recheck the name..");
        }
    }


    public static void storingCustomerDetails(Cabin[] cabinsListRef){
        try {
            FileWriter MyFileWriter = new FileWriter("CabinprogramData_1.txt");
            for (int i = 0; i< cabinsListRef.length; i++) {
                ArrayList <Passenger> passengersToCabin = cabinsListRef[i].InsertThePassengerToCabin();

                //Saving the number of passengers in a cabin
                MyFileWriter.write(passengersToCabin.size() + " ");
                for(int j = 0; j < passengersToCabin.size(); j++) {
                    //Saving the passenger names
                    MyFileWriter.write(passengersToCabin.get(j).getTheNameOfCustomer() + " ");
                }
                MyFileWriter.write("\n");
            }
            MyFileWriter.close();
            System.out.println("Details of the customer have been saved successfully!");
        }
        catch (IOException e) {
            System.out.println("Something Went Wrong! Please Try again");
            e.printStackTrace();
        }
    }
    //https://www.w3schools.com/java/java_files_read.asp
    public static void sortingNames(Cabin[] cabinsListRef) {
        ArrayList <String> PassengerNames = new ArrayList<>();

        for(int i = 0; i < cabinsListRef.length; i++) {
            ArrayList <Passenger> passengerList = cabinsListRef[i].InsertThePassengerToCabin();
            for(int j = 0; j < passengerList.size(); j++){
                PassengerNames.add(passengerList.get(j).getTheNameOfCustomer());
            }
        }

        String valA;
        boolean changed;

        for (int x = 0; x < PassengerNames.size() - 1; x++) {
            changed = false;
            for (int y = 0; y < PassengerNames.size() - x - 1; y++) {
                if (PassengerNames.get(y).compareToIgnoreCase(PassengerNames.get(y + 1)) > 0) {
                    valA = PassengerNames.get(y);
                    PassengerNames.set(y, PassengerNames.get(y + 1));
                    PassengerNames.set((y + 1), valA);
                    changed = true;
                }
            }

            if (!changed) {
                break;
            }
        }

        for(int i=0; i<PassengerNames.size(); i++){
            System.out.println(PassengerNames.get(i));
        }
    }

    public static void loadingTheData(Cabin[] cabinsListRef) {
        try {
            File MyCabinprogramDataFile = new File("CabinprogramData_1.txt");
            Scanner ReadMyData = new Scanner(MyCabinprogramDataFile);
            while (ReadMyData.hasNextLine()) {
                for (int i = 0; i < cabinsListRef.length; i++) {
                    String data = ReadMyData.nextLine();
                    String[] CustomerDetails = data.split("\\s+");
                    int noOfPassengers = Integer.parseInt(CustomerDetails[0]);

                    //The below code snippet creates an array for each cabin.
                    ArrayList<Passenger> ListOfPassengers = new ArrayList<>();
                    if(noOfPassengers == 0){
                        cabinsListRef[i].setAccessibility("e");

                    }else if(noOfPassengers == 1){
                        cabinsListRef[i].setAccessibility("o");

                        String fName01 = CustomerDetails[1];
                        String sname01 = CustomerDetails[2];

                        Passenger fCustomer = new Passenger(fName01, sname01);
                        ListOfPassengers.add(fCustomer);
                        cabinsListRef[i].insertCustomer(ListOfPassengers);

                    } else if(noOfPassengers == 2){
                        cabinsListRef[i].setAccessibility("o");

                        String fName01 = CustomerDetails[1];
                        String sname01 = CustomerDetails[2];

                        String fName02 = CustomerDetails[3];
                        String sname02 = CustomerDetails[4];

                        Passenger fCustomer = new Passenger(fName01, sname01);
                        Passenger sCustomer = new Passenger(fName02, sname02);

                        ListOfPassengers.add(fCustomer);
                        ListOfPassengers.add(sCustomer);

                        cabinsListRef[i].insertCustomer(ListOfPassengers);

                    } else if(noOfPassengers == 3){
                        cabinsListRef[i].setAccessibility("o");

                        String fName01 = CustomerDetails[1];
                        String sname01 = CustomerDetails[2];

                        String fName02 = CustomerDetails[3];
                        String sname02 = CustomerDetails[4];

                        String fName03 = CustomerDetails[5];
                        String sname03 = CustomerDetails[6];

                        Passenger fCustomer = new Passenger(fName01, sname01);
                        Passenger sCustomer = new Passenger(fName02, sname02);
                        Passenger tCustomer = new Passenger(fName03, sname03);

                        ListOfPassengers.add(fCustomer);
                        ListOfPassengers.add(sCustomer);
                        ListOfPassengers.add(tCustomer);

                        cabinsListRef[i].insertCustomer(ListOfPassengers);
                    }
                }
            }
            ReadMyData.close();
            System.out.println("Data was successfully loaded to the  System.");
        } catch (FileNotFoundException e) {
            System.out.println("Oops an Error occurred.!");
            e.printStackTrace();
        }
    }




    //Calculating and presenting the expenses
    public static void CalculatingTheExpenses(Cabin[] cabinsListRef){
        int AmountOfPassengers = 0;
        for(int i = 0; i < cabinsListRef.length; i++){
            ArrayList <Passenger> passengersInCabin = cabinsListRef[i].InsertThePassengerToCabin();

            for(int j = 0; j < passengersInCabin.size(); j++){
                AmountOfPassengers++;
            }
        }

        Passenger customer = new Passenger();
        int TotalCostOfAPassenger = customer.getExpenses();

        //Total cost of one passenger
        System.out.println("Expenses per passenger in (ruppees): Rs." + TotalCostOfAPassenger);

        //Total cost for all the passengers in a cabin
        System.out.println("Total : Rs." + TotalCostOfAPassenger * AmountOfPassengers);
    }

    //Examining whether all the cabins are full
    public static Boolean checkAccessibility(Cabin[] cabinsListRef){
        Boolean cabinsAreAccessible = false;
        for(int i = 0; i < cabinsListRef.length; i++){

            //Searching Empty cabins
            if(cabinsListRef[i].checkingAccessibility().equals("e")){
                cabinsAreAccessible = true;
            }
        }

        return cabinsAreAccessible;
    }

}