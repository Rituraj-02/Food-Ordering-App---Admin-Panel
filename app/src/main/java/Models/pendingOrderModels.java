package Models;

public class pendingOrderModels {
    String customerName,quantity;
    int foodImages;
     boolean isAccepted = false;

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public pendingOrderModels(String customerName, String quantity, int foodImages) {
        this.customerName = customerName;
        this.quantity = quantity;
        this.foodImages = foodImages;

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getFoodImages() {
        return foodImages;
    }

    public void setFoodImages(int foodImages) {
        this.foodImages = foodImages;
    }
}
