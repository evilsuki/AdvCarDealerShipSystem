package org.yearup.datastorages;

import org.yearup.models.Contract;
import org.yearup.models.LeaseContract;
import org.yearup.models.SalesContract;
import org.yearup.models.Vehicle;

import java.io.*;
import java.util.ArrayList;

public class ContractFileManager
{
    public Contract getContract()
    {
        Contract contract = null;
        FileReader fileReader;
        BufferedReader reader = null;

        try
        {
            fileReader = new FileReader("contracts.csv");
            reader = new BufferedReader(fileReader);
            String line;

            ArrayList<Contract> contracts = new ArrayList<>();

            while ((line = reader.readLine()) != null)
            {
                String[] columns = line.split("\\|");
                String contractType = columns[0];
                String contractDate = columns[1];
                String customerName = columns[2];
                String customerEmail = columns[3];
                Vehicle vehicleInfo = new Vehicle(Integer.parseInt(columns[4]), Integer.parseInt(columns[5]), columns[6], columns[7], columns[8], columns[9], Integer.parseInt(columns[10]), Double.parseDouble(columns[11]));

                if (contractType.equalsIgnoreCase("sale"))
                {
                    double saleTax = Double.parseDouble(columns[12]);
                    double recordFee = Double.parseDouble(columns[13]);
                    double processFee = Double.parseDouble(columns[14]);
                    double totalPrice = Double.parseDouble(columns[15]);
                    String isFinanced = columns[16];
                    double monthlyPay = Double.parseDouble(columns[17]);

                    contract = new SalesContract(contractDate, customerName, customerEmail, vehicleInfo, isFinanced);
                    contracts.add(contract);
                }
                else
                {
                    double exEndValue = Double.parseDouble(columns[12]);
                    double leaseFee = Double.parseDouble(columns[13]);
                    double totalPrice = Double.parseDouble(columns[14]);
                    double monthlyPay = Double.parseDouble(columns[15]);


                }
            }
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
                catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return contract;
    }


    public void saveContract(Contract contract)
    {
        FileWriter fileWriter;
        PrintWriter writer = null;

        try
        {
            fileWriter = new FileWriter("contracts.csv", true);
            writer = new PrintWriter(fileWriter);


            if (contract instanceof SalesContract c)
            {
                writer.printf("%s|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f|%s|%.2f \n", "SALE", c.getContractDate(), c.getCustomerName(), c.getCustomerEmail(),
                        c.getVehicleSoldInfo().getVin(), c.getVehicleSoldInfo().getYear(), c.getVehicleSoldInfo().getMake(),
                        c.getVehicleSoldInfo().getModel(), c.getVehicleSoldInfo().getType(), c.getVehicleSoldInfo().getColor(),
                        c.getVehicleSoldInfo().getOdometer(), c.getVehicleSoldInfo().getPrice(), c.getSaleTax(),
                        c.getRecordingFee(), c.getProcessingFee(), c.getTotalPrice(), c.isFinanced(), c.getMonthlyPayment());
            }
            else if (contract instanceof LeaseContract l)
            {
                writer.printf("%s|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f \n", "LEASE", l.getContractDate(), l.getCustomerName(), l.getCustomerEmail(),
                        l.getVehicleSoldInfo().getVin(), l.getVehicleSoldInfo().getYear(), l.getVehicleSoldInfo().getMake(),
                        l.getVehicleSoldInfo().getModel(), l.getVehicleSoldInfo().getType(), l.getVehicleSoldInfo().getColor(),
                        l.getVehicleSoldInfo().getOdometer(), l.getVehicleSoldInfo().getPrice(), l.getExEndValue(),
                        l.getLeaseFee(), l.getTotalPrice(), l.getMonthlyPayment());
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            try
            {
                writer.close();
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
}
