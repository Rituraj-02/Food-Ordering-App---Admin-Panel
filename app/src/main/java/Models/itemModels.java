package Models;

public class itemModels {
    String name, price,discription,imageUrl;
    int menuItemImage,quantity=1;

    public itemModels() {

    }


    public itemModels(int quantity) {
        this.quantity = 1;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public itemModels(String discription) {
        this.discription = discription;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public itemModels(String name, String price, int menuItemImage) {
        this.name = name;
        this.price = price;
        this.menuItemImage = menuItemImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getMenuItemImage() {
        return menuItemImage;
    }

    public void setMenuItemImage(int menuItemImage) {
        this.menuItemImage = menuItemImage;
    }
}
