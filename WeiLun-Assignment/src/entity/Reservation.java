package entity;
import adt.*;

public class Reservation {
    
    private String reservationId;
    private String customerNum;
    private String day,month,year;
    
    
    public Reservation(){}
    
    public Reservation( String reservationId, String customerNum, String day,String month,String year){
        
        this.reservationId =reservationId;
        this.customerNum=customerNum;
        this.day=day;
        this.month=month;
        this.year=year;
        

    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }
    
    public void setCustomerNum(String customerNum){
        this.customerNum=customerNum;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }
    public String getCustomerNum(){
        return customerNum;
    }
    
    @Override
    public String toString(){
        return  " Reservation ID:  " +reservationId + 
                "\n Customer Number: " + customerNum + 
                "\n Date: " + day + "/" + month + "/" + year  + 
                "\n";
    }

    
    public int compareTo(Reservation o){
        int reservationCompare = this.reservationId.toLowerCase().compareTo(o.reservationId.toLowerCase());
        return reservationCompare;
    }
      
    
    
    /*public void addCustomerIntoReservation(Customer c)
    {
        customerList.add(c);
    }*/
    

    
}
