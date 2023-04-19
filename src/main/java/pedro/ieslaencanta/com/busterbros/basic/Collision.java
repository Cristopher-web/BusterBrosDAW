/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

import javafx.geometry.Point2D;

/**
 *
 * @author DAWTarde
 */
public class Collision {
    private Element a;
    private Element b;
    private double distance;
    private Point2D separator;

    public Collision() {
    }

    public Collision(Element a, Element b, double distance, Point2D separator) {
        this.a = a;
        this.b = b;
        this.distance = distance;
        this.separator = separator;
    }
    
    

    public Element getA() {
        return a;
    }

    public void setA(Element a) {
        this.a = a;
    }

    public Element getB() {
        return b;
    }

    public void setB(Element b) {
        this.b = b;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Point2D getSeparator() {
        return separator;
    }

    public void setSeparator(Point2D separator) {
        this.separator = separator;
    }
}
