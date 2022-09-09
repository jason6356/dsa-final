package entity;
import adt.*;
import entity.*;


public class Customer {
    
    private String custNo;
    private String custName;
    private String custPhoneNum;
    private String custEmail;
    private ListInterface<Reservation> reservationList;

    public Customer() {
    }
    
    public Customer(String custNo, String custName, String custPhoneNum, String custEmail) {
        this.custNo = custNo;
        this.custName = custName;
        this.custPhoneNum = custPhoneNum;
        this.custEmail = custEmail;
        this.reservationList = new LinkedList<>();
    }

    
    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPhoneNum() {
        return custPhoneNum;
    }

    public void setCustPhoneNum(String custPhoneNum) {
        this.custPhoneNum = custPhoneNum;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }
    
        public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }
    
        @Override
    /*public String toString() {
        return "\n Customer Information\n" + 
                " Customer Name: " + custName + 
                "\n Customer Phone Number: " + custPhoneNum + 
                "\n Customer Email:" + custEmail + 
                "\n\n Reservation Information\n" + reservationList.getEntry(Integer.parseInt(custNo))+ 
                "\n";
    }*/
    public String toString() {
        return  "\n Customer Information\n" + 
                " Customer Name: " + custName + 
                "\n Customer Phone Number: " + custPhoneNum + 
                "\n Customer Email:" + custEmail + 
                "\n\n Reservation Information\n" + reservationList.getEntry(Integer.parseInt(custNo))+ 
                "\n";
    }
    
      //Reservation's Details

    public ListInterface<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(ListInterface<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

   
    
}
