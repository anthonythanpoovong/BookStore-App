/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookStorePurchaseSceneController {
    @FXML
    private Label welcome;
    @FXML
    private Label points;
    @FXML
    private Label status;
    
    @FXML
    private Label welcomeMessage;
    
    private Customer customer;
    private ArrayList<Book> books;
    
    @FXML
    private TableView<Book> booksTable;
    
    //The columns of the table view
    @FXML
    private TableColumn<Book, String> colName;
    @FXML
    private TableColumn<Book, Double> colPrice;
    @FXML
    private TableColumn<Book, Boolean> colSelect;
    

    private SceneState state;
    //Observable list of the table view
    private ObservableList<Book> observableBooksList;
    
    public BookStorePurchaseSceneController() { 
    }
    
    @FXML
    private void initialize() {
        // Get books and current customer
        books = DataStore.getInstance().getBooks();
        customer = Login.getInstance().getCurrentCustomer();
        
        welcomeMessage.setText("Welcome " + customer.getName() + "!" + " You have " + customer.getPoints() + " points!\n" + "Wow! You have " +
                customer.getStatus() + " status!" + " Happy shopping!");
        //Returns both Points and Status and Name of the customer
        /*
        welcome.setText(customer.getName());
        points.setText(String.format("%d", customer.getPoints()));
        status.setText(customer.getStatus());
        */
        colName.setCellValueFactory(new PropertyValueFactory("title"));
        colPrice.setCellValueFactory(new PropertyValueFactory("price"));
        colSelect.setCellValueFactory(new BookSelectedValueFactory());
        
        colSelect.setCellFactory(CheckBoxTableCell.forTableColumn(colSelect));
        
        observableBooksList = FXCollections.observableArrayList(books);
        booksTable.setItems(observableBooksList);
        booksTable.setEditable(true);
    }
    
    @FXML
    public void onBuyButtonClicked() throws Exception {
        //Switches to checkout scene
        SceneSwitcher switcher = SceneSwitcher.getInstance();
        switcher.goToNewScene(new CheckoutSceneState(false));
    }
    
    @FXML
    public void onRedeemAndBuyButtonClicked() throws Exception {
        //Switches to checkout scene
        SceneSwitcher switcher = SceneSwitcher.getInstance();
        switcher.goToNewScene(new CheckoutSceneState(true));
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
