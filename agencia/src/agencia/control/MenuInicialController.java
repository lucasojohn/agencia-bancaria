/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package agencia.control;

import agencia.Agencia;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

/**
 *
 * @author lucas
 */
public class MenuInicialController implements Initializable {
    
    @FXML
    private TitledPane conteudo;
    
    @FXML
    void acaoMenu(ActionEvent event) throws IOException {

        MenuItem item = (MenuItem)event.getSource();
        
        switch(item.getId()){
            case "menuNovoCliente":
                conteudo.setText("Cadastro de Cliente");
                conteudo.setContent(Agencia.carregaTela("CadastroCliente"));
                conteudo.setVisible(true);
                break;
            case "menuDeletarCliente":
                conteudo.setText("Exclusão de Cliente");
                conteudo.setContent(Agencia.carregaTela("DeletaCliente"));
                conteudo.setVisible(true);
                break;
            case "menuNovoConta":
                conteudo.setText("Cadastro de Conta");
                conteudo.setContent(Agencia.carregaTela("CadastroConta"));
                conteudo.setVisible(true);
                break;
            case "menuDeletarConta":
                conteudo.setText("Exclusão de Conta");
                conteudo.setContent(Agencia.carregaTela("DeletaConta"));
                conteudo.setVisible(true);
                break;
            case "menuDeposito":
                conteudo.setText("Deposito em Conta");
                conteudo.setContent(Agencia.carregaTela("Deposito"));
                conteudo.setVisible(true);
                break;
            case "menuSair":
                Stage stage = (Stage) conteudo.getScene().getWindow();
                stage.close();
                break;
        }
    }
    
    public static void fecharUmaTela(ActionEvent event){
        Node no = (Node) event.getSource();
        
        while (no != null && !(no instanceof TitledPane)) {
            no = no.getParent();
        }
        
        if (no != null) {
            TitledPane tp = (TitledPane)no;
            tp.setContent(null);
            tp.setVisible(false);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conteudo.setVisible(false);
    }    
   
}
