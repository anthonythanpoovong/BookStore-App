/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import javafx.fxml.FXMLLoader;

/**
 *
 * @author Jerome Magpantay
 */
public class CheckoutSceneState extends SceneState{
    private boolean redeemPoints = false;

    public CheckoutSceneState() {
    
    }
    
    public CheckoutSceneState(boolean redeemPoints) {
        this.redeemPoints = redeemPoints;
    }
    
    @Override
    public String getFXMLFilePath(){
        return "CheckoutScene.fxml";
    }
    
    @Override
    public void onLoad(FXMLLoader loader){
        CheckoutSceneController controller = loader.getController();
        controller.checkoutAndDisplay(redeemPoints);
    }
}
