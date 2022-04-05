package bookstore;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;

/**
 *
 * @author Jerome Magpantay
 */
public class LoginController {
    
    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameInput; 
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Label errorMessage;
    
    public LoginController(){
        
    }

   //Checks for admin login, if not passes it to check user login.
   public void checkAdminLogin() throws Exception{
        SceneSwitcher switcher = SceneSwitcher.getInstance();
        
        if(Login.getInstance().adminLogin(usernameInput.getText(), passwordInput.getText())){
            switcher.goToNewScene(new AdminStartSceneState());
        }else{
            checkUserLogin();
        }
    }
   
   
   //Login button function.
   @FXML
   public void loginAttempt(ActionEvent event) throws Exception{
       checkAdminLogin();
   }
   
   //Checks for customer login by checking if username is in hashmap, and if password elements match.
    public void checkUserLogin() throws Exception{
        SceneSwitcher switcher = SceneSwitcher.getInstance();
        
        if(Login.getInstance().customerLogin(usernameInput.getText(), passwordInput.getText())){
            switcher.goToNewScene(new BookStorePurchaseSceneState());
        }else{
            errorMessage.setText("Incorrect Username/Password, Try Again");
        }
    }
}