/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * The class responsible for managing scenes.
 * Uses states to control what to display.
 * @author Caleb Lam
 */
public class SceneSwitcher {
    private static SceneSwitcher instance;
    private SceneState state;
    
    private SceneSwitcher() {
        state = null;
    }
    
    public void goToNewScene(SceneState newState) throws Exception {
        state = newState;
        FXMLLoader loader = new FXMLLoader(BookStoreApp.class.getResource(newState.getFXMLFilePath()));
        Parent newRoot = loader.load();
        state.onLoad(loader);
        BookStoreApp.displayNewScene(newRoot);
    }
    
    public static SceneSwitcher getInstance() {
        if (instance == null) {
            instance = new SceneSwitcher();
        }
        
        return instance;
    }
}
