package com.company;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    static ArrayList<Passenger> Queue = new ArrayList<>();

    static Scanner inputsOfProgram = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Cruise Ship Boarding ");
        Cabin cab0 = new Cabin(0, "e");
        Cabin cab1 = new Cabin(1, "e");
        Cabin cab2 = new Cabin(2, "e");
        Cabin cab3 = new Cabin(3, "e");
        Cabin cab4 = new Cabin(4, "e");
        Cabin cab5 = new Cabin(5, "e");
        Cabin cab6 = new Cabin(6, "e");
        Cabin cab7 = new Cabin(7, "e");
        Cabin cab8 = new Cabin(8, "e");
        Cabin cab9 = new Cabin(9, "e");
        Cabin cab10 = new Cabin(10, "e");
        Cabin cab11 = new Cabin(11, "e");

        Cabin[] cabinsList = {cab0, cab1, cab2, cab3, cab4, cab5, cab6, cab7, cab8, cab9, cab10, cab11};

        String choiceOfPassenger = "Initialize";

        while (!choiceOfPassenger.equalsIgnoreCase("terminate")) {
            choiceList(); //Calling the choiceList method
            System.out.print("Enter the letter of the your option or enter 'Stop' to terminate:  ");
            choiceOfPassenger = inputsOfProgram.next().toLowerCase();

            switch (choiceOfPassenger) {
                case "a": {
                    System.out.print("Enter the number of passengers: ");
                    int noOfPassengers = inputsOfProgram.nextInt();
                    //Checking the user input customer number
                    if (noOfPassengers > 0 && noOfPassengers < 4) {
                        ArrayList<Passenger> passengersArrayList = passengerInformation(noOfPassengers);

                        //Examining whether the cabins are occupied or not
                        if (Cabin.checkAccessibility(cabinsList)) {
                            System.out.print("Enter the cabin number you wish [0-11] : ");
                            int appending = inputsOfProgram.nextInt();

                            //Examining whether the cabins are empty
                            if (cabinsList[appending].checkingAccessibility().equals("e")) {
                                //Sending the customers to the cabin
                                cabinsList[appending].insertCustomer(passengersArrayList);
                            } else {
                                System.out.println("Cabin " + appending + " is occupied. Sorry!");
                            }

                        } else {
                            System.out.println("All the cabins are full. Passengers are now inserted to a waiting list");
                            for (int i = 0; i < passengersArrayList.size(); i++) {
                                Queue.add(passengersArrayList.get(i));
                            }
                        }
                    } else {
                        System.out.println("Seems Like you've entered an invalid number");
                    }
                }
                break;
                case "v": {
                    Cabin.viewAllTheCabins(cabinsList);
                }
                break;
                case "d": {
                    System.out.println("====================Deleting a Customer================== ");
                    System.out.print("Enter the cabin number you wish to remove customers from (0-11): ");
                    int removeFrom = inputsOfProgram.nextInt();

                    //Checks whether the cabin is empty
                    if (!cabinsList[removeFrom].checkingAccessibility().equals("e")) {

                        //deleting the passengers from cabin
                        cabinsList[removeFrom].deleteCustomer();

                        //A passenger list to assign in to a cabin
                        ArrayList<Passenger> passengerArrayList = new ArrayList<>();

                        //Add passengers from waiting list if there is a waiting list
                        if (Queue.size() > 0) {
                            passengerArrayList.add(Queue.get(0));

                            //Adding the first customer in waiting list to the cabin
                            cabinsList[removeFrom].insertCustomer(passengerArrayList);

                            System.out.println(Queue.get(0).getTheNameOfCustomer() + " added to the cabin");

                            //Removing that customer from the waiting list
                            Queue.remove(0);


                        }

                    } else {
                        System.out.println("Cabin " + removeFrom + " is already empty");
                    }
                }
                case "e": {
                    Cabin.displayAllTheEmptyCabins(cabinsList);
                }

                break;
                case "f": {
                    System.out.println("====================Finding the Customer================== ");
                    System.out.println("Enter details of Customer to find cabin: ");
                    System.out.print("Enter the First name of the customer: ");
                    String firstName = inputsOfProgram.next();
                    System.out.print("Enter the Surname of the customer: ");
                    String surname = inputsOfProgram.next();

                    String NameOfCustomer = firstName + " " + surname;

                    Cabin.findingTheCabin(cabinsList, NameOfCustomer);

                }
                break;
                case "s": {
                    System.out.println("====================Saving  Customer Details================== ");
                    Cabin.storingCustomerDetails(cabinsList);
                }
                break;
                case "l": {
                    System.out.println("====================Loading  Customer Details================== ");
                    Cabin.loadingTheData(cabinsList);
                }
                break;
                case "o": {
                    System.out.println("==================== Customers Names in Alphabetical order================== ");
                    Cabin.sortingNames(cabinsList);
                }
                break;
                case "t": {
                    System.out.println("==================== Total Cost==========================");
                    Cabin.CalculatingTheExpenses(cabinsList);
                }
                break;
                case "terminate": {
                    System.out.println("Hold on... ");
                    System.out.println("Program is successfully terminated.");
                }
                break;
                default:
                    System.out.println("Please recheck your input and try again!");
            }
        }
    }

    //Getting customer/passenger details from the user
    private static ArrayList<Passenger> passengerInformation(int AmountOfPassengers) {
        ArrayList<Passenger> ListOfPassengers = new ArrayList<>();

        while (AmountOfPassengers > 0) {
            System.out.print(" Enter the First name of the customer: ");
            String fName = inputsOfProgram.next();

            System.out.print("Enter the Surname of the customer: ");
            String Sname = inputsOfProgram.next();

            Passenger passenger = new Passenger(fName, Sname);
            ListOfPassengers.add(passenger);

            AmountOfPassengers--;
        }
        return ListOfPassengers; //Returning list of passengers
    }


    //Printing the menu
    private static void choiceList() {
        System.out.println();
        System.out.println("=================================================================");
        System.out.println();
        System.out.println("=======================MENU=====================================");
        System.out.println("A: Add Customer to Cabin");
        System.out.println("V: View all the Cabins");
        System.out.println("E: Display Empty Cabins");
        System.out.println("D: Delete Customer from Cabin");
        System.out.println("F: Find Cabin from Customer Name");
        System.out.println("S: Store Program Data into file");
        System.out.println("L: Load Program Data from file");
        System.out.println("O: View passengers Ordered Alphabetically by name");
        System.out.println("T: Print expenses");
        System.out.println("terminate: Enter terminate to terminate the System");
        System.out.println();
        System.out.println("================================================================");
    }
}

