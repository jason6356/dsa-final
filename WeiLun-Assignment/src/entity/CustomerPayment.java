package entity;

import adt.*;


public class CustomerPayment {
    String customerID;
    String customerName;
    String payID;
    String day,month,year;
    String totalPrice;
    String payMethod;

    public CustomerPayment(String customerID, String customerName, String payID, String day, String month, String year, String totalPrice, String payMethod) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.payID = payID;
        this.day = day;
        this.month = month;
        this.year = year;
        this.totalPrice = totalPrice;
        this.payMethod = payMethod;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "CustomerPayment{" +
                "customerID='" + customerID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", payID='" + payID + '\'' +
                ", day='" + day + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", payMethod='" + payMethod + '\'' +
                '}';
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public CustomerPayment(String totalPrice) {
        this.totalPrice = totalPrice;
    }



    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPayID() {
        return payID;
    }

    public void setPayID(String payID) {
        this.payID = payID;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
