package org.yearup.datastorages;

import org.yearup.models.Dealership;
import org.yearup.models.Vehicle;

import java.io.*;
import java.util.ArrayList;

public class DealershipFileManager
{
    public Dealership getDealership()
    {
        Dealership dealership = null;
        FileReader fileReader;
        BufferedReader reader = null;

        try
        {
            fileReader = new FileReader("inventory.csv");
            reader = new BufferedReader(fileReader);
            String line;

            String[] variable = reader.readLine().split("\\|");
            String name = variable[0];
            String address = variable[1];
            String phone = variable[2];

            ArrayList<Vehicle> inventory = new ArrayList<>();

            while ((line = reader.readLine()) != null)
            {
                variable = line.split("\\|");
                int vin = Integer.parseInt(variable[0]);
                int year = Integer.parseInt(variable[1]);
                String make = variable[2];
                String model = variable[3];
                String vehicleType = variable[4];
                String color = variable[5];
                int odometer = Integer.parseInt(variable[6]);
                double price = Double.parseDouble(variable[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                inventory.add(vehicle);
            }

            dealership = new Dealership(name, address, phone, inventory);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }

        return dealership;
    }


    public void saveDealership(Dealership dealership)
    {
        FileWriter fileWriter;
        BufferedWriter writer = null;

        try
        {
            fileWriter = new FileWriter("inventory.csv");
            writer = new BufferedWriter(fileWriter);

            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone() + "\n");

            for (Vehicle vehicle : dealership.getAllVehicles())
            {
                writer.write(vehicle.getVin() + "|" + vehicle.getYear()
                        + "|" + vehicle.getMake() + "|" + vehicle.getModel()
                        + "|" + vehicle.getType() + "|" + vehicle.getColor()
                        + "|" + vehicle.getOdometer() + "|" + vehicle.getPrice() + "\n");
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            if (writer != null)
            {
                try
                {
                    writer.close();
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
