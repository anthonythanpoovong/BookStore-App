package bookstore;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * A CellValueFactory for the Book object's selected property.
 * Toggling this boolean property will add or remove its respective book in the
 * shopping cart.
 * @author Caleb Lam
 */
public class BookSelectedValueFactory implements Callback<TableColumn.CellDataFeatures<Book, Boolean>, ObservableValue<Boolean>> {
    @Override
    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Book, Boolean> param) {
        Book book = param.getValue();
        ShoppingCart shoppingCart = Login.getInstance().getCurrentCustomer().getShoppingCart();
        SimpleBooleanProperty isSelectedProperty = new SimpleBooleanProperty(shoppingCart.contains(book));
        isSelectedProperty.addListener((ov, oldSelectedVal, newSelectedVal) -> {
            // Add or remove the book from the shopping cart,
            // depending on whether its respective checkbox is clicked.
            if (newSelectedVal) {
                shoppingCart.add(book);
            } else {
                shoppingCart.remove(book);
            }
        });
        
        return isSelectedProperty;
    }
}
