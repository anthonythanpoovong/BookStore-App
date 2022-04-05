/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Jerome Magpantay
 */
public class AdminStartSceneController {

    @FXML
    private Button logoutButton;
    @FXML
    private Button ownerBooksButton;
    @FXML
    private Button ownerCustomersButton;
    /**
     * Initializes the controller class.
     */

    @FXML
    private void logout(ActionEvent event) throws Exception { 
        SceneSwitcher switcher = SceneSwitcher.getInstance();
        Login logout = Login.getInstance();
        logout.logout();
        switcher.goToNewScene(new LogoutSceneState());
    }

    @FXML
    private void toAddBooks(ActionEvent event) throws Exception {
        SceneSwitcher switcher = SceneSwitcher.getInstance();
        switcher.goToNewScene(new OwnerBooksSceneState());
    }

    @FXML
    private void toAddCustomers(ActionEvent event) throws Exception {
        SceneSwitcher switcher = SceneSwitcher.getInstance();
        switcher.goToNewScene(new OwnerCustomersSceneState());
    }

}
