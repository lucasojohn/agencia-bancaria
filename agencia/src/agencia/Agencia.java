/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package agencia;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author lucas
 */
public class Agencia extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(carregaTela("MenuInicial"));
        
        stage.setScene(scene);
        stage.show();
    }
    
    public static Parent carregaTela(String nomeTela) throws IOException{
        
        FXMLLoader carregar = 
                new FXMLLoader(
      Agencia.class.getResource("/agencia/view/"  + nomeTela + ".fxml"));
        return carregar.load();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
