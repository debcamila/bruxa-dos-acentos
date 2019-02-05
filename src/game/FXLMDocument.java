package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class FXLMDocument extends Application {
    
    public static Stage stage ;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        this.stage = stage;
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Bruxa dos Acentos");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
