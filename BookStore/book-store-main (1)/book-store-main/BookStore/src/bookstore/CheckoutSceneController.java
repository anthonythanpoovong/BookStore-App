/*
 * COE528
 * CheckoutSceneController
 * and open the template in the editor.
 */
package bookstore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author thanp
 */
public class CheckoutSceneController  {

    @FXML
    private Label showCost;
    @FXML
    private Label showPoints;
    @FXML
    private Label showStatus;
    
    public CheckoutSceneController () {
        
    }
    
    @FXML
    private void initialize() {
        
    }
    
    public void checkoutAndDisplay(boolean redeemPoints) {
        Customer customer = Login.getInstance().getCurrentCustomer();
        Receipt receipt = Checkout.getInstance().processCheckout(customer, redeemPoints);
        
        showCost.setText(String.format("$%.2f", receipt.getTotalCost()));
        
        //Returns both Points and Status of the customer
        showPoints.setText(String.format("%d", receipt.getPointsLeft()));
        showStatus.setText(customer.getStatus());
    }
    
    @FXML
    public void logoutButton (ActionEvent event) throws Exception {
        //Logout button function
        SceneSwitcher switcher = SceneSwitcher.getInstance();
        Login logout = Login.getInstance();
        logout.logout();
        switcher.goToNewScene(new LogoutSceneState());
    }
}
