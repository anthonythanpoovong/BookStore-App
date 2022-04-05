/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;






/**
 *
 * @author Alan Mathew
 */
public class OwnerCustomersSceneController {
    private static ArrayList<Customer> selectedCustomers = new ArrayList<>();
    
    @FXML
    private TextField textFieldName;
    
    @FXML
    private TextField textFieldUsername;
    
    @FXML
    private TextField textFieldPassword;
    
    @FXML
    private TableView<Customer> customersTable;
    
    @FXML
    private TableColumn<Customer,String> colName;
    
    @FXML
    private TableColumn<Customer, String> colUsername;
    
    @FXML
    private TableColumn<Customer, String> colPassword;
    
    @FXML
    private TableColumn<Customer, Integer> colPoints;
    
    @FXML
    private TableColumn<Customer, Boolean> colSelect;
    
    private ObservableList<Customer> customersList;
    
    @FXML
    private void initialize(){
        DataStore ds = DataStore.getInstance();
        
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colUsername.setCellValueFactory(new PropertyValueFactory("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory("password"));
        colPoints.setCellValueFactory(new PropertyValueFactory("points"));
        colSelect.setCellValueFactory(new CustomerSelectedValueFactory());
        
        colSelect.setCellFactory(CheckBoxTableCell.forTableColumn(colSelect));
        
        customersList = FXCollections.observableArrayList(ds.listCustomers());
        
        customersTable.setItems(customersList);
        customersTable.setEditable(true);
    }
    
    public static boolean addSelectedCustomer(Customer c) {
        if (c == null) {
            return false;
        }
        
        if (!selectedCustomers.contains(c)) {
            return selectedCustomers.add(c);
        } else {
            return false;
        }
    }
    
    public static boolean removeSelectedCustomer(Customer c) {
        if (c == null) {
            return false;
        }
        
        return selectedCustomers.remove(c);
    }
    
    public static boolean isSelected(Customer c) {
        if (c == null) {
            return false;
        }
        
        return selectedCustomers.contains(c);
    }
    
    @FXML
    public void buttonAdd(ActionEvent actionEvent){
        try {
            DataStore ds = DataStore.getInstance();
            Customer customer = ds.registerCustomer(textFieldUsername.getText(), textFieldPassword.getText(), textFieldName.getText());
            customersList.add(customer);
        } catch(IllegalArgumentException ex) {
            
        }
    }
    
    @FXML
    public void buttonDelete(ActionEvent actionEvent){
        DataStore ds = DataStore.getInstance();
        for (Customer c : selectedCustomers) {
            ds.removeCustomer(c);
            customersList.remove(c);
        }
    }
    
    @FXML
    public void buttonBack(ActionEvent actionEvent) throws Exception {
        SceneSwitcher switcher = SceneSwitcher.getInstance();
        switcher.goToNewScene(new AdminStartSceneState());
    }
}

