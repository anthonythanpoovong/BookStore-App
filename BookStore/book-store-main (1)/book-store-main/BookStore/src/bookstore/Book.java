package bookstore;

import javafx.scene.control.CheckBox;

/**
 *
 * @author Caleb Lam
 */
public class Book implements Cloneable {

    private double price;
    private String title;

    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public String getTitle() {
        return this.title;
    }

    /**
     * Returns if this Book is equal to a given object.
     * @param o the object to compare to.
     * @return true if o is a book and has matching title and price.
     * (Price comparison has a leeway of 1e-9)
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Book) {
            String oTitle = ((Book) o).getTitle();
            double oPrice = ((Book) o).getPrice();
            return oTitle.equals(this.title) && Math.abs(oPrice - this.price) <= 1e-9;
        } else {
            return false;
        }
    }

    @Override
    public Book clone() throws CloneNotSupportedException {
        return (Book)super.clone();
    }
}
