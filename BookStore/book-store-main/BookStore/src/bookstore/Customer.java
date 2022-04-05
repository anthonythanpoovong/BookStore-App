/*
 * Customer Class
 * COE528 
 */
package bookstore;

public class Customer implements Cloneable {
    private String name;
    private String username;
    private int points;
    private String password;
    private ShoppingCart shoppingCart;
    
    public Customer(String username, String password, String name, int points) {
        this.name = name;
        this.username = username;
        this.points = points;
        this.password = password;
        this.shoppingCart = new ShoppingCart();
    }
    
    //Getters and Setters
    public String getStatus () {
        if (points < 1000) {
            return "Silver";
        }
        else {
            return "Gold";
        }
    }

    public String getName() {
        return name;
    }
    
    public String getUsername() {
        return username;
    }

    public int getPoints() {
        return points;
    }

    public String getPassword() {
        return password;
    }

    public void setPoints (int points) {
        this.points = points;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public Customer clone() throws CloneNotSupportedException {
        Customer clonedCustomer = (Customer)super.clone();
        clonedCustomer.shoppingCart = shoppingCart.clone();
        return clonedCustomer;
    }
}
