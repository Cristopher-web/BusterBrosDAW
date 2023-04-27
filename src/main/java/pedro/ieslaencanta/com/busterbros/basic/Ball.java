/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

/**
 *
 * @author DAWTarde
 */
public class Ball extends ElementWithGravity{
  private double originalvy;

    public Ball(Ball originalvy, double gx, double gy, boolean activegravityx, boolean activegravityy, double vx, double vy, double x, double y, double width, double height) {
        super(gx, gy, activegravityx, activegravityy, vx, vy, x, y, width, height);
        this.originalvy = vy;
    }


   
 public void resetVy(){
    this.setVy(originalvy);
 }
  
  
}
