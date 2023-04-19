/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros;

import javafx.geometry.Dimension2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

/**
 * Se encarga de leer el teclado, imagen de fondo además del reloj Hz (parar e
 * iniciar
 *
 * @author Administrador
 * @see Clock Board
 */
public class Game extends Clock implements IKeyListener {

    public static final int SCALE = 2;
    public static final int INFOAREA = 48;
    public static Image imagenes = null;

    private Dimension2D original_size;
    private Board board;
    private GraphicsContext ctx, bg_context;

    /**
     * constructor
     *
     * @param context
     * @param bg_context
     * @param original
     */
    public Game(GraphicsContext context, GraphicsContext bg_context,
            Dimension2D original) {
        super(50);
        this.ctx = context;
        this.bg_context = bg_context;
        this.original_size = original;
        this.initBoard();

        // this.start();
    }

    public void start() {
        super.start();
        this.board.paintBackground();
    }

    @Override
    protected void onEvent() {
        this.board.TicTac();
    }

    /**
     * inicia el tablero
     */
    private void initBoard() {
        this.board = new Board(original_size);
        this.board.setBackGroundGraphicsContext(bg_context);
        this.board.setGraphicsContext(ctx);
    }

    @Override
    public void onKeyPressed(KeyCode code) {
        this.board.onKeyPressed(code);
        if (code == KeyCode.ADD) {
            this.incFrecuency();
        }

    }

    @Override
    public void onKeyReleased(KeyCode code) {
        this.board.onKeyReleased(code);
        if (code == KeyCode.SUBTRACT) {
            this.decFrencuecy();
        }
    }

}
