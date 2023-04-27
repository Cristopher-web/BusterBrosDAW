/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros;

import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import pedro.ieslaencanta.com.busterbros.basic.Ball;
import pedro.ieslaencanta.com.busterbros.basic.Brick;
import pedro.ieslaencanta.com.busterbros.basic.Brickbreakeable;
import pedro.ieslaencanta.com.busterbros.basic.Element;
import pedro.ieslaencanta.com.busterbros.basic.ElementMovable;
import pedro.ieslaencanta.com.busterbros.basic.ElementWithGravity;
import pedro.ieslaencanta.com.busterbros.basic.Ladder;
import pedro.ieslaencanta.com.busterbros.basic.Level;
import pedro.ieslaencanta.com.busterbros.basic.interfaces.IMovable;
import static pedro.ieslaencanta.com.busterbros.basic.interfaces.IMovable.BorderCollision.DOWN;
import static pedro.ieslaencanta.com.busterbros.basic.interfaces.IMovable.BorderCollision.LEFT;
import static pedro.ieslaencanta.com.busterbros.basic.interfaces.IMovable.BorderCollision.RIGHT;
import static pedro.ieslaencanta.com.busterbros.basic.interfaces.IMovable.BorderCollision.TOP;

;

/**
 * Tablero del juego, posee un fondo (imagen estática, solo se cambia cuando se
 * cambia el nivel), Una bola, un vector de niveles, un disparador y una matriz
 * de bolas gestiona la pulsación de tecla derecha e izquierda
 *
 * @author Pedro
 * @see Bubble Level Shttle BallGrid
 */
public class Board implements IKeyListener {

    private Rectangle2D game_zone;

    private GraphicsContext gc;
    private GraphicsContext bggc;
    private final Dimension2D original_size;

    private boolean debug;
    private boolean left_press, right_press, up_press, down_press;
    private Level levels[];
    private int actual_level = -1;
    private MediaPlayer backgroundsound;
    private Element[] elements;
    private ElementWithGravity jugador;
    private ElementWithGravity bola;

    public Board(Dimension2D original) {
        this.gc = null;
        this.game_zone = new Rectangle2D(8, 8, 368, 192);
        this.original_size = original;
        this.right_press = false;
        this.left_press = false;
        this.up_press = false;
        this.down_press = false;

        this.debug = false;

        this.actual_level = 12;

        this.createLevels();
        this.nextLevel();
        
        this.jugador = new ElementWithGravity(0,2,false,true,2,2,50,50,32,32);
        this.bola = new ElementWithGravity(0,0.4,true,true,2,2,30,30,10,10);
        }

    private void createLevels() {
        int y = 44;
        this.levels = new Level[50];
        for (int i = 0; i < 25; i++) {
            this.levels[2 * i] = new Level();//(8, y);
            this.levels[2 * i].setX(8);
            this.levels[2 * i].setY(y);
            this.levels[2 * i].setImagename("bricks");
            this.levels[2 * i].setBackgroundname("backgrounds");
            this.levels[2 * i].setSoundName("fondo");
            this.levels[2 * i].setTime(30);

            this.levels[2 * i + 1] = new Level();//(8, y);
            this.levels[2 * i + 1].setX(400);
            this.levels[2 * i + 1].setY(y);
            this.levels[2 * i + 1].setImagename("bricks");
            this.levels[2 * i + 1].setBackgroundname("backgrounds");
            this.levels[2 * i + 1].setSoundName("fondo");

            this.levels[2 * i + 1].setTime(30);

            y += 216;
        }
    }

    private void createElementsLevel() {
        Brick tempo;
        Brickbreakeable rompe;
        Ladder escalera;
        ElementWithGravity m;
        Pair<Level.ElementType, Rectangle2D>[] fi = this.levels[this.actual_level].getFigures();
        this.elements = new Element[fi.length];
        for (int i = 0; i < fi.length; i++) {

         

          
            switch (fi[i].getKey()) {
                case FIXED:
                    tempo = new Brick((fi[i].getValue().getMinX() - this.levels[this.actual_level].getX()),
                    (fi[i].getValue().getMinY() - this.levels[this.actual_level].getY()),
                    fi[i].getValue().getWidth(),
                    fi[i].getValue().getHeight()
                    );
                    tempo.setImg(Resources.getInstance().getImage("bricks"));
                    tempo.setOriginal(fi[i].getValue());
                    this.elements[i]=tempo;
                    break;
                case BREAKABLE:
                    rompe = new Brickbreakeable((fi[i].getValue().getMinX() - this.levels[this.actual_level].getX()),
                    (fi[i].getValue().getMinY() - this.levels[this.actual_level].getY()),
                    fi[i].getValue().getWidth(),
                    fi[i].getValue().getHeight()
                    );
                    rompe.setImg(Resources.getInstance().getImage("bricks"));
                    rompe.setOriginal(fi[i].getValue());
                    this.elements[i]=rompe;
                    break;
                case LADDER:
                    escalera = new Ladder((fi[i].getValue().getMinX() - this.levels[this.actual_level].getX()),
                    (fi[i].getValue().getMinY() - this.levels[this.actual_level].getY()),
                    fi[i].getValue().getWidth(),
                    fi[i].getValue().getHeight()
                       );
                    escalera.setImg(Resources.getInstance().getImage("bricks"));
                    escalera.setOriginal(fi[i].getValue());
                    this.elements[i]=escalera;
                    break;
            }
        }

    }

    /**
     * @param debug the debug to set
     */
    public void setGraphicsContext(GraphicsContext gc) {
        this.gc = gc;

    }

    public void setBackGroundGraphicsContext(GraphicsContext gc) {
        this.bggc = gc;
        this.paintBackground();
    }
    
    

