package bookstore;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;

/**
 *
 * @author Jerome Magpantay
 */
public class BookStoreApp extends Application {

    private static Stage appStage;
    private static BookStoreApp instance;
    private static Parent root;
    
    public BookStoreApp() {
        if (instance == null) {
            instance = this;
        } else {
            throw new RuntimeException("BookStoreApp should not be instantiated more than once!");
        }   
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        DataStore.getInstance().loadData();
        appStage = primaryStage;
        SceneSwitcher.getInstance().goToNewScene(new LogoutSceneState());
        primaryStage.setTitle("Book Store");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    
    @Override
    public void stop() {
        DataStore.getInstance().saveData();
    }
    
    public static void displayNewScene(Parent newRoot) throws Exception {
        if (newRoot == null) {
            return;
        }
        
        // Root remains changed despite stage's scene being nonexistent in case
        // the app is initializing.approot
        root = newRoot;
        
        if (appStage.getScene() != null) {
            appStage.getScene().setRoot(newRoot);
        }
    }

    public static BookStoreApp getInstance(){
        if (instance == null){
            instance = new BookStoreApp();
        }
        return instance;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
