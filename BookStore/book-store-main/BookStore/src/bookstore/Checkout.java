/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.util.ArrayList;

/**
 * The Checkout singleton contains the process of calculating the total cost.
 * @author Caleb Lam
 */
public class Checkout {
    private static Checkout instance = null;
    
    private Checkout() {
        
    }
    
    public Receipt processCheckout(Customer c, boolean redeemPoints) {
        double totalCost = 0;
        double booksCost = 0;
        int pointsLeft = c.getPoints();
        
        ArrayList<Book> booksToBuy = c.getShoppingCart().getSelectedBooks();
        for (Book b : booksToBuy) {
            DataStore.getInstance().removeBook(b);
            booksCost += b.getPrice();
        }
        totalCost = booksCost;
        
        if (redeemPoints) {
            int redeemableCost = c.getPoints() / 100;
            int discountedCost = Math.min((int)booksCost, redeemableCost);
            totalCost -= discountedCost;
            pointsLeft -= discountedCost * 100;
        }
        
        pointsLeft += ((int) totalCost) * 10;
        c.setPoints(pointsLeft);
        c.getShoppingCart().reset();
        DataStore.getInstance().updateCustomer(c);
        
        return new Receipt(totalCost, pointsLeft);
    }
    
    public static Checkout getInstance() {
        if (instance == null) {
            instance = new Checkout();
        }
        
        return instance;
    }
}
