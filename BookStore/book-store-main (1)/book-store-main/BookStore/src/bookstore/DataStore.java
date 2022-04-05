package bookstore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Caleb Lam
 */
public class DataStore {
    private static DataStore instance = null;
    private static final String customersFilePath = "customers.txt";
    private static final String booksFilePath = "books.txt";
    
    private HashMap<String, Customer> customers = new HashMap<>();
    private ArrayList<Book> books = new ArrayList<>();
    
    private DataStore() {
        
    }
    
    public void loadData() {
        try {
            // Fetch customers
            File customersFile = new File(customersFilePath);
            customersFile.createNewFile();
            
            try (Scanner reader = new Scanner(customersFile)) {
                while (reader.hasNextLine()) {
                    String username = reader.nextLine();
                    String password = reader.nextLine();
                    String name = reader.nextLine();
                    int points = Integer.parseInt(reader.nextLine());
                    
                    Customer c = new Customer(username, password, name, points);
                    customers.put(username, c);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading customers: " + e.getMessage());
        }
        
        try {
            // Fetch books
            File booksFile = new File(booksFilePath);
            booksFile.createNewFile();
            
            try (Scanner reader = new Scanner(booksFile)) {
                while (reader.hasNextLine()) {
                    String title = reader.nextLine();
                    double price = Double.parseDouble(reader.nextLine());
                    Book b = new Book(title, price);
                    books.add(b);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading books: " + e.getMessage());
        }
    }
    
    public void saveData() {
        try {
            // Save customers
            File customersFile = new File(customersFilePath);
            customersFile.createNewFile();
            
            try (FileWriter writer = new FileWriter(customersFile, false)) {
                for (Customer c : customers.values()) {
                    writer.write(c.getUsername() + "\n");
                    writer.write(c.getPassword() + "\n");
                    writer.write(c.getName() + "\n");
                    writer.write(Integer.toString(c.getPoints()) + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving customers: " + e.getMessage());
        }
        
        try {
            // Save books
            File booksFile = new File(booksFilePath);
            booksFile.createNewFile();
            
            try (FileWriter writer = new FileWriter(booksFile, false)) {
                for (Book b : books) {
                    writer.write(b.getTitle() + "\n");
                    writer.write(Double.toString(b.getPrice()) + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving books: " + e.getMessage());
        }
    }
    
    public ArrayList<Customer> listCustomers() {
        ArrayList<Customer> list = new ArrayList<>();
        for (Customer c : customers.values()) {
            try {
                list.add(c.clone());
            } catch(CloneNotSupportedException ex) {
                System.out.println("Cannot clone " + c.getUsername() + ": " + ex.getMessage());
            }
        }
        
        return list;
    }
    
    public Customer registerCustomer(String username, String password, String name) throws IllegalArgumentException {
        if (!customers.containsKey(username)) {
            Customer newCustomer = new Customer(username, password, name, 0);
            customers.put(username, newCustomer);
            return newCustomer;
        } else {
            throw new IllegalArgumentException("Customer with username " + username + " already exists!");
        }
    }
    
    public Customer getCustomer(String username) {
        try {
            if (customers.containsKey(username)) {
                return customers.get(username).clone();
            } else {
                return null;
            }
        } catch(CloneNotSupportedException ex) {
            System.out.println("Cannot clone " + username + ": " + ex);
        }
        
        return null;
    }
    
    public boolean updateCustomer(Customer c) {
        try {
            if (customers.containsKey(c.getUsername())) {
                customers.put(c.getUsername(), c.clone());
                return true;
            } else {
                return false;
            }
        } catch(CloneNotSupportedException ex) {
            System.out.println("Cannot clone " + c.getUsername() + ": " + ex.getMessage());
        }
        
        return false;
    }
    
    public boolean removeCustomer(Customer c) {
        if (c == null) {
            return false;
        }
        
        return customers.remove(c.getUsername()) != null;
    }
    
    public Book registerBook(String title, double price) {
        Book newBook = new Book(title, price);
        books.add(newBook);
        return newBook;
    }
    
    public ArrayList<Book> getBooks() {
        ArrayList<Book> clonedBooks = (ArrayList<Book>)books.clone();
        for (int i = 0; i < books.size(); i++) {
            try {
                clonedBooks.set(i, books.get(i).clone());
            } catch (CloneNotSupportedException ex) {
                System.out.println("Cannot clone " + books.get(i).getTitle() + ": " + ex.getMessage());
            }
        }
        return clonedBooks;
    }
    
    public boolean removeBook(Book b) {
        if (b == null) {
            return false;
        }
        
        return books.remove(b);
    }
    
    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        
        return instance;
    }
}
