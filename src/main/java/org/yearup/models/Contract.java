package org.yearup.models;


public abstract class Contract
{
    private String contractDate;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSoldInfo;
    private double totalPrice;
    private double monthlyPayment;

    public Contract(String contractDate, String customerName, String customerEmail, Vehicle vehicleSoldInfo)
    {
        this.contractDate = contractDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSoldInfo = vehicleSoldInfo;

    }


    public String getContractDate()
    {
        return contractDate;
    }

    public void setContractDate(String contractDate)
    {
        this.contractDate = contractDate;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getCustomerEmail()
    {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail)
    {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleSoldInfo()
    {
        return vehicleSoldInfo;
    }

    public void setVehicleSoldInfo(Vehicle vehicleSoldInfo)
    {
        this.vehicleSoldInfo = vehicleSoldInfo;
    }


    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();
}
