package com.company;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.*;


public class task1 {
    static Scanner input= new Scanner(System.in);

    public static void main(String[] args) {
	// Creating arrays to store cabins,first name of the customer, surname of the customer, expenses of the customer and the number of customers respectively
    String[] cabins = new String[12];
    String[] firstName = new String [12];
    String [] surname= new String [12];
    int[] expenses = new int [12];
    int[] customers=new int[12];

    //The initialize method
        initialise (cabins);

    // Creating a while loop to print the menu again after the user completes running one choice.
        while(true){
            //The menu is being printed
            System.out.println("=================================================================");
            System.out.println("MENU");
            System.out.println(); //Printing a gap between the menu-list
            System.out.println("A- Add a customer to a cabin");
            System.out.println("V- View all cabins");
            System.out.println("E- Display Empty cabins ");
            System.out.println("D- Delete customer from a cabin");
            System.out.println("F- Find cabin from customer name");
            System.out.println("S- Store programme data into a file");
            System.out.println("L- Load programme data from a file");
            System.out.println("O- View passengers Ordered alphabetically by name");
            System.out.println();
            System.out.println("=================================================================");
            System.out.print("Enter the letter of the your option or enter 'Stop' to terminate: ");

            String option = input.nextLine();
            //To give a clean output
            System.out.println("=================================================================");

            if (option.equals("A")){
                //calls the AddsACustomer method
                AddsACustomer(cabins,customers,firstName,surname);
            }
            if (option.equals("V")){
                //calls the ViewAllCabins method
                ViewAllCabins(cabins);
            }
            if (option.equals("E")){
                //Calling the EmptyCabins method
                EmptyCabins(cabins);
            }
            if (option.equals("D")){
                //Getting the name of the customer that should be deleted
                System.out.println("Enter the name of the customer that should be deleted from the programme: ");
                String deleteName = input.next();
                //Calling the DeleteACustomer method
                DeleteACustomer(deleteName,cabins);
            }
            if (option.equals("F")){
                //Getting the name of the Customer that should be found
                System.out.println("Enter the name of the customer to find the cabin: ");
                String FindName = input.next();
                //Calling the FindTheCabin method
                FindTheCabin(FindName,cabins);
            }

            if (option.equals("S")){
                //Calling the StoringData method.
                StoringData (cabins,customers,firstName,surname);
            }
            if (option.equals("L")){
                //Calling the LoadProgramData method.
                LoadProgramData();
            }
            if (option.equals("O")){
                //Creating an array with all the elements in cabins[] array.
                String [] cabinsNew = new String[12];
                for (int x=0; x<12; x++){
                    cabinsNew[x]=cabins[x];
                }
                //Calling the  OrderOfTheNames method
                OrderOfTheNames(cabinsNew);
            }
            //Implementing a procedure to stop the program by the user.
            if (option.equals("Stop")){
                System.exit(0);
            }
        }

    }
    // Method to add a new customer to a cabin
    public static void AddsACustomer (String cabins[], int customers[], String firstName[], String surname[] ){
        System.out.print("Enter cabin number (0-11): ");
        int cabinNum = input.nextInt();

        //The following details will be asked from the user (ONLY IF THE ROOM IS EMPTY)
        if (cabins[cabinNum].equals("e")){
            System.out.println("Only three customers per cabin!)");
            System.out.print("Enter the customer's name:");
            String customerName = input.next();
            System.out.print("Enter number of customers: ");
            int customerNum = input.nextInt();
            System.out.println("Enter the required information please!");
            System.out.print("First Name:");
            String fName=input.next();
            System.out.print("Surname:");
            String sName=input.next();

            //adding the respective information to the right index of the respective arrays.
            cabins[cabinNum] = customerName;
            customers[cabinNum] = customerNum;
            firstName[cabinNum] = fName;
            surname[cabinNum] = sName;

            //else if the cabin which was chosen by the customer is  already occupied the following message will be printed
        }else {
            System.out.println(" Sorry this cabin is  occupied!");
        }input.nextLine();
        }

    // Method to view all the cabins
    public static void ViewAllCabins(String cabins[]) {
        for (int x = 0; x < 12; x++) {
            System.out.println("Cabin " + x + " is occupied by " + cabins[x]);
        }
    }
    //method to print all the cabins which are empty
    public static void EmptyCabins(String cabins[]){
        for (int x = 0; x <12; x++ )
        {
            if (cabins[x].equals("e"))System.out.println("cabin " + x + " is empty");
        }
    }
    //method to delete a customer
    public static void DeleteACustomer(String deleteName,String cabins[]){
        for (int x = 0; x < 12; x++ )
        {
            if(deleteName.equals(cabins[x])){
                cabins[x]="e";
            }
        }input.nextLine();
    }
    //method  to find out which room a given customer is in.
    public static void FindTheCabin(String FindName, String cabins[]){
        for (int x = 0; x < 12; x++ )
        {
            if(FindName.equals(cabins[x])){
                System.out.println(FindName +" is in cabin number "+x);
            }
        }input.nextLine();
    }
    //storing  the customer and cabin data  in  to a text file
    public static void StoringData(String cabins[], int customers[], String firstName[],String surname[]) {
        try {
            FileWriter writeMyData = new FileWriter("CabinprogramData_1.txt");
            int count = 0;
            for (int x = 0; x < 8; x++) {
                if (cabins[x].equals("e")) {
                    count++; //counts the number of empty cabins.
                }
            }
            if (count != 12) {
                for (int x = 0; x < 12; x++) {
                    if (!cabins[x].equals("e")) {
                        writeMyData.write("room " + x + " is occupied by " + cabins[x] + "\n");
                        writeMyData.write("Number of customers: " + customers[x] + "\n");
                        writeMyData.write("First name of the customer: " + firstName[x] + "\n");
                        writeMyData.write("Last name of the customer: " + surname[x] + "\n");

                    }
                }
                System.out.println("Your data has been stored in file.");
            } else {
                //if every room is empty when the data is saved the bellow message will be printed
                System.out.println("No data has been entered to save!\n");
                //if every room is empty when the data is loaded the bellow message will be printed
                writeMyData.write("No data has been saved to display!\n");
            }
            writeMyData.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    //Loading the data from the text file
    public static void LoadProgramData() {
        try {
            File myObj = new File("CabinprogramData_1.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void OrderOfTheNames(String cabinsNew[]){
        for(int i = 0; i<cabinsNew.length-1; i++)
        {
            for (int j = i+1; j<12; j++)
            {
                //compares each elements of the array to all the remaining elements
                if(cabinsNew[i].compareTo(cabinsNew[j])>0)
                {
                    //swapping array elements
                    String temp = cabinsNew[i];
                    cabinsNew[i] = cabinsNew[j];
                    cabinsNew[j] = temp;
                }
            }
        }
        //prints the sorted array in ascending order excluding "e"(empty rooms)
        for (int x = 0; x<12; x++){
            if((!cabinsNew[x].equals("e"))){
                System.out.println(cabinsNew[x]);
            }
        }
    }
    //method to initialize the hotel array
    private static void initialise (String cabinsRef[] ) {
        for (int x = 0; x < 12; x++) cabinsRef[x] = "e";
        System.out.println("initialize ");
    }


}
