package agencia.control;

import agencia.Clientes;
import agencia.dao.ClienteDAO;
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
import javax.persistence.EntityExistsException;

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

        // validar se cliente com o CPF recebido ja existe
        
        ClienteDAO dao = new ClienteDAO();
        Clientes c = new Clientes();
        
        java.sql.Date dataDatePicker = java.sql.Date.valueOf(dataNasc.getValue());
        
        if (feminino.isSelected()) {
            c.setSexo("f");
        } else if (masculino.isSelected()) {
            c.setSexo("m");
        }
                
        c.setCpf(cpf.getText());
        c.setNome(nome.getText());
        c.setEndereco(endereco.getText());
        c.setNascimento(dataDatePicker);
        if (feminino.isSelected()) {
            c.setSexo("f");
        } else if (masculino.isSelected()) {
            c.setSexo("m");
        }
        
        System.out.println("teste chegou aqui 1");
        
        try {
            System.out.println("teste chegou aqui 2");
            dao.salva(c);
            System.out.println("teste chegou aqui 3");
        } catch (EntityExistsException e) {
            validacao.setText("CPF já cadastrado.");
            validacao.setVisible(true);
        }

        // Criar a conta do banco
        
    }
    
    @FXML
    public void setFeminino(ActionEvent event){
        masculino.setSelected(false);

    }
    
    @FXML
    public void setMasculino(ActionEvent event){
        feminino.setSelected(false);

    }
    
    @FXML
    public void setPoupanca(ActionEvent event){
        contaCorrente.setSelected(false);
    }
    
    @FXML
    public void setCorrente(ActionEvent event){
        contaPoupanca.setSelected(false);
    }
    
    private void validaCampos() {
        if (
                cpf.getText().isEmpty() || nome.getText().isEmpty() || 
                endereco.getText().isEmpty() || dataNasc.getValue() == null ||
                (feminino.isSelected() || masculino.isSelected()) == false ||
                (contaCorrente.isSelected() || contaPoupanca.isSelected()) == false
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
