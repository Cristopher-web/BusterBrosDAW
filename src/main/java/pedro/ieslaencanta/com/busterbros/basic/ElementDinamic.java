/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

import java.util.Optional;
import pedro.ieslaencanta.com.busterbros.basic.interfaces.IColidable;

/**
 *
 * @author DAWTarde
 */
public class ElementDinamic extends Element implements IColidable {
   
    public ElementDinamic() { 
    }

    public ElementDinamic(double x, double y, double width, double height) {
        super(x, y, width, height);  
    }



    @Override
    public Optional<Collision> Collision(Element e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
