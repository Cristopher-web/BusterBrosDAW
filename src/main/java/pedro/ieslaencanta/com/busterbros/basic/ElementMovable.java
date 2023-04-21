/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

import javafx.geometry.Rectangle2D;
import pedro.ieslaencanta.com.busterbros.basic.interfaces.IMovable;

/**
 *
 * @author DAWTarde
 */
public class ElementMovable extends ElementDinamic implements IMovable{
    private double vx;
    private double vy;

    public ElementMovable() {
    }

    public ElementMovable(double vx, double vy, double x, double y, double width, double height) {
        super(x, y, width, height);
        this.vx = vx;
        this.vy = vy;
    }

      @Override
    public BorderCollision isInBorder(Rectangle2D borde) {
        BorderCollision collision=BorderCollision.NONE;
        if(this.rectangle.getMinY() < borde.getMinY()){
            collision=BorderCollision.TOP;
        }else if(this.rectangle.getMaxY()>borde.getMaxY()){
            collision = BorderCollision.DOWN;
        }else if(this.rectangle.getMinX() < borde.getMinX()){
            collision = BorderCollision.LEFT;
        }else if(this.rectangle.getMaxX() > borde.getMaxX()){
            collision = BorderCollision.RIGHT;
        }
        return collision;
    }

    @Override
    public void move(double x, double y) {
            this.rectangle= new Rectangle2D(this.rectangle.getMinX() + x,
                                            this.rectangle.getMinY() + y,
                                            this.rectangle.getWidth(),
                                            this.rectangle.getHeight());
    }
    
    @Override
    public void move() {
        this.rectangle= new Rectangle2D(this.rectangle.getMinX() + vx,
                                            this.rectangle.getMinY() + vy,
                                            this.rectangle.getWidth(),
                                            this.rectangle.getHeight());
    }

    @Override
    public void moveLeft() {
           this.rectangle= new Rectangle2D(this.rectangle.getMinX() - this.vx,
                                            this.rectangle.getMinY(),
                                            this.rectangle.getWidth(),
                                            this.rectangle.getHeight());
    }

    @Override
    public void moveLeft(double inc) {
          this.rectangle= new Rectangle2D(this.rectangle.getMinX() - inc,
                                            this.rectangle.getMinY(),
                                            this.rectangle.getWidth(),
                                            this.rectangle.getHeight());
    }
   @Override
    public void moveRight() {
         this.rectangle= new Rectangle2D(this.rectangle.getMinX() + this.vx,
                                            this.rectangle.getMinY(),
                                            this.rectangle.getWidth(),
                                            this.rectangle.getHeight());
    }
    @Override
    public void moveRight(double inc) {
           this.rectangle= new Rectangle2D(this.rectangle.getMinX() + inc,
                                            this.rectangle.getMinY(),
                                            this.rectangle.getWidth(),
                                            this.rectangle.getHeight());
    }

    @Override
    public void moveUp() {
         this.rectangle= new Rectangle2D(this.rectangle.getMinX(),
                                            this.rectangle.getMinY() - this.vy,
                                            this.rectangle.getWidth(),
                                            this.rectangle.getHeight());
    }

    @Override
    public void moveUp(double inc) {
          this.rectangle= new Rectangle2D(this.rectangle.getMinX(),
                                            this.rectangle.getMinY() - inc,
                                            this.rectangle.getWidth(),
                                            this.rectangle.getHeight());
           }

    @Override
    public void moveDown() {
          this.rectangle= new Rectangle2D(this.rectangle.getMinX(),
                                            this.rectangle.getMinY() + this.vx,
                                            this.rectangle.getWidth(),
                                            this.rectangle.getHeight());
    }

    @Override
    public void moveDown(double inc) {
        this.rectangle= new Rectangle2D(this.rectangle.getMinX(),
                                            this.rectangle.getMinY() + inc,
                                            this.rectangle.getWidth(),
                                            this.rectangle.getHeight());
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void start() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void pause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }    

    
}
