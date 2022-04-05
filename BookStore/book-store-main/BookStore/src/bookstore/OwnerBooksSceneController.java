/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;






/**
 * 
 * @author Alan Mathew
 */
public class OwnerBooksSceneController {
    
    @FXML
    public TextField textFieldBook;
    @FXML
    public TextField textFieldPrice;
    
    @FXML
    private TableView<Book> booksTable;
    
    //The columns of the table view
    @FXML
    private TableColumn<Book, String> colName;
    @FXML
    private TableColumn<Book, Double> colPrice;
    
    private ObservableList<Book> bookList;
    
    
    @FXML
    private void initialize(){
        DataStore ds = DataStore.getInstance();
        
        colName.setCellValueFactory(new PropertyValueFactory("title"));
        colPrice.setCellValueFactory(new PropertyValueFactory("price"));
        
        bookList = FXCollections.observableArrayList(ds.getBooks());
        
        booksTable.setItems(bookList);
    }
    
    @FXML
    public void addBook(ActionEvent actionEvent){
        try {
            DataStore ds = DataStore.getInstance();
            Book book = ds.registerBook(textFieldBook.getText(), Double.parseDouble(textFieldPrice.getText()));
            bookList.add(book);
        } catch (NumberFormatException ex) {
            // add error message
            System.out.println("Invalid price!");
        }
    }
    
    
    @FXML
    public void back(ActionEvent actionEvent) throws Exception {
        SceneSwitcher switcher = SceneSwitcher.getInstance();
        switcher.goToNewScene(new AdminStartSceneState());
    }
}


    

