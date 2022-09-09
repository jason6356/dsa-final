package Driver;

import adt.*;
import java.util.Scanner;
import entity.*;
import java.time.LocalDateTime;
import java.util.Iterator;

public class PaymentModule {
    
    public static SetInterface<Payment> paymentSet;
    public static Scanner input = new Scanner(System.in);
    
    public static void run() {    
        paymentSet = Payment.readFile();
        
        //Menu Loop 
        int choice = 0;
        
        do {
            displayMenu();
            choice = input.nextInt();
        
            while(choice < 0 || choice > 6){
                System.out.println("Invalid Choice !");
                displayMenu();
                choice = input.nextInt();
            }
            
            performChoice(choice);
            
            
        }while(choice != 6);
        
        Payment.writeFile(paymentSet); 
    }
    
    public static void displayMenu(){
        System.out.println("Payment Menu");
        System.out.println("=========================");
        System.out.println("1. Make Payment ");
        System.out.println("2. Delete Payment");
        System.out.println("3. Update Payment");
        System.out.println("4. Display All Payments");
        System.out.println("5. Generate Profit Report");
        System.out.println("6. Exit");
        System.out.print("Enter your choice - ");
        
    }
    
    public static void performChoice(int choice){
        switch(choice){
            case 1:
                makePayment();
                break;
            case 2:
                deletePayment();
                break;
            case 3:
                updatePayment();
                break;
            case 4:
                displayAllPayment();
                break;
            case 5:
                generateProfitReport();
                break;
        }
    }
    
    
    
    public static void makePayment(){
        
        
        input.nextLine(); //buffer
        Payment py = new Payment();
        char choice = 'N';
        do{
            System.out.println("All Orders ");
            OrderModule.displayCurrentOrders();
            System.out.print("Enter Order ID to add into payment - ");
            String id = input.next();
            Order result = OrderModule.searchOrderByID(id);

            if(result != null){
               py.addOrderToPayment(result);
               System.out.println("Successfully added order into payment");
            }else
            {
                System.out.println("Invalid Order ID ");
            }
            input.nextLine();
            System.out.print("Would like add to add more order into payment ? - ");
            choice = input.next().charAt(0);
        }
        while(Character.toUpperCase(choice) != 'N');
        
        System.out.printf("Here are the subtotals - RM%.2f\n", py.calculateSubtotal());
        displayPaymentMethodMenu();
        System.out.println("Choose Your Payment Method");
        int paymentChoice = input.nextInt();
        
        while(paymentChoice < 1 || paymentChoice > 3){
            System.out.println("Invalid Choice");
            System.out.printf("Here are the subtotals - RM%.2f\n", py.calculateSubtotal());
            displayPaymentMethodMenu();
            System.out.println("Choose Your Payment Method");
            paymentChoice = input.nextInt();
        }
        
        switch(paymentChoice){
            case 1:
                py.setPaymentMethod(PaymentMethod.CASH);
                break;
            case 2:
                py.setPaymentMethod(PaymentMethod.DEBIT);
                break;
            case 3:
                py.setPaymentMethod(PaymentMethod.ONLINE_BANKING);
                break;
        
        }
        
        char confirmPay = 'N';
        
        System.out.println("Are you sure to pay? (Y/N) - ");
        confirmPay = input.next().charAt(0);
        
        if(Character.toUpperCase(confirmPay) == 'Y'){
            paymentSet.add(py);
            System.out.println("Successfully added payment!");
            
            System.out.println("Payment Made On - " + LocalDateTime.now());
            
        }
        else{
            Payment.paymentCount--;
            System.out.println("Discarded Successfully !");
        }
        
        
    }
    
    public static void displayAllPayment(){
     
        Iterator it = paymentSet.iterator();
        
        while(it.hasNext())
            System.out.println(((Payment)it.next()));
        
        
    }
    
    public static void displayPaymentMethodMenu(){
    
        System.out.println("Payment Method");
        System.out.println("=================");
        System.out.println("1. Cash ");
        System.out.println("2. Credit Card");
        System.out.println("3. Online Banking");
    
    }
    
    public static void deletePayment(){
        
        //flush buffer
        input.nextLine();
        
        displayCurrentPaymentIDs();
        System.out.print("Enter the payment ID : ");
        String id = input.nextLine();
        Payment py = new Payment(id);
        SetInterface<Payment> test = new ArraySet<>();
        Payment result = searchPaymentByID(py);
        
        if(result != null){
            System.out.println("Are you sure to remove the payment ? ");
            char confirm = input.next().charAt(0);
            
            if(Character.toUpperCase(confirm) == 'Y')
                paymentSet.remove(py);
        }
        else
            System.out.println("No such payment found!");
    }

    public static void updatePayment(){
        
        input.nextLine();
        
        displayCurrentPaymentIDs();
        System.out.print("Enter the payment ID : ");
        String id = input.nextLine();
        Payment py = new Payment(id);
        Payment result = searchPaymentByID(py);
        
        if(result != null){
            displayUpdateOption();
            int choice = input.nextInt();
            
            if(choice == 1){
                displayChangePaymentMethodOption();
                System.out.print("Enter your choice : ");
                int pmChoice = input.nextInt();
                
                switch(pmChoice){
                    case 1:
                        result.setPaymentMethod(PaymentMethod.CASH);
                        break;
                    case 2:
                        result.setPaymentMethod(PaymentMethod.DEBIT);
                        break;
                    case 3:
                        result.setPaymentMethod(PaymentMethod.ONLINE_BANKING);
                        break;
                    default:
                        break;
                }
                
                System.out.println("Successfully Update The Payment Method!");
            
            }
            else if(choice == 2){
                displayChangeOrderOption();
                int chgOrderChoice = input.nextInt();      
                if(choice == 1) addOrderIntoPayment(result);
                else if(choice == 2) removeOrderFromPayment(result);
            }
        
        }
    
    }
    
