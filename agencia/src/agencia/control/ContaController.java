package agencia.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 *
 * @author lucas
 */
public class ContaController implements Initializable {
    
    @FXML
    private ChoiceBox cliente;
    private RadioButton contaCorrente;
    private RadioButton contaPoupanca;
    private TextField saldo;
    private Button salvar;
    private Button cancelar;
    private ChoiceBox comboCliente;
    private TextField conta;
    private TextField valor;
    private Label erroDeposito;
    private Label erroSaque;
    private Label erroTransferencia;
    private TextField contaOrigem;
    private TextField contaDestino;
    
        
    @FXML
    public void fechar(ActionEvent event){       
        MenuInicialController.fecharUmaTela(event);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
}
