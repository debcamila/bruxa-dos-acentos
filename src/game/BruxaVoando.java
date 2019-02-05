
package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class BruxaVoando extends Application{
    private final boolean running = true;
    private int MOVE_IMAGEM_X;
    private int MOVE_IMAGEM_Y;
    private final Group root = new Group();
    private final Scene theScene = new Scene(root);
    private final Canvas canvas = new Canvas(1024, 1080);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();
    
    Image bruxa  = new Image("witch.png"); 
    
    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setScene(theScene);
        root.getChildren().add(canvas);
        final long startNanoTime = System.nanoTime();
        
        new AnimationTimer(){
            @Override
            public void handle(long atualNanoTime) {
                
                double t = (atualNanoTime - startNanoTime)/1000000000.0;
                double x = 232 +128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);
                
                gc.drawImage(bruxa, y, x);
                mexerImagemMouse();
            }
        }.start();
        mexerImagemMouse();
        primaryStage.show(); 
    }
    
    public void mexerImagemMouse(){
        
        theScene.setOnKeyPressed ((KeyEvent event) -> {
            gc.drawImage (bruxa ,  MOVE_IMAGEM_X, MOVE_IMAGEM_Y + 5 );
            if (event.getCode ( ) == KeyCode.RIGHT ) {
                if ( MOVE_IMAGEM_X <= 476 ) {
                    MOVE_IMAGEM_X = MOVE_IMAGEM_X + 5;
                    gc.drawImage (bruxa, MOVE_IMAGEM_X, MOVE_IMAGEM_Y + 5, 70, 70 );
                }
            }
            else if ( event.getCode ( ) == KeyCode.LEFT ) {
                if ( MOVE_IMAGEM_X >= 10 ) {
                    MOVE_IMAGEM_X = MOVE_IMAGEM_X - 5;
                    gc.drawImage (bruxa, MOVE_IMAGEM_X, MOVE_IMAGEM_Y + 5, 70, 70 );
                }
            }
            else if ( event.getCode ( ) == KeyCode.UP ) {
                if ( MOVE_IMAGEM_Y >= 20 ) {
                    MOVE_IMAGEM_Y = MOVE_IMAGEM_Y - 5;
                    gc.drawImage (bruxa, MOVE_IMAGEM_X, MOVE_IMAGEM_Y + 5, 70, 70 );
                }
            }
            else if ( event.getCode ( ) == KeyCode.DOWN ) {
                if ( MOVE_IMAGEM_Y <= 220 ) {
                    MOVE_IMAGEM_Y = MOVE_IMAGEM_Y + 5;
                    gc.drawImage (bruxa, MOVE_IMAGEM_X, MOVE_IMAGEM_Y + 5, 70, 70 );
                }
            }
        });
    }
}
