/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Driver;

import adt.*;
import entity.*;
import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class OrderModule {

    public static ListInterface<Food> menuItems = new LinkedList<>();
    public static QueueInterface<Order> orderls = new ArrayQueue<>();
    public static QueueInterface<Order> serve = new ArrayQueue<>();
    public static Scanner input = new Scanner(System.in);
    public static String[] splitFood;
    public static boolean ranBefore = false;

    public static void run() {

        readFile("src/txt/menu.txt");
        readOrderLs();
        readServedOrder();

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

        writeServedFile();
        writeOrderLsFile();

    }

    public static void displayMenu() {
        System.out.println("Order Menu");
        System.out.println("=========================");
        System.out.println("1. Make Order ");
        System.out.println("2. Serve Order");
        System.out.println("3. Display Order Details");
        System.out.println("4. Exit");
        System.out.print("Enter your choice - ");

    }

    public static void performChoice(int choice) {
        switch (choice) {
            case 1:
                makeOrder();
                break;
            case 2:
                serveOrder();
                break;
            case 3:
                searchOrder();
                break;
        }
    }

    public static void searchOrder() {
        System.out.print("Enter Order ID to display Order Detail : ");
        String id = input.next();

        Order result = searchOrderByID(id);

        if (result == null) {
            System.out.println("No such order found");
        } else {
            System.out.println(result.toString());
        }
    }

    public static void serveOrder() {
        
        if(orderls.isEmpty()){
            System.out.println("Order is empty!");
            return;
        }
        
        boolean ordering = true;
        boolean served = false;
        Order order = orderls.getFront();
        System.out.println(order);
        while (!served) {
            //show current order
            input.nextLine();
            //confirm
            System.out.println("Is all the Food serve?");
            String serveFood = input.nextLine();
            serveFood = serveFood.toLowerCase();
            if (serveFood.equalsIgnoreCase("yes")) {
                System.out.println("All food served.");
                served = true;
                order.serveAllFood();
                Order servedOrder = orderls.dequeue();
                serve.enqueue(servedOrder);
            } else if (serveFood.equalsIgnoreCase("no")) {
                boolean allServe = false;
                String storeAns = "";
                while (!allServe) {
                    QueueInterface<Food> orderedFood = order.getOrderedFood();
                    while (orderedFood.getSize() != 0) {
//                        System.out.println(orderls.getSize());
                        System.out.println("Is Food : " + orderedFood.getFront().getFoodName() + " serve?");
                        String partialFood = input.nextLine();
                        partialFood = partialFood.toLowerCase();
                        if (partialFood.equalsIgnoreCase("yes")) {
                            order.serveFood();
                            storeAns = storeAns.concat(partialFood);
                        }
                    }
                    if (!(storeAns.contains("no"))) {
                        System.out.println("All Food serve.");
                        Order servedOrder = orderls.dequeue();
                        serve.enqueue(servedOrder);
                        allServe = true;
                        served = true;
                    }
                }
            }
        }

    }

    public static void makeOrder() {

        boolean ordering = true;
        boolean served = false;
        String orderedFood = "";
        Order order = new Order();
        System.out.println("Menu:");
        readFile("src/txt/menu.txt");
        System.out.println("Please input the Food ID(1-9) from above to order and input 'Done' when finish order:");
//        while(!served){
        while (ordering) {
            String foodOrder = input.nextLine();
            foodOrder = foodOrder.toLowerCase();

            if (foodOrder.equalsIgnoreCase("done")) {
                ordering = false;
            } else if (foodOrder.contains("done")) {

                foodOrder = foodOrder.substring(0, foodOrder.indexOf("d"));
                orderedFood = orderedFood.concat(foodOrder);
                splitFood = orderedFood.split("");
                for (String id : splitFood) {
                    Food result = searchFoodByID("F" + id);
                    if (result != null) {
                        order.addItemToOrder(result);
                    }
                }

                ordering = false;
            }
        }
        orderls.enqueue(order);

    }

    public static Order searchOrderByID(String id) {
        Iterator it = serve.iterator();

        while (it.hasNext()) {
            Order item = (Order) it.next();
            if (item.getOrderID().equals(id)) {
                return item;
            }
        }

        return null;
    }

    public static Food searchFoodByID(String id) {
        Iterator it = menuItems.iterator();

        while (it.hasNext()) {
            Food item = (Food) it.next();
            if (item.getFoodID().equals(id)) {
                return item;
            }
        }

        return null;
    }

    //write file method
    public static void writeFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/txt/foodList.txt"));
            for (String food : splitFood) {
                writer.write(food + "#");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error unable to write to file");
        }
    }

    //read file method
    public static void readFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split("#");
                if (ranBefore) {
                    System.out.println(splitLine[0] + " " + splitLine[1] + " " + splitLine[2]);

                }
                Food item = new Food(splitLine[0], splitLine[1], Double.parseDouble(splitLine[2]));
                menuItems.add(item);
            }
        } catch (IOException e) {
            System.out.println("Error unable to read file");
        }
        ranBefore = true;
    }

    public static void readOrderLs() {
        String fileName = "src/txt/orderls.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split("#");
                QueueInterface<Food> servedfood = new ArrayQueue<>();
                for (String str : splitLine[1].split("&")) {
                    System.out.println(str);
                    servedfood.enqueue(searchFoodByID(str));
                }

                System.out.println();

                Order item = new Order(splitLine[0], servedfood, new ArrayQueue<>());
                orderls.enqueue(item);
            }
        } catch (IOException e) {
            System.out.println("Error unable to read file");
        }
    }

    public static void readServedOrder() {
        String fileName = "src/txt/servedOrder.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split("#");
                QueueInterface<Food> servedfood = new ArrayQueue<>();
                for (String str : splitLine[1].split("&")) {
                    servedfood.enqueue(searchFoodByID(str));
                }

                System.out.println();

                Order item = new Order(splitLine[0], new ArrayQueue<>(), servedfood);
                serve.enqueue(item);
            }
        } catch (IOException e) {
            System.out.println("Error unable to read file");
        }
    }

    public static void displayCurrentOrders() {

        Iterator it = serve.iterator();

        if (it.hasNext()) {
            System.out.printf("%s ", ((Order) it.next()).getOrderID());
        }

        while (it.hasNext()) {
            System.out.printf("-> %s ", ((Order) it.next()).getOrderID());
        }
        System.out.println("");
    }

    public static void writeServedFile() {
        
     

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/txt/servedOrder.txt"));
            Iterator it = serve.iterator();

            while (it.hasNext()) {
                Order od = (Order) it.next();
                writer.write(od.servedtoFileFormat());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error unable to write to file");
        }
    }

    public static void writeOrderLsFile() {
        
 

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/txt/orderls.txt"));
            Iterator it = orderls.iterator();

            while (it.hasNext()) {
                Order od = (Order) it.next();
                writer.write(od.orderLstoFileFormat());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error unable to write to file");
        }
    }

}