    public static void removeOrderFromPayment(Payment py){
        displayOrderIDsInPayment(py);
        int choice = input.nextInt();
        int len = py.getOrders().getNumberOfEntries();
        ListInterface<Order> orders = py.getOrders();
        
        while(choice < 1 || choice > len){
            System.out.println("Invalid Payment ID");
            displayOrderIDsInPayment(py);
            choice = input.nextInt();
        }
        input.nextLine();
        System.out.println("Are you sure to remove ? (Y/N) - ");
        char confirm = input.next().charAt(0);
        
        if(Character.toUpperCase(confirm) == 'Y')
            orders.remove(choice);
        else
            System.out.println("Successfully Discarded!");
        
    }
    
    public static void displayOrderIDsInPayment(Payment py){
        ListInterface<Order> ordersInThePayment = py.getOrders();
        System.out.println("Here are your Payment Ids to Delete : ");
        for(int i = 1; i <= ordersInThePayment.getNumberOfEntries(); i++)
            System.out.printf("%d %s\n", i, ordersInThePayment.getEntry(i).getOrderID());
        
        System.out.printf("Enter your choice (1 - %d): ", ordersInThePayment.getNumberOfEntries());
    }
    
    
    public static void addOrderIntoPayment(Payment py){
        displayFilteredOrderIDs(py);
        System.out.print("Enter the order ID to add the order into the payment : ");
        String id = input.next();
        
        Order result = OrderModule.searchOrderByID(id);
        if(result != null)
            py.addOrderToPayment(result);
        else
            System.out.println("No Such Order Found!");
    
    }
    
    public static void displayUpdateOption(){
        System.out.println("Which part would u like to update ? ");
        System.out.println("1. Payment Method ");
        System.out.println("2. Orders");
    }
    
    public static void displayChangePaymentMethodOption(){
        System.out.println("Here are the list of payment method");
        System.out.println("1. Cash");
        System.out.println("2. Credit Card/ Debit Card");
        System.out.println("3. Bank");
    
    }
    
    public static void displayChangeOrderOption(){
        System.out.println("Which operation would you like to do ?");
        System.out.println("1. Add Order into Payment");
        System.out.println("2. Remove Order from Payment");
    }
    
    
    public static void generateProfitReport(){
        double cummulativeProfit = 0.0;
        Iterator it = paymentSet.iterator();
        System.out.println("Generating.. Profit Report");
        System.out.println("Daily Profit Report ");
        System.out.println("Date - " + LocalDateTime.now().toString().split("T")[0]);
        System.out.println("======================================================");
        System.out.println("Payment ID  Orders     Amount Paid Cummulative Profit");
        while(it.hasNext()){
            Payment py = (Payment)it.next();
            ListInterface<Order> orders = py.getOrders();
            cummulativeProfit += orders.getEntry(1).calculateTotalPrice();
            System.out.printf("%-9s   %-6s     %9.2f %16.2f\n", py.getId(),orders.getEntry(1).getOrderID(),orders.getEntry(1).calculateTotalPrice(), cummulativeProfit);
            for(int i =2; i<= orders.getNumberOfEntries(); i++){
                cummulativeProfit += orders.getEntry(1).calculateTotalPrice();
                System.out.printf("%-9s   %-6s     %9.2f %16.2f\n", "", orders.getEntry(i).getOrderID(),orders.getEntry(i).calculateTotalPrice(),cummulativeProfit);
            }
            System.out.println("=================================================");
             System.out.printf("%-9s   %-6s     %9.2f %16.2f\n\n", "", "Total", py.calculateSubtotal(), cummulativeProfit);
        }
    }
    
    public static void displayFilteredOrderIDs(Payment py){
        if(py == null)
            return;
        
        ListInterface<Order> ordersInPayment = py.getOrders();

        Iterator it = OrderModule.serve.iterator();
        while(it.hasNext()){
            Order od = (Order)it.next();
            if(!ordersInPayment.contains(od))
                System.out.print(" " + od.getOrderID());
        
        }
        
        
        System.out.println("");
    }
    
    public static void displayCurrentPaymentIDs(){
        System.out.println("Current Ongoing Payment ");
        Iterator it = paymentSet.iterator();
        
        if(it.hasNext())
            System.out.printf("%s ", ((Payment)it.next()).getId());
        
        while(it.hasNext())
            System.out.printf("-> %s ", ((Payment)it.next()).getId());
    
        System.out.println("");
    }
    
    public static Payment searchPaymentByID(Payment key){
        
        Iterator it = paymentSet.iterator();
        Payment item;
        
        while(it.hasNext()){
            item = (Payment)it.next();
            //System.out.println(item);
            if(item.equals(key))
                return item;
        }
        return null;
    }
}