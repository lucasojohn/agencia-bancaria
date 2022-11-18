package agencia.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 *
 * @author lucas
 */
public class ClienteController implements Initializable {
    
    @FXML
    private TextField cpf;
    private TextField nome;
    private TextField dataNasc;
    private RadioButton masculino;
    private RadioButton feminino;
    private TextField endereco;
    private RadioButton contaCorrente;
    private RadioButton contaPoupanca;
    private TextField saldo;
    private Button deletar;
    private Button cancelar;
    private Button buscar;
    private TextField dados;
    private Button sair;
    
    
    @FXML
    public void fechar(ActionEvent event){       
        MenuInicialController.fecharUmaTela(event);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
}
