package org.yearup.models;

public class LeaseContract extends Contract
{

    public LeaseContract(String contractDate, String customerName, String customerEmail, Vehicle vehicleSoldInfo)
    {
        super(contractDate, customerName, customerEmail, vehicleSoldInfo);
    }

    private final double carPrice = getVehicleSoldInfo().getPrice();
    private double monthlyPay;


    public double getExEndValue()
    {
        return carPrice / 2;
    }

    public double getLeaseFee()
    {
        return carPrice * 0.07;
    }

    public double getTotalPrice()
    {
        return (carPrice - getExEndValue()) + getLeaseFee();
    }


    public double getMonthlyPayment()
    {
        double monthlyInterestRate = 0.04 / 12;

        monthlyPay = getTotalPrice() * (monthlyPay / (1 - Math.pow((1 - monthlyInterestRate), 36)));

        return monthlyPay;
    }
}
