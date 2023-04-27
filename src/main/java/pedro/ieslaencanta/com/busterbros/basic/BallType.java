/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

/**
 *
 * @author DAWTarde
 */
public enum BallType{
    EXTRABIG(48,40,1),
    BIG(32,26,52),
    MEDIUM(16,14,86),
    LITTLE(8,7,106);
    protected final int width;
    protected final int height;
    protected final int starx;

    private BallType(int width, int height, int starx) {
        this.width = width;
        this.height = height;
        this.starx = starx;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getStarx() {
        return starx;
    }
    
    
}
