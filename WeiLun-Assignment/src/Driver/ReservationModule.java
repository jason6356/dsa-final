package Driver;
import adt.*;
import entity.*;

//import necessary library
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.regex.Pattern;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
//import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationModule {

    private static final Scanner input = new Scanner(System.in);
    private static final ListInterface<Reservation> reservationList = new LinkedList<Reservation>();
    //private static final ListInterface<Reservation> deletedList = new LinkedList<Reservation>();
    private static final ListInterface<Customer> customerList = new LinkedList<Customer>();
    public static String[] tempId = new String[10];
    public static String[] tempNum = new String[10];
    public static String[] tempDay = new String[10];
    public static String[] tempMonth = new String[10];
    public static String[] tempYear = new String[10];
    public static String[] tempName = new String[10];
    public static String[] tempPhoneNum = new String[10];
    public static String[] tempcustEmail = new String[10];
    public static String[] tempNo = new String[10];
    public static int store = 0;
    public static String custNo = "";

    public static void run() {
        //Reservation reservation1 = new Reservation("Lim Meng Loeng", "5","22","7","2022");
        //Reservation reservation2 = new Reservation("Wong Jeng Liang", "8","23","7", "2022");
        // Reservation reservation3 = new Reservation("Loh Wei Lun", "9","24","7","2022");
        //reservationList.Create(reservation1);
        //reservationList.Create(reservation2);
        //reservationList.Create(reservation3);
        int choose;
        Scanner input = new Scanner(System.in);

        reservationTable();

    }

    public static void reservationTable() {
        int choose;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("+======================+");
            System.out.println("|Reservation Menu      |");
            System.out.println("+======================+");
            System.out.println("|1. Add Reservation    |");
            System.out.println("|2. Remove Reservation |");
            System.out.println("|3. Display Reservation|");
            System.out.println("+======================+");
            System.out.println("|Extra Features??      |");
            System.out.println("+======================+");
            System.out.println("|4. Clear the Table    |"); // without using adt
            System.out.println("|5. Edit Reservation   |");
            System.out.println("|6. Search Reservation |");
            System.out.println("+======================+");
            System.out.printf("Please select your option: ");
            choose = input.nextInt();
            System.out.println("");
            System.out.println("");

            switch (choose) {
                case 1:
                    addReservation();
                    break;
                case 2:
                    removeReservation();
                    break;
                case 3:
                    displayReservation();
                    break;
                case 4:
                    clearTable();
                    break;
                case 5:
                    editReservation();
                    break;
                case 6:
                    searchReservation();
                    break;
                default:
                    System.out.println("Please enter the correct number in range 1 to 6");
                    break;
            }
        } while (choose > 0 && choose <= 6);
    }

    public static void addReservation() {

        boolean continueAdd = false;

        System.out.println("+===========================+");
        System.out.println("+ Add Customer Information  +");
        System.out.println("+===========================+");
        input.nextLine();
        System.out.printf("Please input the Customer Name           : ");
        String custName = input.nextLine();

        for (int i = 0; i < customerList.getNumberOfEntries(); i++) {
            if (customerList.getEntry(i + 1).getCustName().compareTo(custName) == 0) {
                System.out.println(customerList.getEntry(i + 1) + "\n");
                continueAdd = true;
            }
            else
                continueAdd = false;
        }
        
        if (continueAdd == true) {
            System.out.println("This customer is exist in the reservation! Do u sure u want to keep adding?: ");
            System.out.printf("Y for keep adding, N for stop adding: ");
            char confirm = input.next().charAt(0);

            if (confirm == 'y' || confirm == 'Y') {
                input.nextLine();
                System.out.printf("Please input the Customer Phone Number   : ");
                String custPhoneNum = input.nextLine();
                System.out.printf("Please input the Customer Email          : ");
                String custEmail = input.nextLine();
                System.out.println("");

                System.out.println("+===========================+");
                System.out.println("+ Add Resevation Information+");
                System.out.println("+===========================+");
                System.out.printf("Please input the Reservation Id    : ");
                String reservationId = input.nextLine();
                System.out.printf("Please input the Customer Number   : ");
                String customerNum = input.nextLine();
                System.out.printf("Please input the Reservation Day   : ");
                String day = input.nextLine();
                System.out.printf("Please input the Reservation Month : ");
                String month = input.nextLine();
                System.out.printf("Please input the Reservation Year  : ");
                String year = input.nextLine();
                System.out.println("");

                custNo = "" + (store + 1);

                try {
                    FileWriter myWriter = new FileWriter("src/txt/reservation.txt", true);
                    myWriter.write(custNo + "#");
                    myWriter.write(custName + "#");
                    myWriter.write(custPhoneNum + "#");
                    myWriter.write(custEmail + "#");
                    myWriter.write(reservationId + "#");
                    myWriter.write(customerNum + "#");
                    myWriter.write(day + "#");
                    myWriter.write(month + "#");
                    myWriter.write(year + "\n");
                    myWriter.close();
                } catch (IOException e) {
                    System.out.println("Cannot Write into the file! ");
                    e.printStackTrace();
                }
                store++;
            } else {
                System.out.println("Add Cancelled");
            }
        }else if(continueAdd == false){
            System.out.printf("Please input the Customer Phone Number   : ");
                String custPhoneNum = input.nextLine();
                System.out.printf("Please input the Customer Email          : ");
                String custEmail = input.nextLine();
                System.out.println("");

                System.out.println("+===========================+");
                System.out.println("+ Add Resevation Information+");
                System.out.println("+===========================+");
                System.out.printf("Please input the Reservation Id    : ");
                String reservationId = input.nextLine();
                System.out.printf("Please input the Customer Number   : ");
                String customerNum = input.nextLine();
                System.out.printf("Please input the Reservation Day   : ");
                String day = input.nextLine();
                System.out.printf("Please input the Reservation Month : ");
                String month = input.nextLine();
                System.out.printf("Please input the Reservation Year  : ");
                String year = input.nextLine();
                System.out.println("");

                custNo = "" + (store + 1);

                try {
                    FileWriter myWriter = new FileWriter("src/txt/reservation.txt", true);
                    myWriter.write(custNo + "#");
                    myWriter.write(custName + "#");
                    myWriter.write(custPhoneNum + "#");
                    myWriter.write(custEmail + "#");
                    myWriter.write(reservationId + "#");
                    myWriter.write(customerNum + "#");
                    myWriter.write(day + "#");
                    myWriter.write(month + "#");
                    myWriter.write(year + "\n");
                    myWriter.close();
                } catch (IOException e) {
                    System.out.println("Cannot Write into the file! ");
                    e.printStackTrace();
                }
                store++;
        }

        // reservationList.Create(new Reservation(clientName, clientNum, day, month, year));
        //reservationList.create(new Reservation(clientName, clientNum, day, month, year));
        //writeFile();
    }

    public static void removeReservation() {
        boolean continueInput = true;
        //char confirmDelete = 'Y', toDeleteMore = 'Y';
        int line = 0;

        System.out.println("+=====================+");
        System.out.println("+ Check In Resevation +");
        System.out.println("+=====================+");
        System.out.println("");

        if (!customerList.isEmpty()) {

            System.out.println(customerList.toString());

            do {
                try {
                    System.out.print("\nCheck In [Plese enter the digit for removing the reservation]: ");
                    line = input.nextInt();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println("Please enter digit only. Thanks~~~\n");
                    continueInput = true;
                    input.nextLine();
                }
            } while (continueInput);

            System.out.println("Deleting... " + reservationList.getEntry(line).getReservationId());

            Customer deletedCust = customerList.remove(line);
            //Reservation deletedRs = reservationList.remove(line);
            System.out.println("Customer Deleted\n" + deletedCust + "\n");
            System.out.println("Customer \n" + customerList.toString() + "\n");

        }
        FileWriter fw;
        PrintWriter pw;
        File file = new File("src/txt/reservation.txt");

        //=========================
        for (int current = 0; current < customerList.getNumberOfEntries(); current++) {

            if (current == line - 1) {
                if (tempId[line] == null) {

                    tempName[current] = "";
                    tempPhoneNum[current] = "";
                    tempcustEmail[current] = "";
                    tempId[current] = "";
                    tempNum[current] = "";
                    tempDay[current] = "";
                    tempMonth[current] = "";
                    tempYear[current] = "";

                } else {
                    //current is normal loop start from 0
                    //select is use to duplicate the front value to the current

                    tempName[current] = tempName[line];
                    tempPhoneNum[current] = tempPhoneNum[line];
                    tempcustEmail[current] = tempcustEmail[line];
                    tempId[current] = tempId[line];
                    tempDay[current] = tempDay[line];
                    tempMonth[current] = tempMonth[line];
                    tempYear[current] = tempYear[line];

                }
                line++;
            }
        }//Looping to overwrite data
        --store;
        //=========================

        try {
            fw = new FileWriter(file, false);
            fw.write("");
            fw.close();

        } catch (IOException ex) {
            Logger.getLogger(ReservationModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int n = 0; n < customerList.getNumberOfEntries(); n++) {
            try {
                fw = new FileWriter(file, true);
                Scanner ReservationReader = new Scanner(file);

                fw.write(tempNo[n] + "#");
                fw.write(tempName[n] + "#");
                fw.write(tempPhoneNum[n] + "#");
                fw.write(tempcustEmail[n] + "#");
                fw.write(tempId[n] + "#");
                fw.write(tempNum[n] + "#");
                fw.write(tempDay[n] + "#");
                fw.write(tempMonth[n] + "#");
                fw.write(tempYear[n] + "\n");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Clear();
    }

    public static void displayReservation() {
        Clear();
        readFile();
        System.out.println(customerList.toString());
    }

    public static void clearTable() {

        //getDayMonthYear();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));

        System.out.println(customerList.toString());
        System.out.println("");

        //Input the current Day 
        System.out.println("Today Day: " + now.getDayOfMonth());
        int iptDay = now.getDayOfMonth() - 1;
        int line = 1;

        //Read the data from TXT
        File file = new File("src/txt/reservation.txt");
        FileWriter fw;
        PrintWriter pw;

        int count = 0;
        Reservation deletedRs = new Reservation(); // n = delete which row
        int rmv = 1;
        //System.out.println(store);

        //Compare the yesterday with the data in the txt file
        System.out.println("The reservation record which B4 & on " + iptDay + " will be removed!!! ");

        for (int n = 0; n < store; n++) {
            if (Integer.parseInt(tempDay[n]) <= iptDay) { // <= less than equal will be removed( more than equal will be saved)
                Customer deletedCust = customerList.remove((rmv));
                System.out.println("Data Deleted!!!");
                System.out.println(deletedCust);
                rmv--;
                //Reservation deletededRs = reservationList.getEntry(n+1);
            } else{
                
                tempName[count] = tempName[n];
                tempPhoneNum[count] = tempPhoneNum[n];
                tempcustEmail[count] = tempcustEmail[n];
                tempId[count] = tempId[n];
                tempNum[count] = tempNum[n];
                tempDay[count] = tempDay[n];
                tempMonth[count] = tempMonth[n];
                tempYear[count] = tempYear[n];
                count++;
                
            }
            rmv++;
        }
        System.out.println("+================+");
        System.out.println("| Lasted Data!!! |");
        System.out.println("+================+");
        System.out.println(customerList.toString());

        try {
            fw = new FileWriter(file, false);
            fw.write("");
            fw.close();

        } catch (IOException ex) {
            Logger.getLogger(ReservationModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        //write into file
        for (int n = 0; n < customerList.getNumberOfEntries(); n++) {
            try {
                fw = new FileWriter(file, true);
                Scanner ReservationReader = new Scanner(file);

                fw.write(tempNo[n] + "#");
                fw.write(tempName[n] + "#");
                fw.write(tempPhoneNum[n] + "#");
                fw.write(tempcustEmail[n] + "#");
                fw.write(tempId[n] + "#");
                fw.write(tempNum[n] + "#");
                fw.write(tempDay[n] + "#");
                fw.write(tempMonth[n] + "#");
                fw.write(tempYear[n] + "\n");

                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Clear();
        reservationTable();
    }

    public static void editReservation() {
        Scanner edit = new Scanner(System.in);
        String id = "", num = "", day = "", month = "", year = "", name = "", phoneNum = "", email = "";

        int select = 0;
        if (!customerList.isEmpty()) {
            System.out.println("\n+======================+");
            System.out.println("|   Edit Reservation   |");
            System.out.println("+======================+");
            System.out.println(customerList.toString());
            System.out.printf("Enter the number to edit :");
            select = edit.nextInt();
            edit.nextLine();
            System.out.println("+==========================================+");
            System.out.println("|    Reservation & Customer Information    |");
            System.out.println("+==========================================+");
            System.out.println(customerList.getEntry(select));

            //Editting part
            System.out.printf("Enter the new customer name                   :");
            name = edit.nextLine();
            System.out.printf("Enter the new customer phone number           :");
            phoneNum = edit.nextLine();
            System.out.printf("Enter the new customer email                  :");
            email = edit.nextLine();
            System.out.printf("Enter the new reservation ID                   :");
            id = edit.nextLine();
            System.out.printf("Enter the new customer number of reservation   :");
            num = edit.nextLine();
            System.out.printf("Enter the new day of reservation               :");
            day = edit.nextLine();
            System.out.printf("Enter the new month of reservation             :");
            month = edit.nextLine();
            System.out.printf("Enter the new year of reservation              :");
            year = edit.nextLine();

            //Looping to overwrite data
            System.out.printf("Do you confirm to edit this reservation? (Y/N) :");
            String confirm = edit.nextLine();
            if (confirm.toUpperCase().equals("Y")) {

                customerList.replace(select, new Customer(custNo, name, phoneNum, email));
                reservationList.replace(select, new Reservation(id, num, day, month, year));
                customerList.getEntry(select).setReservationList(reservationList);
                //System.out.println(customerList.getEntry(select).getReservationList());
                System.out.println();

                //Part of overwrite to txt file
                //tempNo[select - 1] = custNo;
                tempId[select - 1] = id;
                tempNum[select - 1] = num;
                tempDay[select - 1] = day;
                tempMonth[select - 1] = month;
                tempYear[select - 1] = year;
                tempName[select - 1] = name;
                tempPhoneNum[select - 1] = phoneNum;
                tempcustEmail[select - 1] = email;

                writeFile();
                System.out.println(id + " has successfully edited\n");

                Clear();
            } else {
                System.out.println("The edit action has been cancelled..");
            }//Confirm remove loop

        } else {
            System.out.println("List is empty! ");
        }
    }

    public static void searchReservation() {
        Scanner search = new Scanner(System.in);
        int detect = 0;
        String name = "", phoneNum = "", email = "";

        if (!reservationList.isEmpty()) {
            System.out.println("\n+========================+");
            System.out.println("|   Search Reservation   |");
            System.out.println("+========================+");
            System.out.println("|    1. Name             |");
            System.out.println("|    2. Phone Number     |");
            System.out.println("|    3. Email            |");
            System.out.println("+========================+");

            System.out.printf("Plese choose 1-3 to search: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1: {
                    System.out.printf("Enter the customer name: ");
                    name = search.nextLine();

                    //System.out.println(reservationList.contains(new Reservation(name, name)));
                    for (int i = 0; i < customerList.getNumberOfEntries(); i++) {
                        if (customerList.getEntry(i + 1).getCustName().compareTo(name) == 0) {
                            //System.out.println(customerList.getEntry(i+1));
                            //System.out.println(reservationList.getEntry(i+1));

                            detect++;
                            if (detect > 1) {
                                System.out.println(customerList.getEntry(i + 1).getReservationList().getEntry(i + 1));
                            } else {
                                System.out.println(customerList.getEntry(i + 1));
                            }
                        }
                    }
                }
                break;
                case 2: {
                    System.out.printf("Enter the customer phone number: ");
                    phoneNum = search.nextLine();

                    //System.out.println(reservationList.contains(new Reservation(name, name)));
                    for (int i = 0; i < customerList.getNumberOfEntries(); i++) {
                        if (customerList.getEntry(i + 1).getCustPhoneNum().compareTo(phoneNum) == 0) {
                            //System.out.println(customerList.getEntry(i + 1) + "\n");
                            detect++;
                            if (detect > 1) {
                                System.out.println(customerList.getEntry(i + 1).getReservationList().getEntry(i + 1));
                            } else {
                                System.out.println(customerList.getEntry(i + 1));
                            }
                        }
                    }
                }
                break;
                case 3: {
                    System.out.printf("Enter the customer email: ");
                    email = search.nextLine();

                    //System.out.println(reservationList.contains(new Reservation(name, name)));
                    for (int i = 0; i < customerList.getNumberOfEntries(); i++) {
                        if (customerList.getEntry(i + 1).getCustEmail().compareTo(email) == 0) {
                            //System.out.println(customerList.getEntry(i + 1) + "\n");
                            detect++;
                            if (detect > 1) {
                                System.out.println(customerList.getEntry(i + 1).getReservationList().getEntry(i + 1));
                            } else {
                                System.out.println(customerList.getEntry(i + 1));
                            }
                        }
                    }
                }
                break;
            }

            if (detect == 0) {
                System.out.println("No exist in the reservation List\n\n");
            }

        } else {
            System.out.println("No exist in the reservation List\n\n");
        }
    }

    public static void Clear() {
        System.out.print("Notice!!! Reservation List is being clear at this step, data now is in txt file press display to store the data into List!!!\n\n");
        //System.out.println("");
        //char confirm = input.next().charAt(0);
        //if (confirm == 'y' || confirm == 'Y') {
        reservationList.clear();
        customerList.clear();
        //} else {
        //    System.out.println("Clear Cancel");
        //}

    }

    public static void readFile() {

        try {
            File reservation = new File("src/txt/reservation.txt");
            Scanner ReservationReader = new Scanner(reservation);
            //Scanner fileData = new Scanner(new File("reservation.txt"));
            StringTokenizer token = null;
            String no = "";
            String reservationId = " ";
            String customerNum = " ";
            String day = " ";
            String month = "";
            String year = "";
            String custName = "";
            String custPhoneNum = "";
            String custEmail = "";

            int i = 0;
            store = 0;
            while (ReservationReader.hasNextLine()) {

                //String data = (ReservationReader.nextLine());
                token = new StringTokenizer(ReservationReader.nextLine(), "#");

                no = token.nextToken();
                custName = token.nextToken();
                custPhoneNum = token.nextToken();
                custEmail = token.nextToken();
                reservationId = token.nextToken();
                customerNum = token.nextToken();
                day = token.nextToken();
                month = token.nextToken();
                year = token.nextToken();

                tempNo[i] = no;
                tempName[i] = custName;
                tempPhoneNum[i] = custPhoneNum;
                tempcustEmail[i] = custEmail;
                tempId[i] = reservationId;
                tempNum[i] = customerNum;
                tempDay[i] = day;
                tempMonth[i] = month;
                tempYear[i] = year;
                i++;

//                System.out.printf("Reservation ID: %s\nCustomer Number: %s\nReservation Date: %s-%s-%s\n", reservationId, customerNum, day, month, year);
//                System.out.printf("Customer Name: %s\nCustomer Phone Number: %s\nCustomer Email: %s\n\n", custName, custPhoneNum, custEmail);
                //reservationList.Create(reservation2);
                //reservationList.add(reservation2);
                customerList.add(new Customer(no, custName, custPhoneNum, custEmail));
                reservationList.add(new Reservation(reservationId, customerNum, day, month, year));
                customerList.getEntry(i).setReservationList(reservationList);
                store++;
            }
            ReservationReader.close();

        } catch (FileNotFoundException ex) {
            //Logger.getLogger(ReservationClient.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No such file");
        }
    }

    public static void writeFile() {

        FileWriter fw;
        File file = new File("src/txt/reservation.txt");

        try {
            fw = new FileWriter(file, false);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(ReservationModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < store; i++) {
            try {
                fw = new FileWriter(file, true);
                fw.write(tempNo[i] + "#");
                fw.write(tempName[i] + "#");
                fw.write(tempPhoneNum[i] + "#");
                fw.write(tempcustEmail[i] + "#");
                fw.write(tempId[i] + "#");
                fw.write(tempNum[i] + "#");
                fw.write(tempDay[i] + "#");
                fw.write(tempMonth[i] + "#");
                fw.write(tempYear[i] + "\n");

                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(ReservationModule.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
