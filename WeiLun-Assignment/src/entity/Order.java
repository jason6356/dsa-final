
package entity;
import adt.*;
import java.util.Iterator;

public class Order {
    
    private String orderID;
    private QueueInterface<Food> orderedFood;
    private QueueInterface<Food> servedFood;
    private int currentFoodCount = 0;
    private static int totalOrders = 0;
    
    public Order(String orderID) {
        this.orderID = orderID;
        this.currentFoodCount = 0;
        this.orderedFood = new ArrayQueue<>();
        this.servedFood = new ArrayQueue<>();
        totalOrders++;
    }
    
    public Order(String orderID,QueueInterface<Food> orderedFood,QueueInterface<Food> servedFood){
        this.orderID = orderID;
        this.orderedFood = orderedFood;
        this.servedFood = servedFood;
        this.currentFoodCount = orderedFood.getSize() > 0 ? orderedFood.getSize() : servedFood.getSize();    
        String str = orderID.replaceAll("\\D+", "");
        totalOrders = Integer.parseInt(str);
        totalOrders++;        
    }

    public Order() {
        this("OD" + (totalOrders + 1));
    }
  
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void addItemToOrder(Food item){
         orderedFood.enqueue(item);
         currentFoodCount++;
    }
    
    public void serveFood(){
        servedFood.enqueue(orderedFood.dequeue());
    }

    public void serveAllFood(){
        QueueInterface<Food> temp = servedFood;
        servedFood = orderedFood;
        orderedFood = temp;
    }
    
    public QueueInterface<Food> getOrderedFood() {
        return orderedFood;
    }

    public int getCurrentFoodCount() {
        return currentFoodCount;
    }
    
    public double calculateTotalPrice(){
        Iterator it = servedFood.iterator();

        double total = 0;
                
        while(it.hasNext()){
            Food food = (Food)it.next();
            total += food.getFoodPrice();
        }
        return total;
    }
    
    public String queueToString(){
        Iterator it = servedFood.iterator();
        String result = "";
        while(it.hasNext()){
            result += ((Food)it.next()).toString();
        }
    
        return result;
    }
    
    public String qToFileFormat(QueueInterface<Food> q){
        Iterator it = q.iterator();
        String result = "";
        while(it.hasNext()){
            result += ((Food)it.next()).getFoodID() + "&";
        }
        result = result.substring(0, result.length()-1);
        result += "\n";
        return result;
    }
    
    public String servedtoFileFormat(){
        return String.format("%s#%s",orderID,qToFileFormat(servedFood));
    }
    
    public String orderLstoFileFormat(){
        return String.format("%s#%s",orderID,qToFileFormat(orderedFood));
    }

    @Override
    public String toString() {
        return String.format("Order ID : %s\nServed Food:\n%sFood Count : %d\nTotal Amount:%.2f",orderID,queueToString(),currentFoodCount,calculateTotalPrice());
    }
    
    
}
