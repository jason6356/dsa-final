/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Driver;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Jason
 */
public class Driver {
    
    public static void main(String[] args) throws IOException{
 
        Scanner input = new Scanner(System.in);
                //Menu Loop 
        int choice = 0;

        do {
            displayMenu();
            choice = input.nextInt();

            while (choice < 0 || choice > 4) {
                System.out.println("Invalid Choice !");
                displayMenu();
                choice = input.nextInt();
            }

            performChoice(choice);

        } while (choice != 4);
    }
    
    public static void displayMenu(){
        System.out.println("Modules Menu");
        System.out.println("=========================");
        System.out.println("1. Order Module");
        System.out.println("2. Payment Module");
        System.out.println("3. Reservation Module");
        System.out.println("4. Exit");
        System.out.print("Enter your choice - ");
        
    }
    
        public static void performChoice(int choice){
        switch(choice){
            case 1:
                OrderModule.run();
                break;
            case 2:
                PaymentModule.run();
                break;
            case 3:
                ReservationModule.run();
                break;
        }
    }
    
}
