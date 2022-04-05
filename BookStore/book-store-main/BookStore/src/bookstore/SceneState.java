/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import javafx.fxml.FXMLLoader;

public abstract class SceneState {
    public abstract String getFXMLFilePath();
    public abstract void onLoad(FXMLLoader loader);
}
