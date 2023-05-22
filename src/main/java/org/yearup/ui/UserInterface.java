package org.yearup.ui;

import org.yearup.datastorages.ContractFileManager;
import org.yearup.datastorages.DealershipFileManager;
import org.yearup.models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface
{
    Scanner userInput = new Scanner(System.in);
    private final DealershipFileManager fileManager;
    private final Dealership dealership;

    public UserInterface()
    {
        fileManager = new DealershipFileManager();
        dealership = fileManager.getDealership();
    }

    public void display()
    {
        while(true)
        {
            System.out.println();
            displayHomeScreen();

            try
            {
                int selection = getUserInputInt("Make a selection: ");
                System.out.println();

                switch (selection) {
                    case 1 -> // search by price range
                            processGetByPrice();
                    case 2 -> // search by make model
                            processGetByMakeModel();
                    case 3 -> // search by year
                            processGetByYear();
                    case 4 -> processGetByColor();
                    case 5 -> processGetByMileage();
                    case 6 -> processGetByVehicleType();
                    case 7 -> processGetAllVehicles();
                    case 8 -> processAddVehicle();
                    case 9 -> processDeleteVehicle();
                    case 0 -> processSaleOrLeaseVehicle();
                    case 99 ->
                    { // exit
                        fileManager.saveDealership(dealership);
                        System.out.println("Exiting....");
                        System.out.println("Good Bye!");
                        return;
                    }
                    default -> System.out.println("Not a valid selection.");
                }
            }
            catch(Exception ex)
            {
                System.out.println("Please enter a valid option.");
            }
        }
    }


    public String getUserInputString(String message)
    {
        System.out.print(message);
        return userInput.nextLine().strip();
    }

    public int getUserInputInt(String message)
    {
        return Integer.parseInt(getUserInputString(message));
    }

    public double getUserInputDouble(String message)
    {
        return Double.parseDouble(getUserInputString(message));
    }

    public void displayHomeScreen()
    {
        System.out.println();
        System.out.println("Menu");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("What do you want to do?");
        System.out.println("\t 1) Search by price range");
        System.out.println("\t 2) Search by make / model");
        System.out.println("\t 3) Search by year range");
        System.out.println("\t 4) Search by color");
        System.out.println("\t 5) Search by mileage range");
        System.out.println("\t 6) Search by type (sedan, truck , SUV, coupe");
        System.out.println("\t 7) List all vehicles");
        System.out.println("\t 8) Add vehicle");
        System.out.println("\t 9) Remove vehicle");
        System.out.println("\t 0) Sell/Lease vehicle");
        System.out.println("\t 99) Exit");

    }

    private void displaySearchResults(ArrayList<Vehicle> results)
    {
        System.out.printf("%-9s %-8s %-11s %-11s %-10s %-10s %-13s %s \n", "VIN", "Year", "Make", "Model", "Type", "Color", "Odometer", "Price");
        System.out.println("-------------------------------------------------------------------------------------------");

        if(results.size() == 0)
        {
            System.out.println("No search results.");
            return;
        }

        for(Vehicle vehicle: results)
        {
            System.out.printf("%-9d %-8d %-11s %-11s %-10s %-10s %-13d $ %.2f \n", vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());
        }

        System.out.println("===========================================================================================");
        System.out.println();
    }

    public void processGetByPrice()
    {
        // declare variables
        double min = getUserInputDouble("Enter the lowest price: ");
        double max = getUserInputDouble("Enter the highest price: ");

        // get all cars from the dealership
        ArrayList<Vehicle> results = dealership.getByPrice(min, max);

        // display the vehicles
        System.out.println("Search by Price: " + min + " - " + max);
        System.out.println("-------------------------------------------------------------------------------------------");

        displaySearchResults(results);
    }

    public void processGetByMakeModel()
    {

        // declare variables
        String make = getUserInputString("Enter the make: ");
        String model = getUserInputString("Enter the model: ");

        // get all cars from the dealership
        ArrayList<Vehicle> results = dealership.getByMakeModel(make, model);

        // display the vehicles
        System.out.println("Search by Make / Model: " + make + " / " + model);
        System.out.println("-------------------------------------------------------------------------------------------");

        displaySearchResults(results);
    }

    public void processGetByYear()
    {
        // declare variables
        int min = getUserInputInt("Enter the lowest year: ");
        int max = getUserInputInt("Enter the highest year: ");

        // get all cars from the dealership
        ArrayList<Vehicle> results = dealership.getByYear(min, max);

        // display the vehicles
        System.out.println("Search by Year: " + min + " - " + max);
        System.out.println("-------------------------------------------------------------------------------------------");

        displaySearchResults(results);
    }

    public void processGetByColor()
    {
        // declare variables
        String color = getUserInputString("Enter the color: ");

        // get all cars from the dealership
        ArrayList<Vehicle> results = dealership.getByColor(color);

        // display the vehicles
        System.out.println("Search by Color: " + color);
        System.out.println("-------------------------------------------------------------------------------------------");

        displaySearchResults(results);
    }


    public void processGetByMileage()
    {
        // declare variables
        int min = getUserInputInt("Enter the lowest mileage: ");
        int max = getUserInputInt("Enter the highest mileage: ");

        // get all cars from the dealership
        ArrayList<Vehicle> results = dealership.getByMileage(min, max);

        // display the vehicles
        System.out.println("Search by Mileage: " + min + " - " + max);
        System.out.println("-------------------------------------------------------------------------------------------");

        displaySearchResults(results);
    }


    public void processGetByVehicleType()
    {
        // declare variables
        String type = getUserInputString("Enter the vehicle type: ");

        // get all cars from the dealership
        ArrayList<Vehicle> results = dealership.getByType(type);

        // display the vehicles
        System.out.println("Search by Vehicle Type: " + type);
        System.out.println("-------------------------------------------------------------------------------------------");

        displaySearchResults(results);
    }


    public void processGetAllVehicles()
    {
        // get all cars from the dealership
        ArrayList<Vehicle> results = dealership.getAllVehicles();

        // display the vehicles
        System.out.println("List All Vehicles ");
        System.out.println("-------------------------------------------------------------------------------------------");

        displaySearchResults(results);
    }


    public void processAddVehicle()
    {
        System.out.println("Add New Vehicle");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println();

        int vin = getUserInputInt("Enter vin: ");
        int year = getUserInputInt("Enter year: ");
        String make = getUserInputString("Enter make: ");
        String model = getUserInputString("Enter model: ");
        String type = getUserInputString("Enter type: ");
        String color = getUserInputString("Enter color: ");
        int miles = getUserInputInt("Enter miles: ");
        double price = getUserInputDouble("Enter price: ");

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, miles, price);

        dealership.addVehicle(vehicle);

        System.out.println();
        System.out.printf("%s %s has been added.\n", vehicle.getMake(),vehicle.getModel());
        System.out.println();
    }


    public void processDeleteVehicle()
    {
        System.out.println("Remove Vehicle");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println();

        int vin = getUserInputInt("Enter vin of the car to remove: ");

        Vehicle vehicle = dealership.findByVin(vin);

        if(vehicle == null)
        {
            System.out.printf("VIN: %d was not found.\n", vin);
            return;
        }

        dealership.removeVehicle(vehicle);
        System.out.printf("Vehicle %d was successfully removed.\n", vin);
    }

    public void processSaleOrLeaseVehicle()
    {
        System.out.println("Sale or Lease Vehicle");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println();

        String contractType = getUserInputString("Enter the contract type (Sale or Lease): ");
        String contractDate = getUserInputString("Enter the contract date (yyyymmdd): ");
        String customerName = getUserInputString("Enter customer name: ");
        String customerEmail = getUserInputString("Enter customer email: ");
        int vin = getUserInputInt("Enter the VIN of vehicle: ");

        ContractFileManager contractFileManager = new ContractFileManager();
        Contract contract = null;

        // find the vehicle in the dealership
        Vehicle vehicle = dealership.findByVin(vin);
        if (vehicle == null)
        {
            System.out.printf("Vehicle with VIN %d was not found. \n", vin);
        }

        if (contractType.equalsIgnoreCase("sale"))
        {
            String isFinanced = getUserInputString("Does contract has financed?(Yes or No): ");
            contract = new SalesContract(contractDate, customerName, customerEmail, vehicle, isFinanced);
            dealership.addContract(contract);

            System.out.println("Completed Sale Contract.");
        }
        else if (contractType.equalsIgnoreCase("lease"))
        {
            contract = new LeaseContract(contractDate, customerName, customerEmail, vehicle);
            dealership.addContract(contract);

            System.out.println("Completed Lease Contract.");
        }
        else
        {
            System.out.println("Invalid contract type.");
            displayHomeScreen();
        }

        dealership.removeVehicle(vehicle);
        contractFileManager.saveContract(contract);
    }
}
