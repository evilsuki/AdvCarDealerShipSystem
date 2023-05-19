package org.yearup.datastorages;

import org.yearup.models.Contract;
import org.yearup.models.LeaseContract;
import org.yearup.models.SalesContract;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ContractFileManager
{
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
