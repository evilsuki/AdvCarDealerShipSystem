package org.yearup.models;

public class SalesContract extends Contract
{
    private final double carPrice = getVehicleSoldInfo().getPrice();
    private final String isFinanced;
    private double saleTax;
    private double recordFee;
    private double processFee;


    public SalesContract(String contractDate, String customerName, String customerEmail, Vehicle vehicleSoldInfo, String isFinanced)
    {
        super(contractDate, customerName, customerEmail, vehicleSoldInfo);
        this.isFinanced = isFinanced;
    }


    public double getSaleTax()
    {
        return 0.05 * carPrice;
    }

    public double getRecordingFee()
    {
        return 100;
    }

    public double getProcessingFee()
    {
        double processingFee;

        if (carPrice < 10000)
        {
            processingFee = 295;
        }
        else
        {
            processingFee = 495;
        }

        return processingFee;
    }

    public String isFinanced()
    {
        if (isFinanced.equalsIgnoreCase("yes") || isFinanced.equalsIgnoreCase("y"))
        {
            return "YES";
        }
        else
        {
            return "NO";
        }
    }

    public double getTotalPrice()
    {
        return carPrice + getSaleTax() + getRecordingFee() + getProcessingFee();
    }


    public double getMonthlyPayment()
    {
        double monthlyPayment = 0;

        if (isFinanced().equalsIgnoreCase("no"))
        {
            monthlyPayment = 0;
        }
        else if (isFinanced().equalsIgnoreCase("yes"))
        {
            if (getTotalPrice() > 10000)
            {
                double monthlyInterestRate = 0.0425 / 12;
                monthlyPayment = getTotalPrice() * (monthlyInterestRate / (1 - Math.pow((1 - monthlyInterestRate), 48)));
            }
            else
            {
                double monthlyInterestRate = 0.0525 / 12;
                monthlyPayment = getTotalPrice() * (monthlyInterestRate / (1 - Math.pow((1 - monthlyInterestRate), 24)));
            }
        }

        return monthlyPayment;
    }
}
