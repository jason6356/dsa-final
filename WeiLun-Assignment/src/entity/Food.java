
package entity;

public class Food {
    
    public String foodID;
    public String foodName;
    public double foodPrice;
    
    public Food(String foodID, String foodName, double foodPrice){
        this.foodID = foodID;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }
    
    @Override
    public String toString(){
        return String.format("%s %s %.2f\n", foodID,foodName,foodPrice);
                
    }
}