    /**
     * cuando se produce un evento
     */
    public synchronized void TicTac() {
        this.process_input();

        this.update();
        this.render();

        //actualizar
    }
    public void setDebug(){
        this.debug=!this.debug;
        this.jugador.setDebug(debug);
        for(int i = 0;i < this.elements.length; i++){
            this.elements[i].setDebug(debug);
        }
    }
    
    private void evalBorder(ElementMovable e){
        e.move();
        IMovable.BorderCollision b = e.isInBorder(game_zone);
        switch(b){
            case DOWN:
                e.setVy(-Math.abs(e.getVy()));
                e.setPosition(e.getRectangle().getMinX(),this.game_zone.getMaxY() - e.getRectangle().getHeight());
                break;
            case TOP:
                 e.setVy(Math.abs(e.getVy()));
                break;
            case LEFT:
                 e.setVx(Math.abs(e.getVx()));
                 break;
            case RIGHT:
                e.setVx(-Math.abs(e.getVx()));
                break;
            }
    }
    
    private void update() {
       /* for(int i = 0; i<this.elements.length;i++){
            if(this.elements[i] != null && this.elements[i] instanceof IMovable){
                ((ElementMovable)this.elements[i]).move(2, 0);
            }
        }*/
        this.evalBorder(bola);
        /*for(int i = 0; i < this.balls.leght; i++){
            this.evalBorder()
        }*/
    }
    private void evalCollisions() {

    }

    private void render() {
        this.clear();
        if (this.elements != null) {
            for (int i = 0; i < this.elements.length; i++) {

                this.elements[i].paint(gc);
            }
        }
        this.jugador.paint(gc);
        this.jugador.setColor(Color.GOLD);
        this.bola.paint(gc);
        this.bola.setColor(Color.ALICEBLUE);
    }

    private void process_input() {

        if (this.left_press) {
            this.jugador.moveLeft();
             if(this.jugador.isInBorder(game_zone)==IMovable.BorderCollision.LEFT){
                this.jugador.setPosition(this.game_zone.getMinX(), this.jugador.getRectangle().getMinY());
            }
            
        } else if (this.right_press) {
            this.jugador.moveRight();
               if(this.jugador.isInBorder(game_zone)==IMovable.BorderCollision.RIGHT){
                this.jugador.setPosition(this.game_zone.getMaxX() - this.jugador.getRectangle().getWidth(),
                        this.jugador.getRectangle().getMinY());
            }
        }
        if (this.up_press) {
            this.jugador.moveUp();
            if(this.jugador.isInBorder(game_zone)==IMovable.BorderCollision.TOP){
                this.jugador.setPosition(this.jugador.getRectangle().getMinX(),this.game_zone.getMinY());
            }
        }
        if (this.down_press) {
            this.jugador.moveDown();
            if(this.jugador.isInBorder(game_zone)==IMovable.BorderCollision.DOWN){
                  this.jugador.setPosition(this.jugador.getRectangle().getMinX(),this.game_zone.getMaxY() - this.jugador.getRectangle().getHeight());
            }
        }

    }

    /**
     * limpiar la pantalla
     */
    private void clear() {
        this.gc.restore();
        this.gc.clearRect(0, 0, this.original_size.getWidth() * Game.SCALE, this.original_size.getHeight() * Game.SCALE);
    }

    /**
     * pintar el fonodo
     */
    public void paintBackground() {
        if (this.bggc != null) {
            this.bggc.clearRect(0, 0, this.original_size.getWidth() * Game.SCALE, (this.original_size.getHeight() + Game.INFOAREA) * Game.SCALE);
            this.bggc.setFill(Color.BLACK);
            this.bggc.fillRect(0, 0, this.original_size.getWidth() * Game.SCALE, (this.original_size.getHeight() + Game.INFOAREA) * Game.SCALE);
            if (this.gc != null) {
                this.gc.clearRect(0, 0, this.original_size.getWidth() * Game.SCALE, (this.original_size.getHeight() + Game.INFOAREA) * Game.SCALE);
            }

            this.bggc.drawImage(this.levels[actual_level].getBackground(),
                    this.levels[actual_level].getX(), this.levels[actual_level].getYBackground(), this.original_size.getWidth(), this.original_size.getHeight(),
                    0, 0, this.original_size.getWidth() * Game.SCALE, this.original_size.getHeight() * Game.SCALE);
        }
    }

    /**
     * gestión de pulsación
     *
     * @param code
     */
    @Override
    public void onKeyPressed(KeyCode code) {
        switch (code) {
            case LEFT:
                this.left_press = true;

                break;
            case RIGHT:

                this.right_press = true;
                break;
            case UP:
                this.up_press = true;
                break;
            case DOWN:
                this.down_press = true;
                break;
        }
    }

    @Override
    public void onKeyReleased(KeyCode code) {

        switch (code) {
            case LEFT:
                this.left_press = false;
                break;
            case RIGHT:
                this.right_press = false;
                break;
            case UP:
                this.up_press = false;
                break;
            case DOWN:
                this.down_press = false;
                break;
            case D:
                this.setDebug();
                break;
            case N:
                this.nextLevel();
                break;

        }

    }

    private void nextLevel() {
        this.actual_level++;
        if (this.actual_level >= this.levels.length) {
            this.actual_level = 0;
        }
        if(this.backgroundsound != null){
            this.backgroundsound.stop();
        }
        this.backgroundsound=Resources.getInstance().getSound("fondo");
        this.backgroundsound.play();
        this.levels[this.actual_level].analyze();
        this.createElementsLevel();

        this.paintBackground();
        Game.reset_counter();

    }
}
