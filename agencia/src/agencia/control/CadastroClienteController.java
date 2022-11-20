package agencia.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author lucas
 */
public class CadastroClienteController implements Initializable {
    
    @FXML
    private TextField cpf;
    @FXML
    private TextField nome;
    @FXML
    private DatePicker dataNasc;
    @FXML
    private RadioButton masculino;
    @FXML
    private RadioButton feminino;
    @FXML
    private TextField endereco;
    @FXML
    private RadioButton contaCorrente;
    @FXML
    private RadioButton contaPoupanca;
    @FXML
    private TextField saldo;
    @FXML
    private Button deletar;
    @FXML
    private Button cancelar;
    @FXML
    private Text validacao;
    
    
    @FXML
    public void fechar(ActionEvent event){       
        MenuInicialController.fecharUmaTela(event);
    }
    
    @FXML
    public void salvarCliente(ActionEvent event){
        validaCampos();

    }
    
    private void validaCampos() {
        if (
                cpf.getText().isEmpty() || nome.getText().isEmpty() || 
                endereco.getText().isEmpty() || dataNasc == null
            ) {
            validacao.setText("Campo obrigatório não preenchido.");
            validacao.setVisible(true);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}
