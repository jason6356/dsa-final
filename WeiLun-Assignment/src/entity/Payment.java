package entity;

import Driver.OrderModule;
import adt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

//Invoice

public class Payment {

    String id; //Invoice ID
    String day, month, year; //Date

    PaymentMethod paymentMethod;
    ListInterface<Order> orders;
    double subtotal;
    public static int paymentCount = 1;

    public Payment() {
        LocalDateTime now = LocalDateTime.now();
        String day, month, year;
        day = Integer.toString(now.getDayOfMonth());
        month = Integer.toString(now.getMonthValue());
        year = Integer.toString(now.getYear());
        this.id = generatePaymentID();
        this.day = day;
        this.month = month;
        this.year = year;
        this.orders = new LinkedList<>();
    }

    public Payment(String id) {
        LocalDateTime now = LocalDateTime.now();
        String day, month, year;
        day = Integer.toString(now.getDayOfMonth());
        month = Integer.toString(now.getMonthValue());
        year = Integer.toString(now.getYear());
        this.id = id;
        this.day = day;
        this.month = month;
        this.year = year;
        this.orders = new LinkedList<>();
    }

    public Payment(String id, String day, String month, String year, PaymentMethod paymentMethod) {
        this.id = id;
        this.day = day;
        this.month = month;
        this.year = year;
        this.paymentMethod = paymentMethod;
        this.orders = new LinkedList<>();
        String str = id.replaceAll("\\D+", "");
        paymentCount = Integer.parseInt(str);
        paymentCount++;
    }

    private String generatePaymentID() {
        return "PY" + paymentCount;

    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public ListInterface<Order> getOrders() {
        return orders;
    }

    public void setOrders(ListInterface<Order> orders) {
        this.orders = orders;
    }

    public void addOrderToPayment(Order order) {
        orders.add(order);
    }

    public double calculateSubtotal() {
        double total = 0;

        Iterator it = orders.iterator();

        while (it.hasNext()) {
            total += ((Order) it.next()).calculateTotalPrice();
        }

        return total;
    }

    @Override
    public String toString() {
        return String.format("ID : %s\nDate : %s:%s:%S\nPayment Method : %s\n Orders :\n %s\n Subtotal : %.2f", id,day,month,year,paymentMethod.toString(),orders,subtotal);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Payment other = (Payment) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }

        return true;
    }

    private String getFileFormat() {
        String result = String.format("%s#%s/%s/%s#%s#", this.id, this.day, this.month, this.year, this.paymentMethod.toString());

        Iterator it = orders.iterator();
        if (it.hasNext()) {
            Order od = (Order) it.next();
            result += od.getOrderID();
        }

        while (it.hasNext()) {
            Order od = (Order) it.next();
            result += "~" + od.getOrderID();
        }

        result += "\n";

        return result;
    }

    public static SetInterface<Payment> readFile() {
        final String path = "src/txt/payment.txt";
        Path pth = Paths.get(path);
        Scanner scanner;
        SetInterface<Payment> pySet = new ArraySet<>();
        try {
            scanner = new Scanner(pth);

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] data = line.split("#");
                String id = data[0];
                String date = data[1];
                String pyMethod = data[2];
                String[] orderIDs = data[3].split("~");
                String day = date.split("/")[0];
                String month = date.split("/")[1];
                String year = date.split("/")[2];
//                System.out.println("ID = " + id);
//                System.out.printf("Date = %s\n", date);
//                System.out.println("Payment Method = " + pyMethod);
//                System.out.println(String.join(" ", orderIDs));

                Payment py = new Payment(id, day, month, year, convertStringToEnum(pyMethod));
                for (String orderID : orderIDs) {
                    Order ord = OrderModule.searchOrderByID(orderID);
                    if (ord != null) {
                        py.addOrderToPayment(ord);
                    }
                }
                pySet.add(py);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pySet;
    }

    private static PaymentMethod convertStringToEnum(String data) {
        switch (data) {
            case "DEBIT":
                return PaymentMethod.DEBIT;
            case "ONLINE_BANKING":
                return PaymentMethod.ONLINE_BANKING;
            case "CASH":
                return PaymentMethod.CASH;
            default:
                return PaymentMethod.CASH;
        }
    }

    public static void writeFile(SetInterface<Payment> pySet) {
        final String path = "src/txt/payment.txt";
        File file = new File(path);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);

            Iterator it = pySet.iterator();
            while (it.hasNext()) {
                Payment py = (Payment) it.next();
                fr.write(py.getFileFormat());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
