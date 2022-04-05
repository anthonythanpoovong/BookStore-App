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
public class CustomerSelectedValueFactory implements Callback<TableColumn.CellDataFeatures<Customer, Boolean>, ObservableValue<Boolean>> {
    @Override
    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Customer, Boolean> param) {
        Customer customer = param.getValue();
        SimpleBooleanProperty isSelectedProperty = new SimpleBooleanProperty(OwnerCustomersSceneController.isSelected(customer));
        isSelectedProperty.addListener((ov, oldSelectedVal, newSelectedVal) -> {
            // Add or remove the book from the shopping cart,
            // depending on whether its respective checkbox is clicked.
            if (newSelectedVal) {
                OwnerCustomersSceneController.addSelectedCustomer(customer);
            } else {
                OwnerCustomersSceneController.removeSelectedCustomer(customer);
            }
        });
        
        return isSelectedProperty;
    }
}
