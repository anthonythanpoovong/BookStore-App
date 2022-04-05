/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.util.ArrayList;

/**
 * The shopping cart consists of the list of Books to purchase.
 * @author Caleb Lam
 */
public class ShoppingCart implements Cloneable {
    private ArrayList<Book> books;
    
    public ShoppingCart() {
        books = new ArrayList<>();
    }
    
    public boolean contains(Book b) {
        return books.contains(b);
    }
    
    public boolean add(Book b) {
        if (b == null) {
            return false;
        }
        
        if (!this.contains(b)) {
            return books.add(b);
        }
        
        return true;
    }
    
    public boolean remove(Book b) {
        if (b != null) {
            return books.remove(b);
        } else {
            return false;
        }
    }
    
    public ArrayList<Book> getSelectedBooks() {
        ArrayList<Book> clonedList = (ArrayList<Book>)books.clone();
        for (int i = 0; i < clonedList.size(); i++) {
            try {
                Book bookClone = clonedList.get(i).clone();
                clonedList.set(i, bookClone);
            } catch(CloneNotSupportedException e) {
                System.out.println("Failed to clone " + clonedList.get(i).getTitle());
            }
        }
        
        return clonedList;
    }
    
    public void reset() {
        books.clear();
    }

    @Override
    public ShoppingCart clone() throws CloneNotSupportedException {
        ShoppingCart clonedCart = (ShoppingCart)super.clone();
        clonedCart.books = getSelectedBooks();
        return clonedCart;
    }
    
    
}
