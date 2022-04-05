/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

/**
 * This is a class representing a basic receipt
 * @author Caleb Lam
 */
public class Receipt {
    private double totalCost;
    private int pointsLeft;

    public Receipt(double totalCost, int pointsLeft) {
        this.totalCost = totalCost;
        this.pointsLeft = pointsLeft;
    }

    public int getPointsLeft() {
        return pointsLeft;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
